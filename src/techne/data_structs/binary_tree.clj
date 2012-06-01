(ns techne.data-structs.binary-tree)


(defprotocol Tree
  (root   [tree])
  (left   [tree])
  (right  [tree])
  (insert [tree key])
  (delete [tree key])
  (has?   [tree key]))

(declare tree)

(defn leaf [val]
  (tree val nil nil))

(defn- leftmost [node]
  (if (nil? (left node))
    node
    (leftmost (left node))))


(defn tree [node left right]
  (reify Tree
    (root [_] node)
    (left [_] left)
    (right [_] right)

    (has? [_ k]
      (cond
        (= k node)
          true
        (< k node)
          (if (nil? right)
            false
            (has? right k))
        (> k node)
          (if (nil? left)
            false
            (has? left k))))

     (insert [self k]
       (cond
         (= k node)
           node ;do nothing
         (> k node)
           (if (nil? right)
             (tree node left (leaf k ))
             (tree node left (insert right k)))
         (< k node)
           (if (nil? left)
             (tree node (leaf k ) right)
             (tree node (insert left k) right))))

    (delete [self k]
      (cond
        (= k node)
          (cond
            (and right left)
              (let [replacement (leftmost right)]
                (tree (root replacement)
                      left
                      (delete right (root replacement))))
            right
              right
            left
              left
            true
              nil)
        (< k node)
          (if (nil? left)
            node ;value to delete not found
            (tree node (delete left k) right))
        (> k node)
          (if (nil? right)
            node ;value to delete not found
            (tree node left (delete right k)))))))
