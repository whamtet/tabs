(ns acme.state
  (:require
    [alandipert.storage-atom :refer [local-storage]]))

(def tab-storage (local-storage (atom {}) :tab-storage))
(def current-tab (local-storage (atom nil) :current-tab))

(defn tabs []
  (keys @tab-storage))
