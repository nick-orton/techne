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

