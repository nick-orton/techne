(ns techne.data-structs.directed-graph-test
  (:use [clojure.test]
        [techne.data-structs.directed-graph]))


(deftest test-has-vertex
  (is (has-vertex? (graph (list (list :a []))) :a ))
  (is (not (has-vertex? (graph (list (list :v []))) :a ))))

(deftest test-insert-vertex
  (let [g (graph '())]
    (is (has-vertex? (insert-vertex g :a) :a))))

(deftest test-insert-edge
  (is (:z (adjacents (insert-edge (graph (list (list :a #{}))) :a :z) :a))))

