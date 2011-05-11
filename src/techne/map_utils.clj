(ns techne.map-utils)

(defn keep-if
  {:deprecated "0.6.1"}
  ([key pred val map]
    (if (pred val)
        (assoc map key val)
        (dissoc map key)))
  ([key pred map]
    (if (pred key)
        map
        (dissoc map key))))

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
    
