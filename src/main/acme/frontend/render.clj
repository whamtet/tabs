(ns acme.frontend.render)

(defmacro render-fns [& syms]
  `(do
    ~@(for [sym syms]
       `(render ~(str sym) (~sym)))))
