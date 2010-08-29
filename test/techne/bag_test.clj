(ns techne.bag-test
  (:use [techne.bag] :reload-all)
  (:use [clojure.test]
        [techne.test-utils]))

(deftest test-put-occurences
  (is (= {:a 2} (put-occurances {} :a 2)))
  (is (= {:a 5} (put-occurances {:a 3} :a 2))
      ))

