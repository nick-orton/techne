(ns techne.utils-test
  (:use [techne.utils] :reload-all)
  (:use [clojure.test]
        [techne.test-utils]))

(deftest test-not-equals
  (is (!= 1 2))
  (isnt (!= 1 1 )))

(deftest not-nil
  (is (!nil? :a))
  (isnt (!nil? nil)))

(deftest insert-in-2nd-place
  (is (= [nil 1] (insert-2nd 1 [])))
  (is (= [0 1] (insert-2nd 1 [0])))
  (is (= [0 1 2] (insert-2nd 1 [0 2])))
  (is (= [0 1 2 3 ] (insert-2nd 1 [0 2 3]))))

(deftest concat-strings
  (is (= "abc" (+strs ["ab" "c"]))))

