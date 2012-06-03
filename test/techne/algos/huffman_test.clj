(ns techne.algos.huffman-test
  (use [techne.algos.huffman]
       [clojure.test]))

(deftest test-build-code-tree
  (let [freq-map {\a 45 \b 13 \c 12 \d 16 \e 9 \f 5}
        tree (build-huffman-code-tree freq-map)]
    (is (= \a (:value (:left tree))))
    (is (= \d (:value (:right (:right (:right tree))))))))

;TODO convert tree into codes

