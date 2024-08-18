(ns acme.frontend.render
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]))

(defn render [id hiccup]
  (set! (.-innerHTML (js/document.getElementById id)) (html hiccup)))
