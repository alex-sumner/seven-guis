(ns site.components.crud
  (:require [site.state :as state]
            [reagent.core :as r]
            [clojure.string :as st]))

(defn update-btns-enabled
  []
  (let [no-selection (= -1 (.-selectedIndex @state/list-node))]
    (reset! state/update-disabled no-selection)
    (reset! state/delete-disabled no-selection)))

(defn filter-changed
  [event]
  (let [new-filter (.. event -target -value)]
    (reset! state/name-filter new-filter))
  (js/setTimeout update-btns-enabled 30))

(defn first-name-changed
  [event]
  (let [new-first-name (.. event -target -value)]
    (reset! state/first-name new-first-name)))

(defn surname-changed
  [event]
  (let [new-surname (.. event -target -value)]
    (reset! state/surname new-surname)))

(defn list-selection-changed
  [_]
  (update-btns-enabled))

(defn parse-name
  [name-str]
  (let [[surname first-name] (st/split name-str #"," 2)]
    {:surname surname :first-name (st/triml first-name)}))

(defn selected-person
  []
  (when-let [selected-items (.-selectedOptions @state/list-node)]
    (when-let [selected-item (.item selected-items 0)]
      (.-value  selected-item))))

(defn index-of-currently-selected-name
  []
  (when-let [n (selected-person)]
    (let [target (parse-name n)]
      (reduce-kv
       (fn [_ k v]
         (if (= v target)
           (reduced k)))
       nil
       @state/names))))

(defn on-create
  []
  (let [new-first-name @state/first-name
          new-surname @state/surname
          new-map {:first-name new-first-name :surname new-surname}]
      (swap! state/names #(conj % new-map))
      (js/setTimeout update-btns-enabled 30)))

(defn on-update
  []
  (when-let [selected-index (index-of-currently-selected-name)]
    (let [new-first-name @state/first-name
          new-surname @state/surname
          new-map {:first-name new-first-name :surname new-surname}]
      (swap! state/names #(assoc % selected-index new-map))
      (js/setTimeout update-btns-enabled 30))))

(defn on-delete
  []
  (when-let [n (selected-person)]
    (let [target (parse-name n)
          new-names (filterv #(not= % target) @state/names)]
      (reset! state/names new-names))
    (js/setTimeout update-btns-enabled 30)))

(defn get-prefix
  []
  (let [untrimmed @state/name-filter
        trimmed (st/triml untrimmed)]
    (if (st/blank? trimmed)
      trimmed
      untrimmed)))

(defn filtered-names
  []
  (let [prefix (get-prefix)]
    (->> @state/names
         (filter #(st/starts-with? (:surname %) prefix))
         (map #(str (:surname %) ", " (:first-name %))))))

(defn list-item
  [index desc]
  ^{:key index}[:option desc])

(defn crud-button
  [{:keys [caption handler disabled]}]
  ^{:key caption}[:button {:type "button" :class "my-4 mx-2 px-2.5 py-1.5 border border-transparent text-xs leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-500 focus:outline-none focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700 transition ease-in-out duration-150 inline-flex rounded-md shadow-sm disabled:opacity-50 disabled:textOpacity-50" :on-click handler :disabled @disabled} caption])

(defn crud
  []
  [:div {:class "mx-2 sm:ml-8 mt-10"}
   [:div {:class "text-xl pb-4"} "Task 5: CrUD"]
   [:div {:class "sm:grid sm:grid-cols-2"}
    [:div {:class "sm:flex my-2 sm:mb-4"}
     [:label {:for "filter" :class "text-right min-w-40 text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:py-2 sm:mr-4"} "Filter prefix"]
     [:div {:class "mt-1 sm:mt-0 rounded-md sm:w-full shadow-sm"};;max-w-lg
      [:input {:id "filter" :class "form-input max-w-lg w-full sm:text-sm sm:leading-5" :value @state/name-filter :on-change filter-changed}]]]
    [:div]
    [:select {:ref #(reset! state/list-node %) :size "5" :class "max-w-lg w-full justify-self-stretch overflow-y-auto rounded-md" :on-change list-selection-changed}
     (doall (map-indexed list-item (seq (filtered-names))))]
    [:div
     [:div {:class "sm:flex sm:items-center my-2 sm:mb-4"}
      [:label {:for "first name" :class "text-right min-w-40 text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:py-2 sm:mr-4"} "First Name"]
      [:div {:class "mt-1 sm:mt-0 max-w-lg rounded-md shadow-sm sm:max-w-xs"}
       [:input {:id "first-name" :class "form-input w-full sm:text-sm sm:leading-5" :value @state/first-name :on-change first-name-changed}]]]
     [:div {:class "sm:flex sm:items-center my-2 sm:mb-4"}
      [:label {:for "first name" :class "text-right min-w-40 text-sm font-medium leading-5 text-gray-700 sm:mt-px sm:py-2 sm:mr-4"} "Surname"]
      [:div {:class "mt-1 sm:mt-0 max-w-lg rounded-md shadow-sm sm:max-w-xs"}
       [:input {:id "surname" :class "form-input w-full sm:text-sm sm:leading-5" :value @state/surname :on-change surname-changed}]]]]]
   [:div {:class "flex justify-center"}
    (doall (for [details [{:caption "Create" :handler on-create :disabled state/create-disabled}
                          {:caption "Update" :handler on-update :disabled state/update-disabled}
                          {:caption "Delete" :handler on-delete :disabled state/delete-disabled}]]
             (crud-button details)))]])


