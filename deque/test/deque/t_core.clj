(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque]))

(facts "about push-front"
  (let [dq (Deque. '(1 2) '(3) 3)]
    (fact "Pushes elt to the front of our custom Deque."
      (push-front dq 4) => (Deque. '(4 1 2) '(3) 4)
    )
  )
)

(facts "about push-back"
  (let [dq (Deque. '(1 2) '(3) 3)]
    (fact "Pushes elt to the back of our custom Deque."
      (push-back dq 4) => (Deque. '(1 2) '(4 3) 4)
    )
  )
)

(facts "about flip-front"
  (let [dq (Deque. '() '(3 4) 2)]
    (fact "Flips back to front of our custom Deque."
      (flip-front dq) => (Deque. '(4 3) '() 2)
    )
  )
)

(facts "about flip-back"
  (let [dq (Deque. '(4 3) '() 2)]
    (fact "Flips front to back of our custom Deque."
      (flip-back dq) => (Deque. '() '(3 4) 2)
    )
  )
)

(facts "about front"
  (let [dq (Deque. '(1 2) '(3) 3)]
    (fact "Gets front of our custom Deque."
      (front dq) => 1
    )
  )
)

(facts "about back"
  (let [dq (Deque. '(1 2) '(3) 3)]
    (fact "Gets back of our custom Deque."
      (back dq) => 3
      (back (pop-back dq)) => 2
    )
  )
)

(facts "about pop-front"
  (let [dq (Deque. '(4 3) '() 2)]
    (fact "Pops first element from front of our custom Deque."
      (pop-front dq) => (Deque. '(3) '() 1)
    )
  )
)

(facts "about pop-back"
  (let [dq (Deque. '(4 3) '() 2)]
    (fact "Pops first element from back of our custom Deque."
      (pop-back dq) => (Deque. '() '(4) 1)
      (pop-back (pop-back (pop-back dq))) => (make-deque)
    )
  )
)