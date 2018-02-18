(ns clj-gol.core)

(def deltas [[-1, -1] [-1, 0] [-1 1] [0, -1] [0 1] [1 -1]  [1 0] [1 1]])

(defn- neighbours
  [[x y]]
  (map (fn [[dx dy]] [(+ x dx) (+ y dy)]) deltas))

(defn- is-valid-neighbour
  [[x y]]
  (and (< -1 x) (< -1 y)))

(defn- valid-neighbours
  [[x y]]
  (filter is-valid-neighbour (neighbours [x y])))

(defn- is-live
  [cell world]
  (some #(= cell %) world))

(defn- live-neighbours-count
  [[x y] world]
  (reduce +
    (map #(if (is-live %1 world) 1 0) (valid-neighbours [x y]))))

(defn- candidate-cells
  [world]
  (distinct
    (concat world
      (reduce concat (map valid-neighbours world)))))

(defn- in-next-gen
  [[x y] world]
  (let [live-count (live-neighbours-count [x y] world),
        live-cell (is-live [x y] world)]
    (cond
      (and live-cell (< live-count 2)) false
      (and live-cell (< live-count 4)) true
      (and live-cell (> live-count 3)) false
      (and (not live-cell) (== live-count 3)) true
      :else live-cell)))

(defn game-of-life
  [world]
  (let [candidates (candidate-cells world)]
    (sort (filter #(in-next-gen % world) candidates))))