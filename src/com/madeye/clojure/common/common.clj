(ns com.madeye.clojure.common.common
  (:gen-class))

(require 'clojure.java.io)
(require '[clj-time.core :as tm])
(require '[clj-time.local :as tloc])
(require '[clj-time.format :as tfmt])

(defn load-props
  "Function to load a Java-style Properties file into a Clojure map"
  [file-name]
  (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)] 
      (let [props (java.util.Properties.)]
          (.load props reader)
          (into {} (for [[k v] props] [(keyword k) v])))))

(defn to-unix-time 
  "Function to turn a DateTime object into Unix time"
  [t] 
  (tm/in-secs (tm/interval (tm/epoch) t)))

(defn current-unix-time 
  "Function to get the current time in Unix time"
  [] 
  (to-unix-time (tm/now)))

(defn from-unix-time 
  "Function to convert a Unix time into a DateTime object"
  [s] 
  (tm/plus (tm/epoch) (tm/secs s)))
