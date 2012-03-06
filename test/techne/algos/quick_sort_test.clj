(ns techne.algos.quick-sort-test
  (:use [clojure.test]
       [techne.algos.quick-sort]))


(deftest test-it
  (is (= [] (qsort [])))
  (is (= [0] (qsort [0])))
  (is (= [1 2 3] (qsort [2 1 3])))
  (is (= [1 2 3 4 4 5 6] (qsort [2 6 1 4 3 5 4]))))
