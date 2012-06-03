(ns techne.data-structs.directed-graph)


(defprotocol Graph
  (has-vertex?      [graph vertex])
  (tos              [graph vertex])
  (froms            [graph vertex])
  (as-list          [graph])
  (insert-vertex    [graph vertex])
  (remove-vertex    [graph vertex])
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

    (froms [_ vertex]
      (:froms (get-vertex vertices-list vertex)))

    (as-list [_] vertices-list)

    (insert-vertex [g vertex]
      (if (not (has-vertex? g vertex))
        (graph (cons (struct Vertex vertex #{} #{}) vertices-list))))

    (insert-edge [self from-name to-name]
      (let [self (add-if-missing self from-name)
            self (add-if-missing self to-name)]
        (let [from (get-vertex (as-list self) from-name)
              from* (struct Vertex from-name (conj (:tos from) to-name) (:froms from))
              to (get-vertex (as-list self) to-name)
              to* (struct Vertex to-name (:tos to) (conj (:froms to) from-name))
              vertices-list* (conj (remove #(or (= to %) (= from %)) (as-list self)) from* to* )]
          (graph vertices-list*))))

    (remove-vertex [_ vertex]
      (let [self (graph (remove (matches?-fn vertex) vertices-list))]
        (loop [old-list (as-list self)
               vertices-list* '()]
          (if (empty? old-list)
            (graph vertices-list*)
            (let [to-purge (first old-list)]
              (recur
                (rest old-list)
                (cons (struct Vertex (:name to-purge)
                                     (remove #(= vertex %) (:tos to-purge))
                                     (remove #(= vertex %) (:froms to-purge))) vertices-list*)))))))

    (topological-sort [self]
      (loop [self self
             sorted '()]
        (if (empty? (as-list self))
          (reverse sorted)
          (let [v-list (as-list self)
                least-froms-amt (apply min (map #(count (:froms %)) v-list))
                least-froms (first (filter #(= least-froms-amt (count (:froms %))) v-list))
                least-name (:name least-froms)]
            (recur (remove-vertex self least-name) (cons least-name sorted))))))

    (shortest-path [self from to]

       (let [dists (loop [stack (seq (tos self from))
                          distances (reduce #(assoc %1 %2 1) {} stack) ]
                     (if (empty? stack)
                       distances
                       (let [top (first stack)
                             distance (get distances top)
                             nexts (tos self top)
                             to-stack (filter
                                        #(< distance (get distances
                                                          % (inc distance)))
                                        nexts)
                             distances* (reduce #(assoc %1 %2 (inc distance))
                                                distances to-stack)]
                         (recur (concat (rest stack) to-stack) distances*))))]
         (loop [path (list to)]
           (let [top (first path)
                 candidates (froms self top)
                 top-distance (get dists top)
                 least-distance (apply min (map #(get dists % top-distance)
                                                candidates))
                 least-froms (first (filter #(= least-distance
                                                (get dists % top-distance))
                                            candidates))]
             (if (= least-froms from)
               (cons least-froms path)
               (recur (cons least-froms path)))))))
    ))
