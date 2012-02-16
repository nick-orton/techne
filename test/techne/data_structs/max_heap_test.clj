(ns techne.data-structs.max-heap-test
  (:use [techne.data-structs.max-heap])
  (:use [clojure.test]))


(deftest test-insert
  (is (= [1] (insert [] 1)))
  (is (= [1 0] (insert [1] 0)))
  (is (= [1 0] (insert [0] 1)))
  (is (= [2 0 1] (insert [2 0] 1)))
  (is (= [2 0 1] (insert [1 0] 2)))
  (is (= [2 0 1] (insert [2 0] 1)))
  (is (= [3 2 0 1] (insert [3 1 0] 2))))

(deftest test-delete
  (is (= [] (delete [0])))
  (is (= [0] (delete [1 0])))
  (is (= [1 0] (delete [2 0 1])))
  (is (= [1 0] (delete [2 1 0])))
  (is (= [5 3 4 1 0] (delete [6 3 5 1 0 4]))))

(deftest test-merge-h
  (is (= [0] (merge-h [] [0])))
  (is (= [0 0] (merge-h [0] [0])))
  (is (= [1 0] (merge-h [1] [0])))
  (is (= [2 1 0] (merge-h [2 1] [0])))
  (is (= [2 1 2 1 0] (merge-h [2 1] [2 1 0])))
  (is (= [2 0 1] (merge-h [0] [2 1]))))

(deftest test-find-max
  (is (= 0 (find-max [0])))
  (is (= 1 (find-max [1 0]))))

(deftest test-verify)

