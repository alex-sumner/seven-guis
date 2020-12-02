(ns site.core
  (:require [reagent.dom :as r]
            [site.components.header :refer [header]]
	    [site.components.counter :refer [counter]]
	    [site.components.temp-converter :refer [converter]]))

(defn app
  []
  [:div
   [header]
   [counter]
   [converter]])

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
