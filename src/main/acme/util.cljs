(ns acme.util)

(defn map-all [f s1 s2]
  (if (< (count s1) (count s2))
    (map f (concat s1 (repeat nil)) s2)
    (map f s1 (concat s2 (repeat nil)))))
