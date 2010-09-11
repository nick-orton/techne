(ns techne.bag
  (:use [techne.map-utils]))

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (inspect [self])
  (pluck-n [self item n])
  (pluck [self item])
  (get-n [self item])           
             )

(defrecord MapBag [state]
  Bag
    (put-n [self item n] 
      (let [new-n (+ n (get-n self item))]
        (MapBag. (assoc state item new-n))))
    (put [self item]
      (put-n self item 1))
    (inspect [self] state)
    (get-n [self item] 
      (get state item 0))
    (pluck-n [self item n]
      (let [prev (get-n self item)
            new-n (- prev n)]
           (MapBag. (keep-if item pos? new-n state))))
    (pluck [self item]
      (pluck-n self item 1))

  Object
    (toString [self]
      (str ("Bag: " (inspect self))))
 )

(defn new-bag 
  ([] 
   (MapBag. {}))
  ([state] 
   (MapBag. state)))


