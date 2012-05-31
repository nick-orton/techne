(ns techne.geometria.elements-test
  (:use [clojure.test]
        [techne.geometria.elements]))

(def seg
  (let [p1 (point 1 2)
        p2 (point 2 5)]
    (line-segment p1 p2)))

(deftest test-line-segment-slope
  (is (= 3 (line-slope seg))))

(deftest test-segment->line
  (let [line (segment->line seg)]
    (is (= 3  (slope line)))
    (is (= -1 (offset line)))))

(deftest test-length
  (let [seg345 (line-segment (point 0 3) (point 4 0))]
    (is (= 5 (length seg345)))))
