(ns techne.geometria.predicates
  (:use [techne.geometria.elements :only [offset slope x-cord y-cord]]))


(defn point-on-line? [point line]
  (== (y-cord point) (+ (* (slope line) (x-cord point)) (offset line))))
