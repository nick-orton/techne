(ns techne.probs.paren-matching)

(defn matches-parens? [paren-string]
  (loop [cs paren-string
         ctr 0 ]
    (cond
      (> 0 ctr)
        false
      (nil? (first cs))
        (= 0 ctr)
      (= \( (first cs))
        (recur (rest cs) (inc ctr))
      (= \) (first cs))
        (recur (rest cs) (dec ctr))
      true
        (recur (rest cs) ctr))))
