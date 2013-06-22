(ns com.madeye.clojure.common.common-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as tm]
            [com.madeye.clojure.common.common :refer :all]))

(deftest test-load-props
  (testing "Loading properties from test/test.properties"
    (let [props (load-props "test/test.properties")]
      (is (= 1 (count props)))
      (is (= "test.value" (:test.property props)))
    )
  )
)

(deftest test-to-unix-time
  (testing "Testing conversion of time to Unix time"
    (let [unixtime (to-unix-time (tm/date-time 2013 6 22 10 56))]
      (is (= 1371898560 unixtime))
    )
  )
)

(deftest test-from-unix-time
  (testing "Testing conversion of time from Unix time"
    (let [normaltime (from-unix-time 1371898560)]
      (is (=  (tm/date-time 2013 6 22 10 56) normaltime))
    )
  )
)
