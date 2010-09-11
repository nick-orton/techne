(ns techne.bag-test
  (:use [techne.bag] :reload-all)
  (:use [clojure.test]
        [techne.test-utils])
  )

(def empty-bag (new-bag))
(def three-as (new-bag {:a 3}))
;

(defn assert-bag-contents [contents bag]
  (is (= (:state bag) contents)))

(deftest test-put-occurences
  (assert-bag-contents {:a 2} (put-n empty-bag :a 2))
  (assert-bag-contents {:a 5} (put-n three-as :a 2)))

(deftest test-put
  (assert-bag-contents {:a 1} (put empty-bag :a))
  (assert-bag-contents {:a 4} (put three-as :a )))

(deftest test-remove-occurances
  (is (assert-bag-contents {} (pluck-n empty-bag :a 2)))
  (is (assert-bag-contents {:b 2} (pluck-n (new-bag {:b 2}) :a 2)))
  (is (assert-bag-contents {:b 2} (pluck-n (new-bag {:a 1 :b 2}) :a 2)))
  (is (assert-bag-contents {:a 1} (pluck-n (new-bag {:a 3}) :a 2)))
  (is (assert-bag-contents {:a 1 :b 2} (pluck-n (new-bag {:a 3 :b 2}) :a 2))))

(deftest test-remove
  (is (assert-bag-contents {} (pluck empty-bag :a)))
  (is (assert-bag-contents  {:a 2} (pluck three-as :a))))


(deftest test-seq->bag
  (is (assert-bag-contents {} (seq->bag [])))
  (is (assert-bag-contents  {:a 2 :b 1} (seq->bag [:a :b :a]))))


