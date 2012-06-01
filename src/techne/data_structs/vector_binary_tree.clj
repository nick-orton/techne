; Functions for navigating a binary tree represented as a vector.

(ns techne.data-structs.vector-binary-tree )

(defn parent-index [i]
  (dec (bit-shift-right (inc i) 1)))

; The left child has a one-based index twice the parent's.
(defn left-index [i]
  (dec (* (inc i) 2)))

; The right child is immediately after the left
(defn right-index [i]
  (inc (left-index i)))

; The tree has a node if it is in the vector and not nil
(defn- has-node [tree index]
  (and (> (count tree) index )
       (not (nil? (tree index )))))

(defn has-left-child [tree i]
  (has-node tree (left-index i)))

(defn has-right-child [tree i]
  (has-node tree (right-index i)))

; Navigate down the left child branch as far as possible
(defn leftmost-child [tree index]
  (loop [i index]
    (if (has-left-child tree i)
        (recur (left-index i))
        i)))
