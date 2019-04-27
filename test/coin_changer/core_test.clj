(ns coin-changer.core-test
  (:require [clojure.test :refer :all]
            [coin-changer.core :refer :all]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))



(defspec returns-the-same-amount
  100
  (prop/for-all [money gen/s-pos-int]
    (= (change money)) []))
