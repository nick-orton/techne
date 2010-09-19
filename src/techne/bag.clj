(ns techne.bag
  (:use [techne.map-utils]))

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (pluck-n [self item n])
  (pluck [self item])
  (uniques [self])
  (inspect [self])  ;pull up into an inspectable
  (total [self])
  (tally [self item]))

(deftype MapBag [state]
  Bag
    (put-n [self item n] 
      (let [new-n (+ n (tally self item))]
        (MapBag. (assoc state item new-n))))
    (put [self item]
      (put-n self item 1))

    (tally [self item] 
      (get state item 0))
    (pluck-n [self item n]
      (let [prev (tally self item)
            new-n (- prev n)]
           (MapBag. (keep-if item pos? new-n state))))
    (pluck [self item]
      (pluck-n self item 1))
    (inspect [self] state)
    (uniques [self] (set (keys state))) 
    (total [self]
      (reduce #(+ %1 (tally self %2)) 0 (uniques self)))
           
  Object
    (toString [self]
      (str "Bag: " (:state self))))

(defn create 
  ([] 
   (MapBag. {}))
  ([state] 
   (MapBag. state)))

(defn seq->bag
  [sq]
  (reduce #(put %1 %2) (create) sq))


