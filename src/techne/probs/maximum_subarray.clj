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

(defn kadane [vectr]
  (loop [vectr vectr
         max-ending-here 0
         max-so-far 0 ]
    (if (empty? vectr)
      max-so-far
      (let [current (first vectr)]
        (recur (rest vectr)
               (max current max-ending-here current)
               (max max-so-far max-ending-here))))))


