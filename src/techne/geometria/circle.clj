(ns techne.geometria.circle)


(defprotocol Circle
  (radius [c])
  (center [c]))


(defn circle [point distance]
  (reify Circle
    (radius [_] distance)
    (center [_] point)))
