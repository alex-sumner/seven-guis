(ns site.core
  (:require [reagent.dom :as r]))

(defn app
  []
  [:div "Seven GUI Tasks"]
  )

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
