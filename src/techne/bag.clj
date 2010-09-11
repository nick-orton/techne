(ns techne.bag
  (:use [techne.map-utils]))

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (inspect [self])
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
   Object
    (toString [self]
      (str ("Bag: " (inspect self))))
 )

(defn new-bag 
  ([] 
   (MapBag. {}))
  ([state] 
   (MapBag. state)))


(defn get-occurances
  [bag item]
  (get bag item 0))

(defn remove-occurances
  [bag item occurances]
  (let [prev (get-occurances bag item)
        new-occurances (- prev occurances)]
    (keep-if item pos? new-occurances bag)))

(defn remove 
  [bag item]
  (remove-occurances bag item 1))
