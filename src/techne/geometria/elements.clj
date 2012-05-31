(ns techne.geometria.elements)


(defprotocol Point
  (x-cord [p])
  (y-cord [p]))

(defprotocol Line
  (slope  [l])
  (offset [l]))

(defprotocol Line-Segment
  (line-slope    [ls])
  (points        [ls])
  (segment->line [ls])
  (high-point    [ls])
  (low-point     [ls])
  (left-point    [ls])
  (right-point   [ls])
  (mid-point     [ls])
  (length        [ls]))

(defn point [x y]
  (reify Point
    (x-cord [_] x)
    (y-cord [_] y)))

(defn line [m c]
  (reify Line
    (slope  [_] m)
    (offset [_] c)))

(defn- point-that-is [ord-fn dimension-fn segment]
  (let [d (apply ord-fn (map dimension-fn (points segment)))]
    (first (filter #(= (dimension-fn %) d) (points segment)))))


(defn line-segment [point1 point2]
  (let [rise (- (y-cord point1) (y-cord point2))
        run  (- (x-cord point1) (x-cord point2))]
    (reify Line-Segment

      (line-slope [_]
        (/ rise run))

      (points [_]
        #{point1 point2})

      (segment->line [seg]
        (let [m (line-slope seg)
              y (y-cord point1)
              x (x-cord point1)
              c (- y (* m x))]
          (line m c)))

      (length [_]
        (java.lang.Math/sqrt (+ (* rise rise) (* run run))))

      (high-point [segment]
        (point-that-is max y-cord segment))

      (low-point [segment]
        (point-that-is min y-cord segment))

      (left-point [segment]
        (point-that-is min x-cord segment))

      (right-point [segment]
        (point-that-is max x-cord segment))

      (mid-point [seg]
        (let [x (+ (x-cord (left-point seg))
                   (/ (- (x-cord (right-point seg))
                         (x-cord (left-point  seg))) 2))
              y (+ (y-cord (low-point seg))
                   (/ (- (y-cord (high-point seg))
                         (y-cord (low-point  seg))) 2))]
         (point x y))))))
