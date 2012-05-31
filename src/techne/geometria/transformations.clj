(ns techne.geometria.transformations
  (:use [techne.geometria.elements :only [offset slope point]]))


(defn intersection [line1 line2]
  (let [x (/ (- (offset line2) (offset line1))
             (- (slope line1) (slope line2)))
        y (+ (* (slope line1) x) (offset line1))]
       (point x y) ))

(defn perpendicular-slope [slope]
  (* -1 (/ 1 slope)))
