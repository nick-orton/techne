(ns techne.data-structs.directed-graph-test
  (:use [clojure.test]
        [techne.data-structs.directed-graph]))

(def simple-graph (graph (list (struct Vertex :a #{} #{}))))

(deftest test-has-vertex
  (is (has-vertex? simple-graph :a ))
  (is (not (has-vertex? (graph (list (list :v []))) :a ))))

(deftest test-insert-vertex
  (let [g (graph '())]
    (is (has-vertex? (insert-vertex g :a) :a))))

(deftest test-insert-edge
  (is (:z (tos (insert-edge simple-graph :a :z) :a))))

