(ns techne.algos.quick-sort
  (:use [techne.vectors]))


; Split a vector into two parts:
; less -> the elements less than the element at index p
; gtr  -> the elements greater than the element at index p
; The element at index p is not included in either list so that
; the recursion is guarenteed to happen on smaller sublists.
(defn bifurcate [vectr p]
  (loop [less []
         gtr []
         i 0]
    (cond
      (= i (count vectr))
        [less gtr]
      (= i p) ; we do not include the element at p in either lists.
        (recur less gtr (inc i))
      (< (vectr i) (vectr p))
        (recur (conj less (vectr i)) gtr (inc i))
      true
        (recur less (conj gtr (vectr i)) (inc i))
        )))

; we choose the middle element of the vector to be the partition
(defn part [vectr]
  (quot (count vectr) 2))

; Pivot a vector around an index.  Split the vector into two sub-vectors of
; elements less than the pivot point and greater than the pivor point.  Sort
; the sub-vectors and re-combine them.
(defn qsort [vectr]
  (if (>= 1 (count vectr))
      vectr
      (let [p (part vectr)
            less+gtr (bifurcate vectr p)]
        (vconcat
          (conj (qsort (less+gtr 0)) (vectr p))
          (qsort (less+gtr 1))))))
