(ns com.madeye.clojure.common.common
  (:gen-class))

(require 'clojure.java.io)
(require '[clj-time.core :as tm])
(require '[clj-time.local :as tloc])
(require '[clj-time.format :as tfmt])
(require '[clojure.string :as str])

(defn load-props
  "Function to load a Java-style Properties file into a Clojure map"
  [file-name]
  (let [props (java.util.Properties.)]
    (if (and (not (nil? file-name)) (> (count file-name) 0))
      (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)] 
        (.load props reader)
      )
    )
    (into {} (for [[k v] props] [(keyword k) v]))
  )
)

(defn to-unix-time 
  "Function to turn a DateTime object into Unix time"
  [t] 
  (tm/in-seconds (tm/interval (tm/epoch) t)))

(defn current-unix-time 
  "Function to get the current time in Unix time"
  [] 
  (to-unix-time (tm/now)))

(defn from-unix-time 
  "Function to convert a Unix time into a DateTime object"
  [s] 
  (tm/plus (tm/epoch) (tm/secs s)))

(defn count-record [v] { :term (first v) :count (count (second v))})

(defn compare-count-map-desc 
    [m1 m2]
    (compare (:count m2) (:count m1))
)

(defn compare-count-map-asc 
    [m1 m2]
    (compare (:count m1) (:count m2))
)

(defn get-date-range 
  "Gets a date range for a predetermined period of time - currently defined :last_week, :last_month, :all_time.  Returns a map with :start and :end defined"
  [per]
  (case per
    :last_week
      { :start (tm/minus (tm/now) (tm/weeks 1)) :end (tm/now) }
    :last_month
      { :start (tm/minus (tm/now) (tm/months 1)) :end (tm/now) }
    :all_time
      { :start (tm/date-time 2000 1 1) :end (tm/now) }
  )
)

(defn parse-float
  "Function to parse a float string, return 0 if nil"
  [value]
  (if (empty? value)
    0
    (Float. value)
  )
)

(defn build-map
  [headings data]
  (loop [cnt 0
         result {}]
    (if (< cnt (count headings))
      (recur (inc cnt) (assoc result (keyword (get headings cnt)) (get data cnt)))
      result
    )
  )
)

(defn split-row
  [row delimiter]
  (str/split row delimiter)
)

(defn parse-delimited-string
  [string delimiter]
  (let [parsed-data (map #(split-row % delimiter) (str/split string #"\n"))]
    (map (partial build-map (first parsed-data)) (rest parsed-data))
  )
)

(defn parse-delimited-file
  [filename delimiter]
  (-> filename slurp (parse-delimited-string (re-pattern (str "[" delimiter "]"))))
)

(defn convert-string-keys-to-keywords
  [obj]
  (if (map? obj)
    (loop [remaining obj result {}]
     (let [head (first remaining)
           tail (rest remaining)]
        (if (not (empty? remaining))
          (let [mkey (keyword (key head))
                mval (convert-string-keys-to-keywords (val head))]
            (recur tail (assoc result mkey mval))
          )
          result
        )
      )
    )
    (if (coll? obj)
      (map convert-string-keys-to-keywords obj)
      obj
    )
  )
)

