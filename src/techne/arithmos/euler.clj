(ns techne.arithmos.euler)

(defn gcd [int1 int2]
  (loop [a int1
         b int2]
    (if (= 0 b)
      a
      (recur b (mod a b)))))

(defn lcd [int1 int2]
  (/ (* int1 int2) (gcd int1 int2)))
