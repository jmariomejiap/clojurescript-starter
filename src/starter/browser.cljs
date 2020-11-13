(ns starter.browser
  (:require
   [om.core :as om :include-macros true]
   [sablono.core :as html :refer-macros [html]]
   [starter.components.header :refer [header header-2]]
   ))  

(defonce app-state (atom {:text "Hello Natalia"}))

(defn test-component []
  (html [:div "mario test"]) )


(defn main []
  (om/root
   (fn [cur owner]
     (reify
       om/IRender
       (render [_]
         (html
          [:div
           [:h1 "this is my header inline"]
           (header)
           (header-2)
           (test-component)]))))
   app-state
   {:target (.getElementById js/document "app")}))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start")
  (main))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start)
  )

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
