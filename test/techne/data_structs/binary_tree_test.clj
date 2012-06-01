(ns techne.data-structs.binary-tree-test
 (use [clojure.test]
      [techne.data-structs.binary-tree]))


(def t (insert (insert (insert (insert (insert (insert (leaf 5) 3) 7) 2) 4) 6) 8))
;
;                        5
;                      /   \
;                     3     7
;                    / \   / \
;                   2   4 6   8
;

(deftest test-insert
  (is (= 5 (root t)))
  (is (= 3 (root (left t))))
  (is (= 2 (root (left (left t)))))
  (is (= 4 (root (right (left t)))))
  (is (= 7 (root (right t))))
  (is (= 6 (root (left (right t)))))
  (is (= 8 (root (right (right t)))))

         )
