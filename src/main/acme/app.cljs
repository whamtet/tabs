(ns acme.app
  (:require
    acme.actions ;; ensure it compiles
    [acme.view :as view]
    [acme.state :as state]))

(defn init []
  (view/selector (state/tabs)))
