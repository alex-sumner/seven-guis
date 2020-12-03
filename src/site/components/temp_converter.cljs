(ns site.components.temp-converter
  (:require [site.state :as state]))

(defn celcius-to-farenheit [c]
  (let [f (+  (* c 1.8) 32)]
    (.toFixed f 2)))

(defn farenheit-to-celcius [f]
  (let [c (/ (- f 32) 1.8)]
    (.toFixed c 2)))

(defn change-temp [event unit]
  (let [new-str (.. event -target -value)
        is-celcius (= unit :celcius)]
    (if is-celcius
      (reset! state/celcius new-str)
      (reset! state/farenheit new-str))
    (let [new-temp (.valueOf (js/Number. new-str))
          new-temp-valid (not (.isNaN js/Number new-temp))]
      (if new-temp-valid
        (if is-celcius
          (reset! state/farenheit (celcius-to-farenheit new-temp))
          (reset! state/celcius (farenheit-to-celcius new-temp)))))))

(defn converter
  []
  [:div {:class "p-8 flex items-center"}
   [:div {:class "relative sm:flex sm:items-center"}
    [:div {:class "mt-1 mr-4 sm:relative rounded-md shadow-sm"}
     [:input {:id "celsius" :class "form-input w-full sm:text-sm sm:leading-5" :value @state/celcius :on-change #(change-temp % :celcius)}]]
    [:label {:for "celsius" :class "mr-4 text-sm font-medium leading-5 text-gray-700"} "Celsius ="]]
   [:div {:class "relative sm:flex sm:items-center"}
    [:div {:class "mt-1 mr-4 sm:relative rounded-md shadow-sm"}
         [:input {:id "farenheit" :class "form-input w-full sm:text-sm sm:leading-5" :value @state/farenheit :on-change  #(change-temp % :farenheit)}]]
    [:label {:for "farenheit" :class "text-sm font-medium leading-5 text-gray-700"} "Farenheit"]]])
