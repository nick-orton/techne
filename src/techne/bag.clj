(ns techne.bag
  (:use [techne.map-utils])
  (:import java.util.Map))

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (pluck-n [self item n])
  (pluck [self item])
  ;(count [self])
  (count [self item]))

(defrecord MapBag [state]
  Bag
    (put-n [self item n] 
      (let [new-n (+ n (count self item))]
        (MapBag. (assoc state item new-n))))
    (put [self item]
      (put-n self item 1))

    (count [self item] 
      (get state item 0))
    (pluck-n [self item n]
      (let [prev (count self item)
            new-n (- prev n)]
           (MapBag. (keep-if item pos? new-n state))))
    (pluck [self item]
      (pluck-n self item 1))

;  java.util.Map 
;    (getKeys [self] (keys state)) ;TODO TEST
           
  Object
    (toString [self]
      (str ("Bag: " (:state self)))))

(defn create 
  ([] 
   (MapBag. {}))
  ([state] 
   (MapBag. state)))

(defn seq->bag
  [sq]
  (reduce #(put %1 %2) (create) sq))


