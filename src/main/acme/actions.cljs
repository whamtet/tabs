(ns acme.actions
  (:require
    [acme.state :as state]
    [acme.view :as view]))

(defn ^:export new-tab []
  (some-> "New tab:" js/prompt .trim not-empty state/add-tab)
  (view/selector (state/tabs) @state/current-tab)
  (view/col-container (state/tab-content)))

(defn ^:export delete-tab []
  (when (js/confirm "Delete?")
        (state/delete-tab)
        (view/selector (state/tabs) @state/current-tab)
        (view/col-container (state/tab-content))))

(defn ^:export paste [col]
  (.then
    (js/navigator.clipboard.readText)
    (fn [s]
      (state/assoc-col col (.trim s))
      (view/col-container (state/tab-content)))))

(defn ^:export swap-tab [tab]
  (state/set-current-tab tab)
  (view/col-container (state/tab-content)))

(defn ^:export rename []
  (some-> "Rename:" js/prompt .trim not-empty state/rename)
  (view/selector (state/tabs) @state/current-tab))

(defn ^:export paste-interleaved [size]
  (.then
    (js/navigator.clipboard.readText)
    (fn [s]
      (state/assoc-interleaved size (.trim s))
      (view/col-container (state/tab-content)))))

(defn ^:export copy [col]
  (when-let [s (get (state/tab-content) col)]
    (js/navigator.clipboard.writeText s)))
