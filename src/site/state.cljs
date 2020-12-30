(ns site.state
  (:require [reagent.core :as r]))

;;task 1 counter
(def counter (r/atom 0))

;;task 2 temperature converter
(def celcius (r/atom 0))
(def farenheit (r/atom 32))

;;task 3 flight booker
(def journey-type (r/atom "One Way"))
(def departure (r/atom ""))
(def return (r/atom ""))
(def return-disabled (r/atom true))
(def book-disabled (r/atom true))
(def booking-modal-showing (r/atom false))

;;task 4 timer
(def interval (r/atom 30))

;;task 5 crud
(def name-filter (r/atom ""))
(def first-name (r/atom ""))
(def surname (r/atom ""))
(def create-disabled (r/atom false))
(def update-disabled (r/atom true))
(def delete-disabled (r/atom true))
(def names (r/atom [{:first-name "Hans" :surname "Emil"}
                    {:first-name "Max" :surname "Mustermann"}
                    {:first-name "Roman" :surname "Tisch"}]))
(def list-node (r/atom nil))
