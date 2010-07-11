(ns techne.queue)


(defn new 
  ([] '())
  ([& args] args))

(defn front
  [queue]
  (first queue))

(defn push
  [item queue]
  (concat queue '(item)))


