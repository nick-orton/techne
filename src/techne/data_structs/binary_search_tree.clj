(ns techne.data-structs.binary-search-tree
  (:use [techne.data-structs.binary-tree]
        [techne.vectors]
        [techne.utils :only [always]]))

; returns the target if present or nil otherwise
(defn locate [tree target]
  (loop [i 0]
    (cond (= i (count tree))
            nil
          (nil? (tree i))
            nil
          (= target (tree i))
            (tree i)
          (< target (tree i))
            (recur (left-index i))
          true
            (recur (inc (left-index i))))))

(defn insert [tree elem]
  (loop [i 0]
    (cond
      (>= i (count tree))
        (let [expansion (repeat (- i (count tree)) nil)]
          (vconcat tree (conj (vec expansion) elem )))
      (< elem (tree i))
        (recur (left-index i))
      true
        (recur (inc (left-index i))))))
