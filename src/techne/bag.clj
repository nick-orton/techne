(ns techne.bag
  )

(defprotocol Bag
  "A representation of a multi-set.  Bags can have duplicates, but are unordered.  
   Use a Bag where you would normally use a map of sequences that are used to accumulate counts"
  (put-n 
    "Put n items in the bag"
    [self item n])
  (put 
    "Put 1 item in the bag"
    [self item])
  (put-all 
    "Put all the items from a Sequence into a Bag"
    [self items])
  (pluck-n 
    "Pluck n items out of the bag"
    [self item n])
  (pluck 
    "Pluck 1 item out of the bag"
    [self item])
  (uniques 
    "Count the number of unique items in the bag"
    [self])
  (inspect [self])  ;TODO pull up into an inspectable
  (total 
    "Count the total number of items in the bag"
    [self])
  (->seq 
    "Transform the item into a sequence"
    [self])
  (tally 
    "Count the number of items in the bag equal to a given item"
    [self item]))
;TODO tally with a predicate

(deftype MapBag 
  [state]
  Bag
  "Implements the bag interface using a HashMap.  A MapBag has constant time insert, removal, and tallying."
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
           ;TODO
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
  [sq]
  (put-all (create) sq))


