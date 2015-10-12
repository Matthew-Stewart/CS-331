(ns traversal_lab.t-core
  (:use midje.sweet)
  (:use [traversal_lab.core]))

(defn num-solution-paths [maze loc]
  (count (filter #(get-solution (get-2d maze (loc 0) (loc 1)) %) [:n :s :e :w])))

(defn get-solution-dir [maze loc]
  (first (filter #(get-solution (get-2d maze (loc 0) (loc 1)) %) [:n :s :e :w])))

(defn solution-hits-wall [maze loc dir]
  (let [node (get-2d maze (loc 0) (loc 1))]
    (and (get-solution node dir)
         (get-wall node dir))))

(defn go-direction
  "Return the coordinates from a location and a direction."
  [[row col] dir]
  (case dir
    :n [(dec row) col]
    :s [(inc row) col]
    :e [row (inc col)]
    :w [row (dec col)]))

(defn maze-is-solved [maze start goal seen]
  (cond (= start goal) "solved"
        (seen start)   "solution loops"
        (> 1 (num-solution-paths maze start)) "too many solution paths"
        :else (let [dir (get-solution-dir maze start)]
                (cond
                 (solution-hits-wall maze start dir) "solution goes through wall"
                 :else (maze-is-solved maze (go-direction start dir) goal (conj seen start))))))

(facts "about mazes"
       (fact "solves maze"
             (maze-is-solved (solve-maze-dfs (generate-maze 50 50) [0 0] [49 49]) [0 0] [49 49] #{}) => "solved"))

(facts "about solve-maze-dfs"
       (let [m [[155 59] [204 102]]]
       (fact "solves a maze"
(solve-maze-dfs m [0 0] [1 1]) => [[1179 59] [716 102]])
       ))

(facts "about get-adjacent-nodes"
       (fact "gets adjacent nodes"
             (get-neighbors (generate-maze 3 3) [1 1]) => '([0 1] [2 1] [1 2] [1 0])))
