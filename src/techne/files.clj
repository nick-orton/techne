(ns techne.files
  "Tools for working with files.
   Deprecated, should use clojure.java.io/file, file-seq, read-lines instead"
  {:deprecated 0.7}
  (:import (java.io BufferedReader FileReader File))
  (:use [clojure.contrib.str-utils2 :only [join]]))

(defn file->string 
  "Reads whole file into single string.
   Newlines seperated by \\n."
  [file]
    (with-open [rdr (BufferedReader. (FileReader. file))]
      (join "\n" (line-seq rdr))))

(defn dir->files
  "returns all the files in a directory"
  [dir-name]
  (.listFiles (new File dir-name)))
