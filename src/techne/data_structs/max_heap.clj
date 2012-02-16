(ns techne.data-structs.max-heap)


(defn- swap [heap i1 i2]
  (let [v1 (heap i1)
        v2 (heap i2)]
    (assoc (assoc heap i1 v2) i2 v1)))

(defn insert [heap element]
  (loop [h (conj heap element)
         i (count heap)]
    (if (= 0 i)
        h
        (let [parent-i (dec (bit-shift-right (inc i) 1))]
             (if (> (h parent-i) (h i))
                 h
                 (recur (swap h parent-i i) parent-i))))))

(defn delete [heap]
  (loop [h (pop (swap heap 0 (dec (count heap))))
         i 0]
    (let [left-i (dec (* (inc i) 2))
          right-i (inc left-i)]
      (cond (and (< left-i (count h)) (> (h left-i) (h i)))
              (recur (swap h left-i i) left-i)
            (and (< right-i (count h)) (> (h right-i) (h i)))
              (recur (swap h right-i i) right-i)
            true
             h))))

(defn merge-h [heap1 heap2]
  (loop [h1 heap1
         h2 heap2]
    (if (empty? h2)
        h1
      (recur (insert h1 (first h2)) (vec (rest h2))))))

(defn find-max [heap]
  (first heap))
;verify
