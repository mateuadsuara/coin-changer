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
    (= (apply + (change money)) money)))





(def coins [1 2 5 10 20 50 100 200])
(defn in? [ col elem ]
  (some (fn [x] (= elem x)) col))
(defn is-coin? [elem] (in? coins elem))

(defspec returns-coins
  {:max-size 1000 :num-tests 1000}
  (prop/for-all [money gen/s-pos-int]
    (every? is-coin? (change money))))





(defn max-number-coins-by-type? [col coin-type max-coins]
  (<=
    (count (filter (fn [x] (= x coin-type)) col))
    max-coins))

(defspec doesnt-have-more-than-one-onecent-coin
  100
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 1 1)))

(defspec doesnt-have-more-than-two-twocent-coin
  100
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 2 2)))

(defspec doesnt-have-more-than-one-fivecent-coin
  100
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 5 1)))

(defspec doesnt-have-more-than-one-tencent-coin
  100
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 10 1)))

(defspec doesnt-have-more-than-two-twentycent-coin
  100
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 20 2)))

(defspec doesnt-have-more-than-one-fiftycent-coin
  {:max-size 1000 :num-tests 1000}
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 50 1)))

(defspec doesnt-have-more-than-one-oneeuro-coin
  {:max-size 1000 :num-tests 1000}
  (prop/for-all [money gen/s-pos-int]
    (max-number-coins-by-type? (change money) 100 1)))





(comment
(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(defspec first-element-is-min-after-sorting ;; the name of the test
         100 ;; the number of iterations for test.check to test
         (prop/for-all [v (gen/not-empty (gen/vector gen/int))]
           (= (apply min v)
              (first (sort v)))))

(defspec sort-idempotent-prop
  100
  (prop/for-all [v (gen/vector gen/int)]
    (= (sort v) (sort (sort v)))))

)
