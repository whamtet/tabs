(ns acme.view
  (:require
    acme.render)
  (:require-macros
    [acme.render :refer [defrender]]))

(defrender selector [tabs current-tab]
  (for [tab tabs]
    [:option {:value tab
              :selected (= tab current-tab)} tab]))
