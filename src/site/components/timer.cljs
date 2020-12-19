(ns site.components.timer
  (:require [reagent.core :as r]
            [site.state :as state]))

(defn increment-counter
  [counter]
  (if (< @counter @state/interval)
    (swap! counter inc))
  )

(defn percent-done-string
  [counter]
  (let [interval @state/interval]
    (str (* (/ counter interval) 100) "%")))

(defn slider-moved
  [event]
  (reset! state/interval (.. event -target -value)))

(defn timer
  []
  (r/with-let [elapsed (r/atom 0)
               timer-fn  (js/setInterval #(increment-counter elapsed) 1000)]
    [:div {:class "py-8 pl-2 sm:p-8"}
     [:div {:class "text-xl pb-8"} "Task 4: Timer"]
     [:div {:class "relative pt-1 max-w-md sm:max-w-lg"}
      [:div {:class "pb-4"}
       [:input {:class "bg-indigo-600 hover:bg-indigo-500 focus:outline-none active:bg-indigo-700 w-full" :type "range" :min 1 :max 120 :defaultValue 25 :onInput #(slider-moved %)}]]
      [:div "Elapsed Time: " (str @elapsed)]
      [:div {:class "overflow-hidden h-2 mb-4 text-xs flex rounded bg-pink-200"}
       [:div {:style {:width (percent-done-string @elapsed)} :class "shadow-none flex flex-col text-center whitespace-nowrap text-white justify-center bg-pink-500"}]]]
     [:button {:type "button" :class "px-2.5 py-1.5 border border-transparent text-xs leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-500 focus:outline-none focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700 transition ease-in-out duration-150 inline-flex rounded-md shadow-sm disabled:opacity-50 disabled:textOpacity-50" :on-click #(reset! elapsed 0)} "Reset Timer"]
     ]
    (finally (js/clearInterval timer-fn))))
