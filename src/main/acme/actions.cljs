(ns acme.actions
  (:require
    [acme.state :as state]
    [acme.view :as view]))

(defn ^:export new-tab []
  (some-> "New tab:" js/prompt .trim not-empty state/add-tab)
  (view/selector (state/tabs) @state/current-tab))

(defn ^:export delete-tab []
  (when (js/confirm "Delete?")
        (state/delete-tab)
        (view/selector (state/tabs) @state/current-tab)))

(defn ^:export paste [col])

(defn ^:export swap-tab [tab]
  (state/set-current-tab tab))
