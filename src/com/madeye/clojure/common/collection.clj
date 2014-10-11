(ns com.madeye.clojure.common.collection)

; Keep finding I need this so factored it to common - could be 
; a multimethod but the dispatch function alone would be of a 
; similar size and complexity to this
(defn make-set
	[thing]
	(if (set? thing)
		thing
		(if (coll? thing)
			(set thing)
			#{thing}
		)
	)
)