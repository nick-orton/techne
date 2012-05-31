(ns techne.geometria.transformations-test
  (:use [clojure.test]
        [techne.geometria.elements :only [line x-cord y-cord]]
        [techne.geometria.transformations]))

(deftest intersection-test
  (let [line1 (line  3 1)
        line2 (line -1 6)
        p     (intersection line1 line2)]
    (is (= 5/4 (x-cord p)))
    (is (= 19/4 (y-cord p)))))


;TODO reflect point line

