(ns techne.algos.merge-sort
  (:use [techne.vectors]))

; We can merge two sorted vectors together in linear time.  Take the smaller
; head of the two lists and conj it on the result.  Repeat unit one of the
; vectors is empty.
(defn merge [sorted-vector-1 sorted-vector-2]
  (loop [v1 sorted-vector-1
         v2 sorted-vector-2
         acc []]
     (cond
       (empty? v1)
         (vconcat acc v2)
       (empty? v2)
         (vconcat acc v1)
       (< (first v1) (first v2))
         (recur (vrest v1) v2 (conj acc (first v1)))
       true
         (recur v1 (vrest v2) (conj acc (first v2))))))

; Sort by splitting the vector in half, sorting each half, and merging the
; sorted halfs together in linear time.
; A vector of size 1 or 0 is sorted.
(defn merge-sort [vectr]
  (cond
    (>= 1 (count vectr))
      vectr
    true
     (let [midpoint (quot (count vectr) 2)]
      (merge (merge-sort (vtake midpoint vectr))
             (merge-sort (vdrop midpoint vectr))))))
