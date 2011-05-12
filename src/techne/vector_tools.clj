(ns techne.vector-tools
  "tools for vectors"
)

(defn swap [v i1 i2]
  "swap two positions in a vector"
  (assoc v i2 (v i1) i1 (v i2)))
