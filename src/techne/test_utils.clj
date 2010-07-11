(ns techne.test-utils
 (:use [clojure.test]))

(defmacro isnt [thing]
  `(is (not ~thing)))


