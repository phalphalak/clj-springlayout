(ns clj-springlayout.examples
  (:import [javax.swing JFrame JButton])
  (:require [clj-springlayout.core :refer :all])
  (:gen-class))


(defn example1 []
  (let [frame (JFrame. "example 1")
        content-pane (.getContentPane frame)
        button1 (JButton. "left")
        button2 (JButton. "middle")
        button3 (JButton. "right")
        button4 (JButton. "bottom right")
        button5 (JButton. "full width")
        layout (->layout)]
    (doto content-pane
      (.setLayout layout)
      (.add button1)
      (.add button2)
      (.add button3)
      (.add button4)
      (.add button5))
    (doto layout
      (put :west button1 5 :west content-pane)
      (put :west button2 5 :east button1)
      (put :west button3 5 :east button2)
      (put :east content-pane 5 :east button3)
      (put :north button1 5 :north content-pane)
      (put :baseline button2 0 :baseline button1)
      (put :baseline button3 0 :baseline button1)
      (put :north button4 5 :south button1)
      (put :east button4 0 :east button3)
      (put :north button5 5 :south button4)
      (put :west button5 5 :west content-pane)
      (put :east button5 -5 :east content-pane)
      (put :south content-pane 5 :south button5))
    (doto frame
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true))))

(defn -main [demo & _]
(prn demo)
  (case demo
    "1" (example1)))
