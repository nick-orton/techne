(ns techne.map-utils-test
  (:use [techne.map-utils] :reload-all)
  (:use [clojure.test]
        [techne.test-utils]))

(deftest test-put-occurences
  (is (= {:a 2} (put-occurances {} :a 2)))
  (is (= {:a 5} (put-occurances {:a 3} :a 2))
      ))

(deftest test-put
  (is (= {:a 1} (put {} :a)))
  (is (= {:a 4} (put {:a 3} :a))))

(deftest test-remove-occurances
  (is (= {} (remove-occurances {} :a 2)))
  (is (= {:b 2} (remove-occurances {:b 2} :a 2)))
  (is (= {:b 2} (remove-occurances {:a 1 :b 2} :a 2)))
  (is (= {:a 1} (remove-occurances {:a 3} :a 2)))
  (is (= {:a 1 :b 2} (remove-occurances {:a 3 :b 2} :a 2))))

(deftest test-remove
  (is (= {} (remove {} :a)))
  (is (= {:a 2} (remove {:a 3} :a))))


