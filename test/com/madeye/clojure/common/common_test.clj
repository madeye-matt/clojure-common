(ns com.madeye.clojure.common.common-test
  (:require [clojure.test :refer :all]
            [com.madeye.clojure.common.common :refer :all]))

(deftest propstest
  (testing "Loading properties from test/test.properties"
    (let [props (load-props "test/test.properties")]
      (is (= 1 (count props)))
      (is (= "test.value" (:test.property props)))
    )
  )
)
