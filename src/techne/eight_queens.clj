; The eight queens problem asks a player to place eight queens on a board so
; that no queen is positioned to capture another.  We will represent the board
; as a vector where each index represents a column and its value represents
; the row that the queen is placed on.

(ns techne.eight-queens)


(defn- violation? [board row]
  (loop [cols (reverse board)
         i 1]
    (cond
      (empty? cols)
        false
      (or (= (first cols) row)
          (= (first cols) (+ row i))
          (= (first cols) (- row i)))
        true
      true
        (recur (rest cols) (inc i)))))

(defn place [board row]
  (cond
    (= row 8)
      (throw (RuntimeException.))
    (violation? board row)
      (place board (inc row))
    (= (count board) 7)
      (conj board row)
    true
      (try
        (place (conj board row) 0)
      (catch RuntimeException e
        (place board (inc row))))))
