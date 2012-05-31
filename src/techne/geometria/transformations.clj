(ns techne.geometria.transformations
  (:use [techne.geometria.elements :only [offset slope point mid-point
                                          segment->line line-through-point
                                          line-segment length]]
        [techne.geometria.circle :only [circle]]))


(defn intersection [line1 line2]
  (let [x (/ (- (offset line2) (offset line1))
             (- (slope line1) (slope line2)))
        y (+ (* (slope line1) x) (offset line1))]
       (point x y) ))

(defn perpendicular-slope [slope]
  (* -1 (/ 1 slope)))

; TODO test
(defn perpendicular-line [segment]
  (let [seg-line (segment->line segment)
        pslope (perpendicular-slope seg-line)
        mid      (mid-point segment)]
    (line-through-point pslope mid)))

;TODO test
(defn build-circle-from-3-points [a b c]
  (let [ ab (line-segment a b)
         ac (line-segment a c)
         pab (perpendicular-line ab)
         pac (perpendicular-line ac)
         center (intersection pab pac)
         radius (length (line-segment center a))]
    (circle center radius)))
