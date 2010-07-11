(ns techne.queue)


(defn new 
  ([] '())
  ([& args] args))

(defn front
  [queue]
  (last queue))


