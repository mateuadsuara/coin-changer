(ns coin-changer.core
  (:gen-class))

(def valid-coins [1 2 5 10 20 50 100 200])

(defn find-suitable-coin [money]
  (apply max (filter (fn [coin] (<= coin money)) valid-coins)))

(defn change [money]
  (if (= money 0)
    []
    (let [coin (find-suitable-coin money)]
      (conj (change (- money coin)) coin))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
