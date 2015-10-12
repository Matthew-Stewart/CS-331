
(ns l_system_lab.student)

(defn remove-empties
  [v]
  (filter #(not= [] %) v))

;;
(defn get-xy-scale
  "Get the scaling factor for the coordinates."
  ([v]
   (if (= (count v) 1)
    (get-xy-scale v
                  (min (nth (nth v 0) 1) (nth (nth v 0) 3))
                  (max (nth (nth v 0) 1) (nth (nth v 0) 3))
                  (min (nth (nth v 0) 2) (nth (nth v 0) 4))
                  (max (nth (nth v 0) 2) (nth (nth v 0) 4)))
   ;;else
    (get-xy-scale v
                  (min (nth (nth v 0) 1) (nth (nth v 0) 3) (nth (nth v 1) 1) (nth (nth v 1) 3))
                  (max (nth (nth v 0) 1) (nth (nth v 0) 3) (nth (nth v 1) 1) (nth (nth v 1) 3))
                  (min (nth (nth v 0) 2) (nth (nth v 0) 4) (nth (nth v 1) 2) (nth (nth v 1) 4))
                  (max (nth (nth v 0) 2) (nth (nth v 0) 4) (nth (nth v 1) 2) (nth (nth v 1) 4)))
     )
   ;;{:scale (/ 480 (max (- max-x min-x) (- max-y min-y))) :min-x min-x :min-y min-y})
   ;;(get-xy-scale v min-x min-y max-x max-y)
   )
  ([v min-x max-x min-y max-y]
     {:scale (/ 480 (max (- max-x min-x) (- max-y min-y))) :min-x min-x :min-y min-y}))

(get-xy-scale '([:line 30 0 50 -50]))

;; Student function: scale-turtle

;;

;; Input: a list of [:line a b c d] vectors.  Some of the vectors may be empty: []

;; Output: a list that has been centered about coordinates 250,250, scaled to size 480x480.

;; I.e. '([:line 30 0 50 -50] [])  would be converted to '([10 250 480 10])

;;

;; You will need to rewrite this.  This code is just for show.

(defn scale-turtle
  "Normalizes a list of [:line ... ] vectors."
  ([v]

   (scale-turtle (remove-empties v) (get-xy-scale (remove-empties v)) []))
  ([v scale out]
      (if (= (count v) 1)
         (list (into [] (list (nth (nth v 0) 0)
                       (-> (nth (nth v 0) 1) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                       (-> (nth (nth v 0) 2) (- (:min-y scale)) (* (:scale scale)) (+ 10))
                       (-> (nth (nth v 0) 3) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                       (-> (nth (nth v 0) 4) (- (:min-y scale)) (* (:scale scale)) (+ 10)))))
      ;;else
         (list (into [] (list (nth (nth v 0) 0)
                              (-> (nth (nth v 0) 1) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 0) 2) (- (:min-y scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 0) 3) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 0) 4) (- (:min-y scale)) (* (:scale scale)) (+ 10))))
               (into [] (list (nth (nth v 1) 0)
                              (-> (nth (nth v 1) 1) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 1) 2) (- (:min-y scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 1) 3) (- (:min-x scale)) (* (:scale scale)) (+ 10))
                              (-> (nth (nth v 1) 4) (- (:min-y scale)) (* (:scale scale)) (+ 10)))))
      )
  )
)


(defn transform
  [init-pat rules]
  (cond (empty? init-pat) nil
        (empty? rules) init-pat

  )

  (flatten (replace rules init-pat))

)

;; # Some fractals to start out with.  Add some of your own!
