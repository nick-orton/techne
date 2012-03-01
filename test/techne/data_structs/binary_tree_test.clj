(ns techne.data-structs.binary-tree-test
  (:use [clojure.test]
        [techne.data-structs.binary-tree]))

(deftest test-parent-index
  (is (= 0 (parent-index 1)))
  (is (= 0 (parent-index 2)))
  (is (= 1 (parent-index 3))))

(deftest test-left-index
  (is (= 1 (left-index 0)))
  (is (= 3 (left-index 1))))

(deftest test-right-index
  (is (= 2 (right-index 0)))
  (is (= 4 (right-index 1))))


(deftest test-has-left-child
  (is (has-left-child [:a :b] 0))
  (is (not (has-left-child [:a nil :b] 0)))
  (is (not (has-left-child [:a ] 0)))
  (is (not (has-left-child [:a :b :c] 1))))

(deftest test-has-right-child
  (is (has-right-child [:a :b :c] 0))
  (is (not (has-right-child [:a :b] 0)))
  (is (not (has-right-child [:a ] 0)))
  (is (not (has-right-child [:a :b :c] 1))))

(deftest test-leftmost-child
  (is 1 (leftmost-child [:a :b :c] 0))
  (is 0 (leftmost-child [:a] 0))
  (is 0 (leftmost-child [:a nil :c] 0))
  (is 1 (leftmost-child [:a :b :c nil :e] 0))
  (is 3 (leftmost-child [:a :b :c :d :e] 0)))
