(ns acme.render)

(defmacro defrender [sym args & body]
  `(defn ~sym ~args
    (render ~(str sym) (do ~@body))))
