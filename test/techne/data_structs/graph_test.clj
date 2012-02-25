(ns techne.data-structs.graph-test
  (:use [techne.data-structs.graph]
        [clojure.test]))


(def grph {:a [:b :c]
           :b [:a :c :d :e]
           :c [:a :b]
           :d [:b :e]
           :e [:b :d :f]
           :f [:e] })

;   c
;  /|
; a-b-e-f
;   |/
;   d
;

(deftest test-shortest-path
  (is (= 0 (shortest-path grph :a :a)))
  (is (= 1 (shortest-path grph :a :b)))
  (is (= 1 (shortest-path grph :a :c)))
  (is (= 2 (shortest-path grph :a :d)))
  (is (= 2 (shortest-path grph :a :e)))
  (is (= 3 (shortest-path grph :a :f)))
  (is (= 1 (shortest-path grph :b :a)))
  (is (= 1 (shortest-path grph :b :c)))
  (is (= 1 (shortest-path grph :b :d)))
  (is (= 1 (shortest-path grph :b :e)))
  (is (= 2 (shortest-path grph :b :f)))
  (is (= 1 (shortest-path grph :c :a)))
  (is (= 1 (shortest-path grph :c :b)))
  (is (= 2 (shortest-path grph :c :d)))
  (is (= 2 (shortest-path grph :c :e)))
  (is (= 3 (shortest-path grph :c :f)))
  (is (= 2 (shortest-path grph :d :a)))
  (is (= 1 (shortest-path grph :d :b)))
  (is (= 2 (shortest-path grph :d :c)))
  (is (= 1 (shortest-path grph :d :e)))
  (is (= 2 (shortest-path grph :d :f)))
  (is (= 2 (shortest-path grph :e :a)))
  (is (= 1 (shortest-path grph :e :b)))
  (is (= 2 (shortest-path grph :e :c)))
  (is (= 1 (shortest-path grph :e :d)))
  (is (= 1 (shortest-path grph :e :f)))
  (is (= 3 (shortest-path grph :f :a)))
  (is (= 2 (shortest-path grph :f :b)))
  (is (= 3 (shortest-path grph :f :c)))
  (is (= 2 (shortest-path grph :f :d)))
  (is (= 1 (shortest-path grph :f :e)))
         )
