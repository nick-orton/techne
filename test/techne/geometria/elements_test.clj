(ns techne.geometria.elements-test
  (:use [clojure.test]
        [techne.geometria.elements]))

(def p1 (point 1 2))

(def p2 (point 2 5))

(def seg (line-segment p1 p2))

(deftest test-line-segment-slope
  (is (= 3 (line-slope seg))))

(deftest test-segment->line
  (let [line (segment->line seg)]
    (is (= 3  (slope line)))
    (is (= -1 (offset line)))))

(deftest test-length
  (let [seg345 (line-segment (point 0 3) (point 4 0))]
    (is (= 5 (length seg345)))))


(deftest test-points
  (is (= p1 (left-point seg)))
  (is (= p2 (right-point seg)))
  (is (= p1 (low-point seg)))
  (is (= p2 (high-point seg))))
