; Functions to query and manipulate a Binary Search Tree.  The tree is
; expected to be represented as a vector.  The index of each element is twice
; the index of it's parent.  The right child is the successor of the left
; child.  The rule of the tree is that the right child is larger than the
; parent, and the left child is smaller.  This implementation of a BST only
; accepts Ints as values.

(ns techne.data-structs.binary-search-tree
  (:use [techne.data-structs.binary-tree]
        [techne.vectors]
        [techne.utils :only [always]]))

; returns the index if present or nil otherwise
;
; The structure of the tree is used to find the element in logN time.  If the
; element is larger than the current element, we look at the right child.
; If smaller, the left.
;
(defn locate [tree target]
  (loop [i 0]
    (cond (= i (count tree))
            nil
          (nil? (tree i))
            nil
          (= target (tree i))
            i
          (< target (tree i))
            (recur (left-index i))
          true
            (recur (right-index i)))))

; Returns the tree with the element inserted into the correct location.
;
(defn insert [tree elem]
  (loop [i 0]
    (cond
      (>= i (count tree))
        (let [expansion (repeat (- i (count tree)) nil)]
          (vconcat tree (conj (vec expansion) elem )))
      (< elem (tree i))
        (recur (left-index i))
      true
        (recur (right-index i)))))

; The in-order-successor of a node in the tree is the leftmost child
; of the right subtree.
; i : the index of the node in question
;
(defn- in-order-successor [tree i]
  (leftmost-child tree (right-index i)))

; Deletes the first instance of the element in the tree.
;
; First it locates the index of the element to be deleted.  Then if the node is
; a leaf it deletes it.  Otherwise it swaps the node with it's child if there
; is only one and recurses at the child node.  If there is more than one child,
; the node to be deleted is swapped with it's in-order-successor and recurses
; at the index of the IOS.
;
(defn delete [tree elem]
  (clean-right
    (loop [t tree
           i (locate tree elem) ]
      (cond
        (nil? i)
          tree
        (and (has-left-child t i) (has-right-child t i))
          (let [suc (in-order-successor t i)]
            (recur (assoc t i (t suc)) suc))
        (has-left-child t i)
          (recur (assoc t i (t (left-index i))) (left-index i))
        (has-right-child t i)
            (recur (assoc t i (t (right-index i))) (right-index i))
        true ; has no children
          (assoc t i nil)))))
