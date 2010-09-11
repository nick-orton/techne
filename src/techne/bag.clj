(ns techne.bag
  (:use [techne.map-utils]))

(defprotocol Bag
  (put-n [self item n])
  (put [self item])
  (inspect [self]))

(defrecord MapBag [state]
  Bag
    (put-n 
      [self item n] 
      (MapBag. (assoc state item n)))
    (put 
      [self item]
      (put-n self item 1))
    (inspect [self] state)
   Object
    (toString [self]
      (str ("Bag: " (inspect self))))
 )

(defn new-bag [] (MapBag. {}))


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
