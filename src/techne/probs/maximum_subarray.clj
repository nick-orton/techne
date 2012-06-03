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
         index -1
         max-ending-here 0
         max-so-far 0
         start (first vectr)
         end   (first vectr)]
    (if (empty? vectr)
      [max-so-far start end]
      (let [current (first vectr)
            max-ending-here* (max current max-ending-here current)
            max-so-far*      (max max-so-far max-ending-here)]
        (recur (rest vectr)
               (inc index)
               max-ending-here*
               max-so-far*
               (if (not (= max-ending-here* max-ending-here)) index start)
               (if (not (= max-so-far* max-so-far)) index end)
               )))))


