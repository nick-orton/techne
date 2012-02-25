(ns techne.vectors
  "tools for vectors"
)

(defn swap
  "swap two positions in a vector"
  [v i1 i2]
  (assoc v i2 (v i1) i1 (v i2)))

(defn vconcat [vectr other]
  (vec (concat vectr other)))

(defn vrest [vectr]
  (vec (rest vectr)))
