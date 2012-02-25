(ns techne.data-structs.graph
  (:use [techne.vectors]))

; graph is represented by an adjacency matrix
; {:key [:adjacent-keys]}


(defn set-distances [distances distance nodes]
  (reduce #(assoc %1 %2 distance) distances nodes))

(defn init-distances [graph source]
  (assoc (set-distances {} -1 (keys graph)) source 0))

(defn shortest-path [graph source target]
  (loop [distances (init-distances graph source)
         stack [source]]
    (let [node (first stack)
          distance (get distances node)]
      (cond
        (nil? node)
          -1
        (= node target)
          distance
        true
          (let [adjacents (filter #(= -1 (get distances %))
                                      (get graph node))]
            (recur (set-distances distances (inc distance) adjacents)
                   (vconcat (vrest stack) adjacents)))))))
