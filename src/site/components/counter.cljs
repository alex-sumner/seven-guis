(ns site.components.counter
  (:require [site.state :as state]))

(defn counter
  []
  [:div {:class "mt-10 pb-8 px-2 sm:pl-8 sm:self-start"}
   [:div {:class "text-xl pb-4"} "Task 1: Counter"]
   [:span {:class "px-2.5 py-1.5 mr-4 rounded-md text-sm font-medium leading-5 bg-white text-indigo-800"}
    @state/counter]
   
   [:span {:class "rounded-md shadow-sm"}
    [:button {:type "button" :class "px-2.5 py-1.5 border border-transparent text-xs leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-500 focus:outline-none focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700 transition ease-in-out duration-150"
              :on-click #(swap! state/counter inc)}
     "Count"]]]
  )
