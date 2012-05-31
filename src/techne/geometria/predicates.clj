(ns techne.geometria.predicates
  (:use [techne.geometria.elements :only [offset slope x-cord y-cord segment->line points]]))


(defn point-on-line? [point line]
  (= (y-cord point) (+ (* (slope line) (x-cord point)) (offset line))))


(defn high-point [segment]
  (let [y (apply max (map y-cord (points segment)))]
    (first (filter #(= (y-cord %) y) (points segment)))))

(defn low-point [segment] nil
   (let [y (apply min (map y-cord (points segment)))]
    (first (filter #(= (y-cord %) y) (points segment)))))


(defn left-point [segment] nil
  (let [x (apply min (map x-cord (points segment)))]
    (first (filter #(= (x-cord %) x) (points segment)))))


(defn right-point [segment] nil
  (let [x (apply max (map x-cord (points segment)))]
    (first (filter #(= (x-cord %) x) (points segment)))))


(defn point-on-line-segment? [point segment]
  (let [line (segment->line segment)]
    (and (point-on-line? point line)
         (>= (y-cord (high-point segment)) (y-cord point))
         (<= (y-cord (low-point segment)) (y-cord point))
         (<= (x-cord (left-point segment)) (x-cord point))
         (>= (x-cord (right-point segment)) (x-cord point)))))
