(ns clj-springlayout.core-test
  (:require [clojure.test :refer :all]
            [clj-springlayout.core :refer :all])
  (:refer-clojure :exclude [+ - * / max min])
  (:import [javax.swing Spring]))

(deftest test-spring
  (testing "Number"
    (let [s (spring 3)]
      (is (instance? Spring s))
      (is (= 3 (.getMinimumValue s)))
      (is (= 3 (.getValue s)))
      (is (= 3 (.getMaximumValue s)))))
  (testing "Sequential"
    (let [s (spring [1 2 3])]
      (is (instance? Spring s))
      (is (= 1 (.getMinimumValue s)))
      (is (= 2 (.getValue s)))
      (is (= 3 (.getMaximumValue s)))))
  (testing "Spring"
    (let [s (spring (spring 3))]
      (is (instance? Spring s))
      (is (= 3 (.getMinimumValue s)))
      (is (= 3 (.getValue s)))
      (is (= 3 (.getMaximumValue s))))))

(deftest test-+
  (testing "No args"
    (is (= nil (+))))
  (testing "single arg"
    (let [s (+ 2)]
      (is (instance? Spring s))
      (is (= 2 (.getMinimumValue s)))
      (is (= 2 (.getValue s)))
      (is (= 2 (.getMaximumValue s)))))
  (testing "multiple args"
    (let [s (+ 1 2 3)]
      (is (instance? Spring s))
      (is (= 6 (.getMinimumValue s)))
      (is (= 6 (.getValue s)))
      (is (= 6 (.getMaximumValue s))))))

(deftest test--
  (testing "No args"
    (is (= nil (-))))
  (testing "single arg"
    (let [s (- 2)]
      (is (instance? Spring s))
      (is (= -2 (.getMinimumValue s)))
      (is (= -2 (.getValue s)))
      (is (= -2 (.getMaximumValue s)))))
  (testing "multiple args"
    (let [s (- 1 -2 3 4)]
      (is (instance? Spring s))
      (is (= -4 (.getMinimumValue s)))
      (is (= -4 (.getValue s)))
      (is (= -4 (.getMaximumValue s))))))

(deftest test-*
  (testing "two numbers"
    (let [s (* 2 3)]
      (is (instance? Spring s))
      (is (= 6 (.getMinimumValue s)))
      (is (= 6 (.getValue s)))
      (is (= 6 (.getMaximumValue s)))))
  (testing "one number"
    (testing "first is a number"
      (let [s (* 2 (spring 3))]
        (is (instance? Spring s))
        (is (= 6 (.getMinimumValue s)))
        (is (= 6 (.getValue s)))
        (is (= 6 (.getMaximumValue s)))))
    (testing "last is a number"
      (let [s (* (spring 2) 3)]
        (is (instance? Spring s))
        (is (= 6 (.getMinimumValue s)))
        (is (= 6 (.getValue s)))
        (is (= 6 (.getMaximumValue s))))))
  (testing "no numbers"
    (is (thrown? AssertionError (* (spring 2) (spring 3))))))

(deftest test-division
  (testing "two numbers"
    (let [s (/ 7 2)]
      (is (instance? Spring s))
      (is (= 4 (.getMinimumValue s)))
      (is (= 4 (.getValue s)))
      (is (= 4 (.getMaximumValue s)))))
  (testing "one number"
    (testing "first is a number"
      (is (thrown? AssertionError (/ (spring 2) (spring 3)))))
    (testing "last is a number"
      (let [s (/ (spring 8) 2)]
        (is (instance? Spring s))
        (is (= 4 (.getMinimumValue s)))
        (is (= 4 (.getValue s)))
        (is (= 4 (.getMaximumValue s))))))
  (testing "no numbers"
    (is (thrown? AssertionError (/ (spring 2) (spring 3)))))
  (testing "division by zero"
    (is (thrown? ArithmeticException "Divide by Zero" (/ (spring 1) 0)))))

(deftest test-max
  (testing "No args"
    (is (= nil (max))))
  (testing "single arg"
    (let [s (max 2)]
      (is (instance? Spring s))
      (is (= 2 (.getMinimumValue s)))
      (is (= 2 (.getValue s)))
      (is (= 2 (.getMaximumValue s)))))
  (testing "multiple args"
    (let [s (max 1 3 2)]
      (is (instance? Spring s))
      (is (= 3 (.getMinimumValue s)))
      (is (= 3 (.getValue s)))
      (is (= 3 (.getMaximumValue s))))))
