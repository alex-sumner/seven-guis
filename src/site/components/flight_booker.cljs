(ns site.components.flight-booker
  (:require [site.state :as state]
            [reagent.core :as r]
            [tick.alpha.api :as t]
            ["@tailwindui/react" :refer [Transition Transition.Child]]))

(defn in-the-future?
  [date]
  (t/> (t/instant date) (t/now)))

(defn in-order?
  [a b]
  (t/< (t/instant a) (t/instant b)))

(defn update-gui-state
  []
  (let [journey-type @state/journey-type
        departure @state/departure
        return @state/return
        departure-supplied (> (count departure) 0)
        departure-ok (and departure-supplied
                          (in-the-future? departure))
        return-allowed (= journey-type "Return")
        return-supplied (> (count return) 0)
        return-ok (or (not return-allowed)
                      (and return-supplied
                           (in-the-future? return)
                           (or (not departure-supplied)
                               (in-order? departure return))))
        book-allowed (and departure-ok
                          return-ok)]
    (reset! state/return-disabled (not return-allowed))
    (reset! state/book-disabled (not book-allowed))))

(defn journey-type-changed
  [event]
  (let [new-type (.. event -target -value)]
    (reset! state/journey-type new-type)
    (update-gui-state)))

(defn departure-changed
  [event]
  (let [new-departure (.. event -target -value)]
    (reset! state/departure new-departure)
    (update-gui-state)))

(defn return-changed
  [event]
  (let [new-return (.. event -target -value)]
    (reset! state/return new-return)
    (update-gui-state)))

(defn toggle-modal
  [showing]
  (reset! state/booking-modal-showing showing))

(defn booking-type-msg
  []
  (str "You booked a " (if (= @state/journey-type "Return") "return " "one way ") "flight." ))

(defn booking-departure-msg
  []
  (let [departure @state/departure]
    (when (> (count departure) 0)
      (let [instant (t/instant departure)
            date (t/date instant)
            time (t/time instant)]
        (str "Departing on " date " at " time )))))

(defn booking-return-msg
  []
  (let [return @state/return]
    (when (> (count return) 0)
      (let [instant (t/instant return)
            date (t/date instant)
            time (t/time instant)]
        (str "Returning on " date " at " time )))))

(defn modal
  []
  [:> Transition {:class "fixed z-10 inset-0 overflow-y-auto flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0" :show @state/booking-modal-showing}
   [:> Transition.Child {:class "fixed inset-0 transition-opacity"
                         :aria-hidden "true"
                         :enter "ease-out duration-300"
                         :enterFrom "opacity-0"
                         :enterTo "opacity-100"
                         :leave "ease-in duration-200"
                         :leaveFrom "opacity-100"
                         :leaveTo "opacity-0"}
    [:div {:class "absolute inset-0 bg-gray-500 opacity-75"}]]
   ;;This element is to trick the browser into centering the modal contents.
   [:span {:class "hidden sm:inline-block sm:align-middle sm:h-screen", :aria-hidden "true"} "​"]
   [:> Transition.Child {:class "inline-block align-bottom bg-white rounded-lg px-4 pt-5 pb-4 text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-sm sm:w-full sm:p-6"
                         :role "dialog"
                         :aria-modal "true"
                         :aria-labelledby "modal-headline"
                         :enter "ease-out duration-300"
                         :enterFrom "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                         :enterTo "opacity-100 translate-y-0 sm:scale-100"
                         :leave "ease-in duration-200"
                         :leaveFrom "opacity-100 translate-y-0 sm:scale-100"
                         :leaveTo "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"}
    [:div
     [:div {:class "mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-green-100"} 
      [:svg {:class "h-6 w-6 text-green-600" :xmlns "http://www.w3.org/2000/svg" :fill "none" :viewBox "0 0 24 24" :stroke "currentColor" :aria-hidden "true"}
       [:path {:stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M5 13l4 4L19 7"}]]]
     [:div {:class "mt-3 text-center sm:mt-5"}
      [:h3 {:class "text-lg leading-6 font-medium text-gray-900" :id "modal-headline"} "Booking complete"]
      [:div {:class "mt-2"}
       [:p {:class "text-sm text-gray-500"} (booking-type-msg)]
       [:p {:class "text-sm text-gray-500"} (booking-departure-msg)]
       [:p {:class "text-sm text-gray-500"} (booking-return-msg)]]]]
    [:div {:class "mt-5 sm:mt-6"}
     [:button {:type "button" :on-click #(toggle-modal false) :class "inline-flex justify-center w-full rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:text-sm"} "Go back to main page"]]]])

(defn booker
    []
    [:div {:class "ml-2 sm:ml-8 mt-6 sm:mt-5"}
     [:div {:class "text-xl"} "Task 3: Flight Booker"]
     [:div {:class "mt-6 sm:mt-5 sm:grid sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-7 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"}
      [:label {:for "journey_type" :class "block text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:pt-2"} "One Way or Return"]
      [:div {:class "mt-1 sm:mt-0 sm:col-span-3 md:col-span-4 lg:col-span-6"}
       [:div {:class "max-w-lg rounded-md shadow-sm sm:max-w-xs"}
        [:select {:id "journey_type" :class "block form-select w-full transition duration-150 ease-in-out sm:text-sm sm:leading-5" :value @state/journey-type :on-change #(journey-type-changed %)}
         [:option "One Way"]
         [:option "Return"]]]]]
     [:div {:class "mt-1 sm:grid sm:mt-0 sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-7 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"}
      [:label {:for "departure_time" :class "block text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:pt-2"} "Departure Time"]
      [:div {:class "mt-1 sm:mt-0 sm:col-span-3  md:col-span-4 lg:col-span-6"}
       [:div {:class "max-w-lg rounded-md shadow-sm sm:max-w-xs"}
        [:input {:id "departure_time" :type "datetime-local" :value @state/departure :on-change #(departure-changed %) :class "form-input block w-full transition duration-150 ease-in-out sm:text-sm sm:leading-5"}]]]]
     [:div {:class "mt-1 sm:mt-0 sm:grid sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-7 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"}
      [:label {:for "return_time" :class (str "block text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:pt-2" (when @state/return-disabled " opacity-50"))} "Return Time"]
      [:div {:class "mt-1 sm:mt-0 sm:col-span-3  md:col-span-4 lg:col-span-6"}
       [:div {:class "max-w-lg rounded-md shadow-sm sm:max-w-xs"}
        [:input {:id "return_time" :type "datetime-local" :value @state/return :on-change #(return-changed %) :disabled @state/return-disabled :class "form-input block w-full transition duration-150 ease-in-out sm:text-sm sm:leading-5 disabled:opacity-50 disabled:bg-gray-300"}]]]]
     [:div {:class "mt-1 sm:mt-0 sm:grid sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-7 sm:gap-4 sm:items-start sm:border-t sm:border-gray-200 sm:pt-5"}
      [:div]
      [:div {:class "mt-1 sm:mt-0 sm:col-span-3  md:col-span-4 lg:col-span-6 "} 
       [:button {:type "button" :class "px-2.5 py-1.5 border border-transparent text-xs leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-500 focus:outline-none focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700 transition ease-in-out duration-150 inline-flex rounded-md shadow-sm disabled:opacity-50 disabled:textOpacity-50" :on-click #(toggle-modal true) :disabled @state/book-disabled} "Book Flights"]]]
     [modal]])

