(ns techne.map-utils)

(defn keep-if
  ([key fun val map]
   (if (fun val)
       (assoc map key val)
       (dissoc map key)))
  ([key fun map]
   (if (fun key)
       (map)
       (dissoc map key))))


