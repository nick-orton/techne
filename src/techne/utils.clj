(ns techne.utils
  "some generic helper functions"
  )

(defmacro def-bang-form
  "takes a predicate as an argument and defines its inverse identified by a
   preceding '!'

       user=> (def-bang-form =)
       #'user/!=
       user=> (!= 0 1)
       true
       user=>
  "
  [fun]
  `(defn ~(symbol (str "!" fun))
     [& as#]
     (not (apply ~fun as#))))

(def-bang-form =)
(def-bang-form nil?)

(defn insert-2nd
  "insert a value behind the head of a sequence"
  [x xs]
  (cons (first xs) (cons x (rest xs))))
