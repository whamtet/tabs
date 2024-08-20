(ns acme.util)

(defn map-all [f & ss]
  (->> ss
       (map #(concat % (repeat nil)))
       (apply map f)
       (take (->> ss (map count) (apply max)))))
