(ns techne.utils
  "some generic helper functions"
  (:require [clojure.contrib.str-utils2 :as str]))


(defmacro def-bang-form
  [fun]
  `(defn  ~(symbol (str "!" fun)) 
     [& as#] 
     (not (apply ~fun as#))))

(def-bang-form =)
(def-bang-form nil?)

(defn insert-2nd 
  [x xs]
  (cons (first xs) (cons x (rest xs))))

(defn +strs
  [strings]
  (str/join "" strings))

(defn swap-if
  [pred token element]
  (if (pred element) token element))

(defn swap-if-eq 
  [target token element]
  (swap-if #(= target %) token element))


