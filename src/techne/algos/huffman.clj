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

(defn- is-leaf [stack-node]
  (nil? (:left (first stack-node))))

(defn build-code-map [code-tree]
  (loop [stack (list [(:left code-tree) "0"] [(:right code-tree) "1"])
         code-map {}]
    (if (empty? stack)
      code-map
      (let [top (first stack)]
        (if (is-leaf top)
            (recur (rest stack) (assoc code-map (:value (first top)) (second top)))
            (recur
              (conj (rest stack) [(:left  (first top)) (str (second top) "0")]
                                 [(:right (first top)) (str (second top) "1")])
              code-map))))))


