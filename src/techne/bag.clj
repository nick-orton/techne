(ns techne.bag
  )

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (put-all [self items])
  (pluck-n [self item n])
  (pluck [self item])
  (uniques [self])
  (inspect [self])  ;pull up into an inspectable
  (total [self])
  (->seq [self])
  (tally [self item]))

(deftype MapBag [state]
  Bag
    (put-n [self item n] 
      (let [n* (+ n (tally self item))]
        (MapBag. (assoc state item n*))))
    (put [self item]
      (put-n self item 1))
    (put-all [self items]
      (reduce #(put %1 %2) self items))
    (tally [self item] 
      (get state item 0))
    (pluck-n [self item n]
      "remove n items from the bag. If there is a non-positive count in the map, remove the key"
      (let [n* (- (tally self item) n)
            state* (if (pos? n*)
                       (assoc state item n*)
                       (dissoc state item))]
           (MapBag. state*)))
    (pluck [self item]
      (pluck-n self item 1))
    (inspect [self] state)
    (uniques [self] (set (keys state))) 
    (total [self]
      (reduce #(+ %1 (tally self %2)) 0 (uniques self)))
    (->seq [self]
           ;TODO
      (reduce #(concat (repeat (tally self %2) %2) %1) [] (uniques self)))
           
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
  (put-all (create) sq))


