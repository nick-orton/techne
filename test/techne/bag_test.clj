(ns techne.bag-test
  (:require [techne.bag :as bag])
  (:use [clojure.test]
        [techne.test-utils])
  )

(def empty-bag (bag/create))
(def three-as (bag/create {:a 3}))

(deftest test-put-occurences
  (assert-bag-contents {:a 2} (bag/put-n empty-bag :a 2))
  (assert-bag-contents {:a 5} (bag/put-n three-as :a 2)))

(deftest test-put
  (assert-bag-contents {:a 1} (bag/put empty-bag :a))
  (assert-bag-contents {:a 4} (bag/put three-as :a )))

(deftest test-remove-occurances
  (is (assert-bag-contents {} (bag/pluck-n empty-bag :a 2)))
  (is (assert-bag-contents {:b 2} (bag/pluck-n (bag/create {:b 2}) :a 2)))
  (is (assert-bag-contents {:b 2} (bag/pluck-n (bag/create {:a 1 :b 2}) :a 2)))
  (is (assert-bag-contents {:a 1} (bag/pluck-n three-as :a 2)))
  (is (assert-bag-contents {:a 1 :b 2} (bag/pluck-n (bag/create {:a 3 :b 2}) :a 2))))

(deftest test-remove
  (is (assert-bag-contents {} (bag/pluck empty-bag :a)))
  (is (assert-bag-contents  {:a 2} (bag/pluck three-as :a))))

(deftest test-count
  (is (= 3 (bag/count three-as :a)))
  (is (= 0 (bag/count empty-bag :a))))

(deftest test-seq->bag
  (is (assert-bag-contents {} (bag/seq->bag [])))
  (is (assert-bag-contents  {:a 2 :b 1} (bag/seq->bag [:a :b :a]))))


