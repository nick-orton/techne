(ns techne.utils
  "some generic helper functions"
  (:require [clojure.contrib.str-utils2 :as str]))

(defn !=
  [a b]
  (not (= a b)))

(defn !nil?
  [o]
  (not (nil? o)))

(defn insert-2nd 
  [x xs]
  (cons (first xs) (cons x (rest xs))))

(defn +strs
  [strings]
  (str/join "" strings))


