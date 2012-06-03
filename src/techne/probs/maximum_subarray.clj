(ns techne.probs.maximum-subarray)

(defn prices-to-changes [price-vector]
  (loop [prices (rest price-vector)
         last-price (first price-vector)
         changes [0]]
    (if (empty? prices)
      changes
      (recur (rest prices)
             (first prices)
             (conj changes (- (first prices) last-price ))))))

