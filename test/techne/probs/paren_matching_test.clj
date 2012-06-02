(ns techne.probs.paren-matching-test
  (:use [clojure.test]
        [techne.probs.paren-matching]))


(deftest test-paren-matching
  (is (matches-parens? "()"))
  (is (matches-parens? "(())"))
  (is (matches-parens? "(())()"))
  (is (matches-parens? "((a))()"))
  (is (matches-parens? "((a)b)()"))
  (is (matches-parens? "((a)b)()c"))
  (is (matches-parens? ""))
  (is (matches-parens? "a"))
  (is (not(matches-parens? ")")))
  (is (not(matches-parens? "(")))
  (is (not(matches-parens? ")(")))
  (is (not(matches-parens? "()(")))
  (is (not(matches-parens? "())")))
  (is (not(matches-parens? "(()"))))


