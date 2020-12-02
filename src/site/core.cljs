(ns site.core
  (:require [reagent.dom :as r]
            [site.components.header :refer [header]]))

(defn app
  []
  [:div
   [header]]
  )

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
