(ns techne.data-structs.directed-graph)


(defprotocol Graph
  (has-vertex?      [graph vertex])
  (tos              [graph vertex])
  (insert-vertex    [graph vertex])
  (insert-edge      [graph vertex vertex])
  (topological-sort [graph])
  (shortest-path    [graph vertex vertex]))

(defn matches?-fn [name]
  (fn [vertex] (= name (:name vertex))))

(defn- get-vertex [vertices-list vertex]
  (first (filter (matches?-fn vertex) vertices-list)))


(defstruct Vertex :name :tos :froms)

(defn- add-if-missing [graph vertex]
  (if (not (has-vertex? graph vertex))
    (insert-vertex graph vertex)
    graph))

; vertices-list takes the form '( '(vertex-name #{adjacent-vertexes}))
(defn graph [vertices-list]
  (reify Graph

    (has-vertex? [_ vertex]
      (some (matches?-fn vertex) vertices-list))

    (tos [_ vertex]
      (:tos (get-vertex vertices-list vertex)))


    (insert-vertex [g vertex]
      (if (not (has-vertex? g vertex))
        (graph (cons (struct Vertex vertex #{} #{}) vertices-list))))

    (insert-edge [self from-name to-name]
      (let [self (add-if-missing self from-name)
            self (add-if-missing self to-name)]
        (let [from (get-vertex vertices-list from-name)
              from* (struct Vertex from-name (conj (:tos from) to-name) (:froms from))
              to (get-vertex vertices-list to-name)
              to* (struct Vertex to-name (:tos to) (conj (:froms to) from-name))
              vertices-list* (conj (remove #(or (= to %) (= from %)) vertices-list) from* to* )]
          (graph vertices-list*))))
  ))

