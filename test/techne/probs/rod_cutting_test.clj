(ns techne.probs.rod-cutting-test
  (:use [clojure.test]
       [techne.probs.rod-cutting]))

(def p [0 1 5 8 9 10 17 17 20 24 30])

(deftest test-rod-cutting
  (is (= '(10 10 3 2) (first (cut-rod p 25))))
  (is (= 73 (second (cut-rod p 25)))))

