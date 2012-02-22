(ns techne.data-structs.binary-tree )

; TODO test
(defn parent-index [i]
  (dec (bit-shift-right (inc i) 1)))

; TODO test
(defn left-index [i]
  (dec (* (inc i) 2)))
