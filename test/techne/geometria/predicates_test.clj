(ns techne.geometria.predicates-test
  (:use [clojure.test]
        [techne.geometria.elements :only [line point line-segment]]
        [techne.geometria.predicates]))

(deftest point-on-line-test
  (let [line      (line  1 0)
        point-on  (point 3 3)
        point-off (point 3 4)]
    (is (point-on-line? point-on line))
    (is (not (point-on-line? point-off line)))))

(deftest point-on-line-segment
  (let [seg                      (line-segment (point 0 0) (point 5 5))
        point-on                 (point 1 1)
        point-off-line           (point 3 4)
        point-too-far            (point 6 6)
        point-too-far-other-side (point -1 -1)]

    (is (point-on-line-segment? point-on seg))
    (is (not (point-on-line-segment? point-off-line seg)))
    (is (not (point-on-line-segment? point-too-far seg)))
    (is (not (point-on-line-segment? point-too-far-other-side seg)))))

;TODO point-in-circle? point circle
;TODO intersect? line-segment line-segment
