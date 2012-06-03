(ns techne.data-structs.directed-graph-test
  (:use [clojure.test]
        [techne.data-structs.directed-graph]))

(def simple-graph (graph (list (struct Vertex :a #{} #{}))))
(def vert3g (graph (list (struct Vertex :a #{:b :c} #{})
                         (struct Vertex :b #{:c} #{:a})
                         (struct Vertex :c #{} #{:a :b}))))
(def vert3g* (graph (list (struct Vertex :a #{:b } #{})
                          (struct Vertex :b #{:c} #{:a})
                          (struct Vertex :c #{} #{:b}))))
(def vert4g (graph (list (struct Vertex :a #{:b } #{})
                         (struct Vertex :d #{:c} #{})
                         (struct Vertex :b #{:c} #{:a})
                         (struct Vertex :c #{} #{:b :d}))))



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
  (is (not (has-vertex? (remove-vertex vert3g :a) :a)))
  (is (not (:a (froms (remove-vertex vert3g :a) :b))))
  (is (not (:a (froms (remove-vertex vert3g :a) :c))))
  (is (not (:b (froms (remove-vertex vert3g :b) :c))))
  (is (not (:b (tos (remove-vertex vert3g :b) :a)))))

(deftest test-topological-sort
  (is (= (list :a :b :c) (topological-sort vert3g))))

(deftest shortest-path-test
  (is (= '(:a :c) (shortest-path vert3g :a :c)))
  (is (= '(:a :b :c) (shortest-path vert3g* :a :c)))
  (is (= '(:a :b :c) (shortest-path vert4g :a :c))))

