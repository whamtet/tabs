(ns acme.state
  (:require
    [alandipert.storage-atom :refer [local-storage]]))

(def tab-storage (local-storage (atom {}) :tab-storage))
(def current-tab (atom (first (keys @tab-storage))))

(defn tabs []
  (keys @tab-storage))
(defn tab-content []
  (@tab-storage @current-tab))

(defn add-tab [tab]
  (reset! current-tab tab)
  (swap! tab-storage assoc tab [nil nil nil]))

(defn- rename* [m k1 k2]
  (-> m
      (assoc k2 (m k1))
      (dissoc k1)))
(defn rename [new-name]
  (swap! tab-storage rename* @current-tab new-name)
  (reset! current-tab new-name))

(defn set-current-tab [tab]
  (reset! current-tab tab))

(defn delete-tab []
  (swap! tab-storage dissoc @current-tab)
  (reset! current-tab (first (tabs))))

(defn assoc-col [col content]
  (when @current-tab
    (swap! tab-storage assoc-in [@current-tab col] content)))
