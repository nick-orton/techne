(ns techne.maps
  "utilities for working with Associative data strutures"
)

(defn keep-key-if
  "dissoc a key from an Associative if the predicate fails"
  [map pred key]
  (if (pred key)
      map
      (dissoc map key)))
    
(defn remove-key-if
  "dissoc a key from an Associative if the predicate passes"
  [map pred key]
  (keep-key-if map #(not (pred %)) key)) 
    
