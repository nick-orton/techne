(ns techne.bag)


;(defn new 
;  ([] '())
;  ([& args] args))


(defn put-occurances
  [bag item occurances]
  (let [prev (get bag item 0)]
    (assoc bag item (+ prev occurances))))

(defn put 
  [bag item] 
  (put-occurances bag item 1))


(defn remove-occurances
  [bag item occurances]
  (let [prev (get bag item 0)
        new-occurances (- prev occurances)]
    (if (pos? new-occurances)
        (assoc bag item new-occurances)
        (dissoc bag item))))

(defn remove 
  [bag item]
  (remove-occurances bag item 1))
