; This is an example of a dynamic programming problem.
; A company has a price list p of prices for rods of various lengths
;
; For example:
;   length 1 2 3 4 5  6  7  8  9  10
;   price  1 5 8 9 10 17 17 20 24 30
;
; The problem is for a rod of length n, what cuts should be made to the rod to
; maximise the price it can be sold for.
;
; For example: It is more profitable to cut a rod of length 7 into a 6 and 1
; which can be sold for 18 rather than keeping it whole and selling it for 17.
;

(ns techne.probs.rod-cutting)

(defn- price-or-0 [price-list i]
  (if (<= (count price-list) i)
      0
      (price-list i)))

(defn- cut-rod-solutions [price-list size]
  (loop [ costs [0]
          first-cuts [0]
          j 1]
    (let [cost+cuts (loop [cost 0
                           first-cuts first-cuts
                           i 1]
                      (if (< j i)
                        (list cost first-cuts)
                        (let [price (price-or-0 price-list i)
                              cost* (max (+ price (costs (- j i))) cost)
                              first-cuts* (if (< cost cost*) (assoc first-cuts j i) first-cuts)]
                          (recur cost* first-cuts* (inc i)))))
          cost (first cost+cuts)
          cuts (second cost+cuts)
          costs* (assoc costs j cost)]
      (if (= size j)
        (list costs* cuts)
        (recur costs* cuts (inc j))))))


(defn cut-rod [price-list size]
  (let [costs+cuts (cut-rod-solutions price-list size)
        costs (first costs+cuts)
        cuts (second costs+cuts)]
    (loop [i size
           my-cuts '()]
      (if (> i 0)
          (recur (- i (cuts i)) (cons (cuts i) my-cuts))
          (list my-cuts (costs size))))))
