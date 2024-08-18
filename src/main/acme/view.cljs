(ns acme.view
  (:require
    acme.render)
  (:require-macros
    [acme.render :refer [defrender]]))

(defrender selector [tabs current-tab]
  (for [tab tabs]
    [:option {:value tab
              :selected (= tab current-tab)} tab]))

(defrender col-container [[col0 col1]]
  (list
   [:div {:class "w-1/2"}
    (for [line col0]
      [:div line])]
   [:div {:class "w-1/2"}
    (for [line col1]
      [:div line])]))
