(ns techne.algos.huffman
  (:import [java.util PriorityQueue]))

(defstruct node :freq :value :left :right)

(def node-comparator
  (comparator
      #(< (:freq %1) (:freq %2))))

(defn priority-queue []
  (PriorityQueue. 10 node-comparator))

(defn build-queue [frequency-map]
  (let [queue (priority-queue)]
    (doseq [keyval frequency-map]
      (.add queue (struct node (second keyval) (first keyval))))
    queue))


(defn build-huffman-code-tree [frequency-map]
  (loop [queue (build-queue frequency-map)]
    (if (= 1 (.size queue))
      (.poll queue)
      (let [left (.poll queue)
            right (.poll queue)
            freq (+ (:freq left) (:freq right))
            n     (struct node freq nil left right)]
        (.add queue n)
        (recur queue)))))

