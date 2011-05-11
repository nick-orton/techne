(ns techne.utils
  "some generic helper functions"
  (:require [clojure.contrib.str-utils2 :as str]))


(defmacro def-bang-form
  "takes a predicate as an argument and defines its inverse identified by a 
   preceding '!'"
  [fun]
  `(defn  ~(symbol (str "!" fun)) 
     [& as#] 
     (not (apply ~fun as#))))

(def-bang-form =)
(def-bang-form nil?)

(defn insert-2nd 
  "insert a value behind the head of a sequence"
  [x xs]
  (cons (first xs) (cons x (rest xs))))

(defn +strs
  "concatinate a sequence of strings together"
  [strings]
  (str/join "" strings))

(defn swap-if
  {:deprecated "0.6.1"}
  [pred token element]
  (if (pred element) token element))

(defn swap-if-eq 
  {:deprecated "0.6.1"}
  [target token element]
  (swap-if #(= target %) token element))


