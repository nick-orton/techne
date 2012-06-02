(ns techne.data-structs.directed-graph-test
  (:use [clojure.test]
        [techne.data-structs.directed-graph]))

(def simple-graph (graph (list (struct Vertex :a #{} #{}))))
(def vert3g (graph (list (struct Vertex :a #{:b :c} #{})
                         (struct Vertex :b #{:c} #{:a})
                         (struct Vertex :c #{} #{:a :b}))))

(deftest test-has-vertex
  (is (has-vertex? simple-graph :a ))
  (is (not (has-vertex? (graph (list (list :v []))) :a ))))

(deftest test-insert-vertex
  (let [g (graph '())]
    (is (has-vertex? (insert-vertex g :a) :a))))

(deftest test-insert-edge
  (is (:z (tos (insert-edge simple-graph :a :z) :a)))
  (is (:a (froms (insert-edge simple-graph :a :z) :z))))

(deftest test-remove-vertex
  (is (not (has-vertex? (remove-vertex simple-graph :a) :a))))


