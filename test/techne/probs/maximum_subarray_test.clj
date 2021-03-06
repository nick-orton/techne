(ns techne.probs.maximum-subarray-test
  (:use [clojure.test]
        [techne.probs.maximum-subarray]))

(def prices [0 13 -3 -25 20 -3 -16 -23 18 20 -7 12 -5 -22 15 -4 7])

(deftest test-prices-to-changes
  (is (= prices (prices-to-changes
         [100 113 110 85 105 102 86 63 81 101 94 106 101 79 94 90 97]))))

(deftest test-maximum-subarray
    (is (= [20 3 4] (kadane prices))))
