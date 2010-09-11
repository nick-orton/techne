(ns techne.bag-test
  (:use [techne.bag] :reload-all)
  (:use [clojure.test]
        [techne.test-utils])
  )

(def empty-bag (new-bag))
(def three-as (new-bag {:a 3}))
;

(defn assert-bag-contents [bag contents]
  (is (= contents (inspect bag))))

(deftest test-put-occurences
  (assert-bag-contents (put-n empty-bag :a 2) {:a 2})
  (assert-bag-contents (put-n three-as :a 2) {:a 5} ))

;(deftest test-put
;  (assert-bag-contents (put empty-bag :a) {:a 1})
;  (assert-bag-contents (put three-as :a ) {:a 4} ))

(deftest test-remove-occurances
  (is (= {} (remove-occurances {} :a 2)))
  (is (= {:b 2} (remove-occurances {:b 2} :a 2)))
  (is (= {:b 2} (remove-occurances {:a 1 :b 2} :a 2)))
  (is (= {:a 1} (remove-occurances {:a 3} :a 2)))
  (is (= {:a 1 :b 2} (remove-occurances {:a 3 :b 2} :a 2))))

(deftest test-remove
  (is (= {} (remove {} :a)))
  (is (= {:a 2} (remove {:a 3} :a))))


