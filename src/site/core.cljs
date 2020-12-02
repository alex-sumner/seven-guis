(ns site.core
  (:require [reagent.dom :as r]
            [site.components.header :refer [header]]
	    [site.components.counter :refer [counter]]))

(defn app
  []
  [:div
   [header]
   [counter]])

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
