(ns garmin-dashboards.core
  (:gen-class)  
  (:require [clojure.data.json :as json]
            [clojure.string :as str]))


(def activities-json (slurp "resources/activities.json"))
(def activity-json (slurp "resources/activity.json"))

(comment

  activities

  (def activities (json/read-str activities-json
                                 :key-fn keyword))

  (def activity (json/read-str activity-json
                               :key-fn keyword))

  activity

  (println (get-in activities [:activityId]))

  (println (get-in activity [:activityName]))

  (:activityName activity)

  (select-keys activity [:activityName])

  (map :activityName activity)
  (map :activityId activity)


  (map :deviceId activities)

  (filter #(= 3414608113 %) (map :deviceId activity))

  (filter #(= 3414608113 %) (first activity))


  (->> activities
       (map :deviceId)
       (filter #(= % 3414608113)))

  (select-keys activities 16322368640)

  (select-keys activity [::deviceId ::activityId])

  (filter #(= % 16322368640) (map :activityId activities))

  (select-keys activity (filter #(= % 16322368640) (map :activityId activities)))

  (map :deviceId (filter #(= % 3414608113) activity))

  (assoc {} (map :deviceId (filter #(= % 3414608113) activity)))

  (count (first activities))
  (count (second activities))
  (count (last activities))
  (count activities)


  (map :activityId (first activities))
  (map :activityId (last activities))
  (count (map :activityId activities))

  (:activityId activities)



  (defn all-actitivities [{:keys [activityID deviceID]}]

    (println activityID deviceID)

    #_(println k v))

  (all-actitivities activities)

  (map all-actitivities activities)

  (let [ids (mapv :activityId activities)
        devices (mapv :deviceId activities)]
    ;;(println ids devices)

    (-> {}
        (assoc :activityId ids)
        (assoc :deviceId devices)
        (println)))


  (let [ids (mapv :activityId activities)
        devices (mapv :deviceId activities)]

    (println ids devices))

  (mapv :activityId activities)
  (mapv :deviceId activities)

  (println (map new-map (mapv :activityId activities)))

  (defn new-map
    [x]
    (conj {} {:activityId x}))

  (map {}
       (conj {:activityId (mapv :activityId activities)})
       (conj {:deviceId (mapv :deviceId activities)}))


  (let [[one-elem] activity]

    (print one-elem))

  (let [[{ID :activityId
          device :deviceId}] activity]

    (println ID device))


  (let [[{:keys [activityId deviceId]}] activity]

    (println activityId deviceId))

  (let [[k] activities]
    #_(println (get k :activityId))
    #_(println (select-keys k [:activityId]))

    (map #(select-keys % [:activityId]) k))

  (map #(select-keys % [:activityId :deviceId]) activities)

  (defn map-kv [f coll]
    (reduce-kv (fn [m k v] (assoc m k (f v))) (empty coll) coll))

  (->> activities
       (map #(select-keys % [:activityId :deviceId :distance]))
       (filter #(= (:deviceId %) 3414608113))
       (map :distance)
       (reduce +))

  (->> activities
       (filter #(= (:deviceId %) 3414608113))
       (map :distance)
       (reduce +))

  (defn garmin-zanotto?
    [data]
    (= (:deviceId data) 3414608113))

  (def xf (comp (filter garmin-zanotto?)
                (filter #(= (get-year %) "2024"))
                (map :distance)))

  (format "%.3f" (/ (transduce xf + activities) 1000))

  (into [] xf activities)


  (mapv :deviceId activities)

  (let [[{:keys [activityId deviceId]}] activities]

    (println activityId deviceId))

  (defn filter-devices
    [[{:keys [activityId deviceId] :as all}]]

    (println (mapv :activityId all))

    (println activityId deviceId))

  (filter-devices activities)
  (mapv filter-devices activities)


  (def dist '({:distance 6999.5400390625}
              {:distance 4000.8798828125}
              {:distance 3010.889892578125}
              {:distance 1010.77001953125}
              {:distance 3006.52001953125}))

  dist
  (type dist)

  (println (merge {} dist))
  (reduce + dist)

  (defn red-func
    [[k v]]
    (println k v))

  (apply + dist)

  (let [[{ID :activityId
          device :deviceId}] activities]

    (println ID device))

  (defn all-actitivities [{:keys [activityID deviceID]}]

    (println activityID deviceID)

    #_(println k v))


  (peek activity)

  [:activityId activity]
  [:minElevation activity]
  (subvec activity :minElevation)

  (->> activities
       (map #(select-keys % [:activityId :deviceId :distance :startTimeGMT]))
       (filter #(= (:deviceId %) 3414608113))
       #_(map :startTimeGMT)
       (filter #(= (get-year %) "2024")))


  (defn get-year
    [year]
    (get (str/split (:startTimeGMT year) #"-") 0))
  
  (get-year "2023-03-06 23:05:15")
  
  )



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
