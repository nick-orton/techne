; The eight queens problem asks a player to place eight queens on a board so
; that no queen is positioned to capture another.  We will represent the board
; as a vector where each index represents a column and its value represents
; the row that the queen is placed on.
;
(ns techne.probs.eight-queens)

; A queen can be in violation in 1 of 3 ways
; 1. on the same column as another queen (impossible in our solution)
; 2. on the same row as another queen
; 3. on a diagonal from a queen.
;
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

; Try to place a queen on a board.  If the queen is off the board, then back
; up and try the column previous one row up.  If the queen violates the rules,
; try to place it one row up.  If the queen is not in violation and the board
; is full return.  Otherwise try to place a queen on the next column.
;
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
