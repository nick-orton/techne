(ns techne.bag )

(defprotocol Bag
  "A representation of a multi-set.  Bags can have duplicates, but are unordered.  
   Use a Bag where you would normally use a map of sequences that are used to accumulate counts"
  (put-n 
    [self item n])
  (put 
    [self item])
  (put-all 
    [self items])
  (pluck-n 
    [self item n])
  (pluck 
    [self item])
  (uniques 
    [self])
  (inspect [self])  ;TODO pull up into an inspectable
  (total 
    [self])
  (->seq 
    [self])
  (tally 
    [self item]))
;TODO tally with a predicate

; "Implements the bag interface using a HashMap.  A MapBag has constant time insert, removal, and tallying."
(deftype MapBag 
  [state]
  Bag
    (put-n [self item n] 
      (let [n* (+ n (tally self item))]
        (MapBag. (assoc state item n*))))
    (put [self item]
      (put-n self item 1))
    (put-all [self items]
      (reduce #(put %1 %2) self items))
    (tally [self item] 
      (get state item 0))
    (pluck-n [self item n]
      (let [n* (- (tally self item) n)
            state* (if (pos? n*)
                       (assoc state item n*)
                       (dissoc state item))]
           (MapBag. state*)))
    (pluck [self item]
      (pluck-n self item 1))
    (inspect [self] state)
    (uniques [self] (set (keys state))) 
    (total [self]
      (reduce #(+ %1 (tally self %2)) 0 (uniques self)))
    (->seq [self]
      (reduce #(concat (repeat (tally self %2) %2) %1) [] (uniques self)))
           
  Object
    (toString [self]
      (str "Bag: " (:state self))))

(defn create 
  ([] 
   (MapBag. {}))
  ([state] 
   (MapBag. state)))

(defn seq->bag
  "Construct a bag from a sequence"
  [sq]
  (put-all (create) sq))

