(ns techne.test-utils
 (:use [clojure.test])
 (:use [techne.data-structs.bag :only [inspect]]))

(defmacro isnt [thing]
  `(is (not ~thing)))

(defmacro is= [one two]
  `(is (= ~one ~two)))

(defmacro isnt= [one two]
  `(isnt (= ~one ~two)))

(defn assert-bag-contents [contents bag]
  (is (= (inspect bag) contents)))


