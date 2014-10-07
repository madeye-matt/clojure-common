(ns com.madeye.clojure.common.config
  "Simple class to wrap a Properties file into an atom"
  (:gen-class)
  (:require 
  	[com.madeye.clojure.common.common :as c]
  )
)

(defn- reload [] (use :reload-all 'com.madeye.clojure.common.config))

(def ^:private global-config (atom {}))

(defn load-config
	[config-filename]
	(let [config (c/load-props config-filename)]
		(swap! global-config merge config)
	)
)

(defn get-property
  ([item]
	(item @global-config)
  )
  ([]
  	@global-config
  )
)

(defn get-all-properties
  []
  (get-property)
)

(defn get-obj-property
  ([item]
    (read-string (get-property item))
  )
)

(defn set-property
	[item-name-kw item-value]
	(swap! global-config assoc item-name-kw item-value)
)

(defn save-config
	[config-filename]
	(throw (ex-info "save-config not supported" {}))
)
