(ns techne.data-structs.tree
  )

(defn tree? [tree]
  (seq? tree))

(defn children [node]
  (rest node))

(defn root [tree] tree)

(defn leaf? [node]
  (or (not (seq? node))
      (empty? (children node))))

(defn leaf [node]
  (if (seq? node)
    (first node)
    node))

(defn in? [node target]
    (cond (nil? target) false
          (nil? node) false
          (leaf? node)
            (= (leaf node) target)
          (= (first node) target) true
          true
             (some #(in? % target)
                   (children node))))
