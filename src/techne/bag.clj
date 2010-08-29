(ns techne.bag
  (:use [techne.map-utils]))


;(defn new 
;  ([] '())
;  ([& args] args))


(defn get-occurances
  [bag item]
  (get bag item 0))

(defn put-occurances
  [bag item occurances]
  (let [prev (get-occurances bag item)]
    (assoc bag item (+ prev occurances))))

(defn put 
  [bag item] 
  (put-occurances bag item 1))


(defn remove-occurances
  [bag item occurances]
  (let [prev (get-occurances bag item)
        new-occurances (- prev occurances)]
    (keep-if item pos? new-occurances bag)))

(defn remove 
  [bag item]
  (remove-occurances bag item 1))
