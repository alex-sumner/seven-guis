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
  [:<> 
   [header]
   [:div {:class "lg:grid lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 lg:gap-4 lg:items-start"}
    [counter]
    [converter]
    [booker]
    [timer]
    [crud]]])

(defn ^:dev/after-load start []
  (r/render [app] (.getElementById js/document "app")))


(defn ^:export init
  []
  (start))
