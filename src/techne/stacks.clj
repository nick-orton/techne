(ns techne.stacks)


(defn new 
  ([] '())
  ([& args] args))

(defn push
  [top stack]
  (cons top stack))

; pop and peek are supported by clojure
