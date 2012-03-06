(ns techne.algos.merge-sort-test
  (:use [clojure.test]
       [techne.algos.merge-sort]))


(deftest test-it
  (is (= [] (merge-sort [])))
  (is (= [0] (merge-sort [0])))
  (is (= [1 2 3] (merge-sort [2 1 3])))
  (is (= [1 2 3 4 4 5 6] (merge-sort [2 6 1 4 3 5 4]))))
