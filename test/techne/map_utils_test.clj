(ns techne.map-utils-test
  (:use [techne.map-utils] :reload-all)
  (:use [clojure.test]
        [techne.test-utils]))

(defn always-true
  [x] (= x x))

(defn always-false
  [x] (not (= x x)))



(deftest test-keep-if
  (is (= {:a 2} (keep-if :a always-true 2 {})))
  (is (= {} (keep-if :a always-false 2 {:a 2})))
  (is (= {:a 2} (keep-if :a always-true {:a 2})))
  (is (= {} (keep-if :a always-false {:a 2}))))

