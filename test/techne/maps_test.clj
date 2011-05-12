(ns techne.maps-test
  (:use [techne.maps] :reload-all)
  (:use [clojure.test]
        [techne.utils]
        [techne.test-utils]))

(defn always-true
  [x] (= x x))

(defn always-false
  [x] (!= x x))

(deftest test-keep-key-if
  (is (= {:a 2} (keep-key-if {:a 2} always-true :a )))
  (is (= {} (keep-key-if {:a 2} always-false :a))))

(deftest test-remove-key-with
  (is (= {:a 2} (remove-key-if {:a 2} always-false :a)))
  (is (= {} (remove-key-if {:a 2} always-true :a ))))

