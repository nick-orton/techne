(ns techne.files
  (:import (java.io BufferedReader FileReader File))
  (:require [clojure.contrib.str-utils2 :as str]))

(defn file->string 
  "Reads whole file into single string.
   Newlines seperated by \n."
  [file]
    (with-open [rdr (BufferedReader. (FileReader. file))]
      (str/join "\n" (line-seq rdr))))

(defn dir->files
  "returns all the files in a directory"
  [dir-name]
  (.listFiles (new File dir-name)))
