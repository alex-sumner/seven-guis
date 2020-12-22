(ns site.core
  (:require [reagent.dom :as r]
            [site.components.header :refer [header]]
	    [site.components.counter :refer [counter]]
            [site.components.flight-booker :refer [booker]]
	    [site.components.temp-converter :refer [converter]]
            [site.components.timer :refer [timer]]
            [site.components.crud :refer [crud]]))

(defn app
  []
  [:div
   [header]
   [counter]
   [converter]
   [booker]
   [timer]])

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
