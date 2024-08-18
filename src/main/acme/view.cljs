(ns acme.view
  (:require
    acme.render)
  (:require-macros
    [acme.render :refer [defrender]]))

(defrender selector [tabs]
  (for [tab tabs]
    [:option {:value tab} tab]))
