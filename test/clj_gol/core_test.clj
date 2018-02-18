(ns clj-gol.core-test
  (:require [clojure.test :refer :all]
            [clj-gol.core :refer :all]))

(deftest blinker
  (testing "One generation"
    (is (= (game-of-life [[1,3][2,3][3,3]]) [ [2,2][2,3][2,4] ])))
  (testing "Two generations"
    (is (= (game-of-life(game-of-life [[1,3][2,3][3,3]])) [[1,3][2,3][3,3]]))))