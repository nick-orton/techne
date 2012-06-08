(ns techne.data-structs.counting-bloom-filter)
;TODO test
;TODO add delete method
;TODO randomize the ks each time a cbf is created

(defprotocol Counting-Bloom-Fileter
  (cardinality [cbf key])
  (get-vector  [cbf])
  (insert      [cbf key]) )

(def ks
  (loop [ks []
         ctr 10]
    (if (> ctr 0)
      (recur (conj ks (Math/random)) (dec ctr))
      ks)))

(defn mult-hash-val [k c size]
  (let [whole (* k c)
        frac-part (- whole (int whole))]
    (int (* size frac-part))))

(defn insert-into [ key]
  (fn [cbf* c]
    (let [hval (mult-hash-val (hash key) c (count cbf*))]
      (assoc cbf* hval (inc (cbf* hval))))))

(defn counting-bloom-filter [cbf]
    (reify Counting-Bloom-Fileter

      (insert [_ key]
        (counting-bloom-filter
          (reduce (insert-into key) cbf ks)))

      (get-vector [_] cbf)

      (cardinality [_ key]
         (let [counts
                (map (fn [k]
                       (cbf (mult-hash-val (hash key) k (count cbf)))) ks)]
           (apply min counts)))
      ))

(defn new-counting-bloom-filter [size]
  (counting-bloom-filter (vec (repeat size 0))))


