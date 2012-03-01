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
            (recur (right-index i)))))

(defn insert [tree elem]
  (loop [i 0]
    (cond
      (>= i (count tree))
        (let [expansion (repeat (- i (count tree)) nil)]
          (vconcat tree (conj (vec expansion) elem )))
      (< elem (tree i))
        (recur (left-index i))
      true
        (recur (right-index i)))))

; the in-order-successor of a node in the tree is the leftmost child
; of the right subtree
(defn- in-order-successor [tree i]
  (leftmost-child tree (right-index i)))

(defn delete [tree elem]
  (clean-right
    (loop [t tree
           i (locate tree elem) ]
      (cond
        (nil? i)
          tree
        (and (has-left-child t i) (has-right-child t i))
          (let [suc (in-order-successor t i)]
            (recur (assoc t i (t suc)) suc))
        (has-left-child t i)
          (recur (assoc t i (t (left-index i))) (left-index i))
        (has-right-child t i)
            (recur (assoc t i (t (right-index i))) (right-index i))
        true ; has no children
          (assoc t i nil)))))
