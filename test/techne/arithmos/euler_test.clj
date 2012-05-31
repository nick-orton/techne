(ns techne.arithmos.euler-test
  (:use [clojure.test]
        [techne.arithmos.euler]))

(deftest test-gcd
  (is (= 1 (gcd 1 2)))
  (is (= 1 (gcd 2 1)))
  (is (= 5 (gcd 10 35))))

(deftest test-lcd
  (is (= 4 (lcd 2 4))))

(deftest test-lcd
  (is (= 70 (lcd 10 35)))
  (is (= 4 (lcd 2 4))))
