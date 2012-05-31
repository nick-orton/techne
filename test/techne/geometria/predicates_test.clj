(ns techne.geometria.predicates-test
  (:use [clojure.test]
        [techne.geometria.elements :only [line point]]
        [techne.geometria.predicates]))

(deftest point-on-line-test
  (let [line      (line  1 0)
        point-on  (point 3 3)
        point-off (point 3 4)]
    (is (point-on-line? point-on line))
    (is (not (point-on-line? point-off line)))))

;TODO point-on-line-segment? point line-segment
;TODO point-in-circle? point circle
;TODO intersect? line-segment line-segment
