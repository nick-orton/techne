(ns techne.data-structs.binary-tree)

(defprotocol Node
  (left      [node])
  (right     [node])
  (successor [node]))

(defprotocol Tree
  (root          [tree])
  (in-order-walk [tree])
  (insert        [tree key])
  (delete        [tree key])
  (minimum       [tree])
  (has?          [tree key]))

(declare tree)

(defn leaf [val]
  (tree val nil nil))


(defn tree [node left-node right-node]
  (reify
    Node
    (left [_] left-node)
    (right [_] right-node)
    (successor [_]
      (minimum right-node))

    Tree
    (root [_] node)

    (in-order-walk [_]
      (concat (if left-node (in-order-walk left-node))
              [node]
              (if right-node (in-order-walk right-node))))


    (minimum [tree]
      (if (nil? (left tree))
        tree
        (minimum (left tree))))

    (has? [_ k]
      (cond
        (= k node)
          true
        (> k node)
          (if (nil? right-node)
            false
            (has? right-node k))
        (< k node)
          (if (nil? left-node)
            false
            (has? left-node k))))

     (insert [self k]
       (cond
         (= k node)
           node ;do nothing
         (> k node)
           (if (nil? right-node)
             (tree node left-node (leaf k ))
             (tree node left-node (insert right-node k)))
         (< k node)
           (if (nil? left-node)
             (tree node (leaf k ) right-node)
             (tree node (insert left-node k) right-node))))

    (delete [self k]
      (cond
        (= k node)
          (cond
            (and right-node left-node)
              (let [replacement (successor self)]
                (tree (root replacement)
                      left-node
                      (delete right-node (root replacement))))
            right-node
              right-node
            left-node
              left-node
            true
              nil)
        (< k node)
          (if (nil? left-node)
            node ;value to delete not found
            (tree node (delete left-node k) right-node))
        (> k node)
          (if (nil? right-node)
            node ;value to delete not found
            (tree node left-node (delete right-node k)))))))
