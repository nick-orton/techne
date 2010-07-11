(ns techne.stacks-test

(:require [techne.stacks :as stack])
(:use [clojure.test]
      [techne.utils]))

(deftest new-with-no-args-is-empty
  (is (empty? (stack/new)))
  (is (not-empty (stack/new [:a :b :c])))
  (is (= '(:a :b :c) (stack/new :a :b :c))))

(deftest test-push
  (is (= '(:a) (stack/push :a ()))))
