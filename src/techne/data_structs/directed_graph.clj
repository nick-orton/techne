(ns techne.data-structs.directed-graph)


(defprotocol Graph
  (has-vertex?      [graph vertex])
  (adjacents        [graph vertex])
  (insert-vertex    [graph vertex])
  (insert-edge      [graph vertex vertex])
  (topological-sort [graph])
  (shortest-path    [graph vertex vertex]))

(defn matches?-fn [name]
  (fn [vertex] (= name (:name vertex))))

(defn- get-vertex [vertices-list vertex]
  (first (filter (matches?-fn vertex) vertices-list)))


(defstruct Vertex :name :tos :froms)

; vertices-list takes the form '( '(vertex-name #{adjacent-vertexes}))
(defn graph [vertices-list]
  (reify Graph

    (has-vertex? [_ vertex]
      (some (matches?-fn vertex) vertices-list))

    (adjacents [_ vertex]
      (:tos (get-vertex vertices-list vertex)))

    (insert-vertex [g vertex]
      (if (not (has-vertex? g vertex))
        (graph (cons (struct Vertex vertex #{} #{}) vertices-list))))

    (insert-edge [_ vertex vertex2]
      (let [v (get-vertex vertices-list vertex)
            v* (struct Vertex (:name v) (conj (:tos v) vertex2))
            vertices-list* (cons v* (remove #(= v %) vertices-list))]
        (graph vertices-list*)))
  ))

