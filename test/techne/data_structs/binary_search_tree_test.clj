(ns techne.data-structs.binary-search-tree-test
  (:use [clojure.test]
        [techne.data-structs.binary-search-tree]))

(deftest test-locate
  (is (nil? (locate [] 0)))
  (is (= 0 (locate [2] 2)))
  (is (= 1 (locate [2 1] 1)))
  (is (= 2 (locate [2 1 3] 3)))
  (is (= 4 (locate [3 1 4 nil 2] 2)))
  (is (nil? (locate [3 1 4 nil nil nil 5] 2)))
  (is (= 6 (locate [3 1 4 nil nil nil 5] 5))))

(deftest test-insert
  (is (= [1] (insert [] 1)))
  (is (= [2 1] (insert [2] 1)))
  (is (= [2 nil 3] (insert [2] 3)))
  (is (= [2 1 4 nil nil 3] (insert [2 1 4] 3)))
  (is (= [2 nil 4 nil nil 3] (insert [2 nil 4] 3)))
  (is (= [2 1 3] (insert [2 1] 3))))

(def _ nil)

(deftest test-delete
  (is (= [] (delete [] 1)))
  (is (= [] (delete [1] 1)))
  (is (= [4 2 ] (delete [3 2 4] 3)))
  (is (= [2 ] (delete [3 2 ] 3)))
  (is (= [2 ] (delete [3 _ 2 ] 3)))
  (is (= [4 1 5 _ _ _ 6] (delete [3 1 5 _ _ 4 6 ] 3)))
  (is (= [4 1 7 _ _ 5 6 ]
         (delete [3
                  1       7
                  _   _   4  6
                  _ _ _ _ _ 5] 3))))
