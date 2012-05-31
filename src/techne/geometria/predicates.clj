(ns techne.geometria.predicates
  (:use [techne.geometria.elements :only [offset slope x-cord y-cord segment->line points
                                          high-point low-point left-point right-point]]))


(defn point-on-line? [point line]
  (= (y-cord point) (+ (* (slope line) (x-cord point)) (offset line))))


(defn point-on-line-segment? [point segment]
  (let [line (segment->line segment)]
    (and (point-on-line? point line)
         (>= (y-cord (high-point segment)) (y-cord point))
         (<= (y-cord (low-point segment)) (y-cord point))
         (<= (x-cord (left-point segment)) (x-cord point))
         (>= (x-cord (right-point segment)) (x-cord point)))))
