(ns techne.algos.huffman-test
  (use [techne.algos.huffman]
       [clojure.test]))

(def freq-map {\a 45 \b 13 \c 12 \d 16 \e 9 \f 5})
(def tree (build-huffman-code-tree freq-map))

(deftest test-build-code-tree
  (is (= \a (:value (:left tree))))
  (is (= \d (:value (:right (:right (:right tree)))))))

(deftest test-build-code-map
  (is (= "0" (get (build-code-map tree) \a)))
  (is (= "101" (get (build-code-map tree) \b)))
  (is (= "100" (get (build-code-map tree) \c)))
  (is (= "111" (get (build-code-map tree) \d)))
  (is (= "1101" (get (build-code-map tree) \e)))
  (is (= "1100" (get (build-code-map tree) \f))))


