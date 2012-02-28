(ns techne.data-structs.binary-search-tree
  (:use [techne.data-structs.binary-tree]
        [techne.vectors]
        [techne.utils :only [always]]))

; returns the index if present or nil otherwise
(defn locate [tree target]
  (loop [i 0]
    (cond (= i (count tree))
            nil
          (nil? (tree i))
            nil
          (= target (tree i))
            i
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

(defn has-left-child [tree i]
  (and (> (count tree) (left-index i) )
       (not (nil? (tree (left-index i))))))

(defn has-right-child [tree i]
  (let [right (inc (left-index i))]
    (and (> (count tree) right )
         (not (nil? (tree right))))))

(defn delete [tree elem]
  (let [i (locate tree elem)]
    (cond
      (nil? i)
        tree
      (and (has-left-child tree i) (has-right-child tree i))
        nil ; TODO see below
      (has-left-child tree i)
        nil ; TODO delete i replace i with left
      (has-right-child tree i)
        nil ; TODO delete i replace i with right
      true ; has no children
        (assoc tree i nil))))

; Call the node to be deleted N. Do not delete N. Instead, choose either its
; in-order successor node or its in-order predecessor node, R. Replace the
; value of N with the value of R, then delete R.
; As with all binary trees, a node's in-order successor is the left-most child
; of its right subtree


