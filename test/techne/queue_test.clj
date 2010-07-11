(ns techne.queue_test
  (:require [techne.queue :as queue])
  (:use [clojure.test]
        [techne.utils]))

(deftest new-with-no-args-is-empty
  (is (empty? (queue/new)))
  (is (not-empty (queue/new [:a :b :c])))
  (is (= '(:a :b :c) (queue/new :a :b :c))))

(deftest test-front
  (is (= :c (queue/front (queue/new :a :b :c)))))
