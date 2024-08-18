(ns acme.frontend.app
  (:require
    [acme.frontend.render :as render]
    [alandipert.storage-atom :refer [local-storage]])
  (:require-macros [acme.frontend.render :refer [render-fns]]))

(def tabs (local-storage (atom {}) :tabs))

(defn selector []
  (list
    [:option {:value "x"} "X"]))

(defn init []
  (println "Hello World")
  (render-fns selector))
