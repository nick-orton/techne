(ns techne.files-test
  (:use [techne.files]
        [clojure.test]
        [techne.test-utils]))

(deftest test-file->string
  (is (= 63 (.length (file->string "test-data/sample_file.clj")))))

(deftest test-dir->files
  (is (= "sample_file.clj" (.getName (first (dir->files "test-data"))))))
