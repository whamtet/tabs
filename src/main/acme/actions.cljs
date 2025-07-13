(ns acme.actions
  (:require
    [cljs.reader :refer [read-string]]
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

(defn ^:export copy-song-name []
  (js/navigator.clipboard.writeText @state/current-tab))

(defn ^:export download []
  (let [link (js/document.createElement "a")]
    (set! (.-download link) "ttabs.edn")
    (->> @state/tab-storage
         pr-str
         (str "data:text/plain;charset=utf-8,")
         (set! (.-href link)))
    (js/document.body.appendChild link)
    (.click link)
    (js/document.body.removeChild link)))

(defn ^:export upload [file]
  (when file
        (let [reader (js/FileReader.)]
          (set! (.-onload reader)
            (fn [e]
              (-> e .-target .-result read-string state/upload)
              (js/location.reload)))
          (.readAsText reader file))))
