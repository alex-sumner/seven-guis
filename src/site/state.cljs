(ns site.state
  (:require [reagent.core :as r]))

;;task 1 counter
(def counter (r/atom 0))

;;task 2 temperature converter
(def celcius (r/atom 0))
(def farenheit (r/atom 32))
