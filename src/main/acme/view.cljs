(ns acme.view
  (:require
    acme.render
    [acme.util :as util])
  (:require-macros
    [acme.render :refer [defrender]]))

(defrender selector [tabs current-tab]
  (for [tab tabs]
    [:option {:value tab
              :selected (= tab current-tab)} tab]))

(defrender col-container [[col0 col1 col2]]
  (if (some-> col1 .trim not-empty)
    (util/map-all
     (fn [l0 l1 l2]
       [:div {:class "flex mt-2 hover:bg-sky-200"}
        [:div {:class "w-1/3"} l0]
        [:div {:class "w-1/3"} l1]
        [:div {:class "w-1/3"} l2]])
     (some-> col0 (.split "\n"))
     (some-> col1 (.split "\n"))
     (some-> col2 (.split "\n")))
    (util/map-all
     (fn [l0 l1]
       [:div {:class "flex mt-2 hover:bg-sky-200"}
        [:div {:class "w-1/2"} l0]
        [:div {:class "w-1/2"} l1]])
     (some-> col0 (.split "\n"))
     (some-> col2 (.split "\n")))))
