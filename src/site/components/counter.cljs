(ns site.components.counter
  (:require [site.state :as state]))

(defn counter
  []
  [:div {:class "py-8 pl-2 sm:p-8"}
   [:div {:class "text-xl pb-8"} "Task 1: Counter"]
   [:span {:class "inline-flex items-center px-2.5 py-1.5 mr-4 rounded-md text-sm font-medium leading-5 bg-white text-indigo-800"}
    @state/counter]
   
   [:span {:class "inline-flex rounded-md shadow-sm"}
    [:button {:type "button" :class "inline-flex items-center px-2.5 py-1.5 border border-transparent text-xs leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-500 focus:outline-none focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700 transition ease-in-out duration-150"
              :on-click #(swap! state/counter inc)}
     "Count"]]]
  )
