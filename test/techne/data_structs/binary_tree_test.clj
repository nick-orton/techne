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
  (is (= 8 (root (right (right t))))))

(deftest test-has
  (is (has? t 5))
  (is (has? t 3))
  (is (has? t 2))
  (is (has? t 4))
  (is (has? t 7))
  (is (has? t 6))
  (is (has? t 8))
  (is (not (has? t 1))))

(deftest test-minimum
  (is (= 2 (root (minimum t)))))

(deftest test-delete
  (is (= 6 (root (delete t 5))))
  (is (not (has? (delete t 5) 5))))

(deftest test-inorder-walk
  (is (= [2 3 4 5 6 7 8] (in-order-walk t))))

