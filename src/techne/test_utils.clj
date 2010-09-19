(ns techne.test-utils
 (:use [clojure.test])
 (:require [techne.bag :as bag]))

(defmacro isnt [thing]
  `(is (not ~thing)))

(defmacro is= [one two]
  `(is (= ~one ~two)))

(defmacro isnt= [one two]
  `(isnt (= ~one ~two)))

(defn assert-bag-contents [contents bag]
  (is (= (bag/inspect bag) contents)))


