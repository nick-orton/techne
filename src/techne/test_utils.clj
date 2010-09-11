(ns techne.test-utils
 (:use [clojure.test]))

(defmacro isnt [thing]
  `(is (not ~thing)))

(defmacro is= [one two]
  `(is (= ~one ~two)))

(defmacro isnt= [one two]
  `(isnt (= ~one ~two)))

