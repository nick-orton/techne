(ns techne.geometria.elements)


(defprotocol Point
  (x-cord [p])
  (y-cord [p]))

(defprotocol Line
  (slope  [l])
  (offset [l]))

(defprotocol Line-Segment
  (line-slope [ls])
  (points     [ls])
  (segment->line [ls]))

(defn point [x y]
  (reify Point
    (x-cord [_] x)
    (y-cord [_] y)))

(defn line [m c]
  (reify Line
    (slope  [_] m)
    (offset [_] c)))

(defn line-segment [point1 point2]
  (reify Line-Segment
    (line-slope [_]
      (let [rise (- (y-cord point1) (y-cord point2))
            run  (- (x-cord point1) (x-cord point2))]
        (/ rise run)))
    (points [_]
      #{point1 point2})
    (segment->line [seg]
      (let [m (line-slope seg)
            y (y-cord point1)
            x (x-cord point1)
            c (- y (* m x))]
        (line m c)))))
