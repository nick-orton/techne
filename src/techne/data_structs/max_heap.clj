; Functions for querying and manipulating a Max-Heap.  A max heap is a binary tree where the parent is
; always larger than its children.

(ns techne.data-structs.max-heap
  (:use [techne.vectors :only [swap]]
        [techne.data-structs.binary-tree :only [parent-index left-index right-index]]))

; insert the new value at the bottom of the heap.  Bubble it up until the heap is valid
(defn insert [heap element]
  (loop [h (conj heap element)
         i (count heap)]
    (if (= 0 i)
        h
        (let [parent-i (int (parent-index i))]
             (if (> (h parent-i) (h i))
                 h
                 (recur (swap h parent-i i) parent-i))))))

; a heap is in violation if the child is in the bounds of the heap and yet larger than its parent
(defn- violation? [heap i child]
  (and (< child (count heap)) (> (heap child) (heap i))))

; swap the bottom element with the top of the heap and then bubble it down until the heap is valid
(defn delete [heap]
  (loop [h (pop (swap heap 0 (dec (count heap))))
         i 0]
    (let [left-i (left-index i)
          right-i (right-index i)]
      (cond (violation? h i left-i)
              (recur (swap h left-i i) left-i)
            (violation? h i right-i)
              (recur (swap h right-i i) right-i)
            true
             h))))

; to merge two heaps, repeatedly insert values from the one into the other
(defn merge-h [heap1 heap2]
  (loop [h1 heap1
         h2 heap2]
    (if (empty? h2)
        h1
      (recur (insert h1 (first h2)) (vec (rest h2))))))

; The top element of a valid max-heap is the max.
(defn find-max [heap]
  (first heap))

; a vector is a max-heap if none of it's elements are in violation
; note that only the top half of the heap can possibly be in violation
(defn heap? [vectr]
  (loop [i 0]
    (cond (= i (quot (count vectr) 2))
            true
          (violation? vectr i (left-index i))
            false
          (violation? vectr i (right-index i))
            false
          true
            (recur (inc i)))))
