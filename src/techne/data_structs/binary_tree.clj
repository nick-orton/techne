(ns techne.data-structs.binary-tree )

; Methods for navigating a binary tree represented as a vector.

; TODO test
(defn parent-index [i]
  (dec (bit-shift-right (inc i) 1)))

; TODO test
(defn left-index [i]
  (dec (* (inc i) 2)))

; TODO test
(defn right-index [i]
  (inc (left-index i)))

; TODO test
; The left child has a one-based index twice the parent's.
(defn has-left-child [tree i]
  (and (> (count tree) (left-index i) )
       (not (nil? (tree (left-index i))))))

; TODO test
(defn has-right-child [tree i]
  (let [right (inc (left-index i))]
    (and (> (count tree) right )
         (not (nil? (tree right))))))

; TODO test
(defn leftmost-child [tree index]
  (loop [i index]
    (if (has-left-child tree i)
        (recur (left-index i))
        i)))


