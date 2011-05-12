(ns techne.vectors-test
  (:use [techne.vectors] :reload-all)
  (:use [clojure.test]
        [techne.test-utils]))


(deftest test-swap
  (is (= [2 1 3] (swap [1 2 3] 0 1)))
  (is (= [2 1 3] (swap [1 2 3] 1 0)))
  (is (= [1 3 2] (swap [1 2 3] 1 2)))
  (is (= [1 3 2] (swap [1 2 3] 2 1)))
  (is (= [3 2 1] (swap [1 2 3] 0 2)))
  (is (= [3 2 1] (swap [1 2 3] 2 0))))

