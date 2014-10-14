;;; Version 1.2
;;; 
;;; JRules Changes

;;; Reference Material
;;;
;;; http://www.angusj.com/sudoku/hints
;;; http://www.scanraid.com/BasicStrategies.htm
;;; http://www.sudokuoftheday.com/pages/techniques-overview
;;; http://www.sudokuonline.us/sudoku_solving_techniques
;;; http://www.sadmansoftware.com/sudoku/techniques.htm
;;; http://www.krazydad.com/blog/2005/09/29/an-index-of-sudoku-strategies/

;;; #######################
;;; DEFTEMPLATES & DEFFACTS
;;; #######################

(deftemplate possible
   (slot row)
   (slot column)
   (slot diagonal)
   (slot value)
   (slot group)
   (slot id))
   
(deftemplate impossible
   (slot id)
   (slot value)
   (slot rank)
   (slot reason))
   
(deftemplate technique-employed
   (slot reason)
   (slot rank))

(deftemplate technique
   (slot name)
   (slot rank))
   
(deftemplate size-value
   (slot size)
   (slot value))
   
(deftemplate iterate-rc
   (slot row)
   (slot column)
   (slot index))
   
(deftemplate rank
   (slot value)
   (slot process))
   
(deftemplate unsolved
   (slot row)
   (slot column))
   
(deftemplate acak
   (slot id)
   (slot value))
      
;;; ###########
;;; SETUP RULES
;;; ###########

;;; **********
;;; initialize
;;; **********

(defrule initialize

   =>

   (assert (phase grid-values))
   (assert (repeat 100))
   
   (assert (size-value (size 1) (value 1)))
   (assert (size-value (size 2) (value 2)))
   (assert (size-value (size 2) (value 3)))
   (assert (size-value (size 2) (value 4)))
   (assert (size-value (size 3) (value 5)))
   (assert (size-value (size 3) (value 6))))

;;; ***********
;;; stress-test
;;; ***********

(defrule stress-test
   
   (declare (salience 10))
   
   (phase match)
   
   (stress-test)
   
   (rank (value ?last))
   
   (not (rank (value ?p&:(> ?p ?last))))
   
   (technique (rank ?next&:(> ?next ?last)))
   
   (not (technique (rank ?p&:(> ?p ?last)&:(< ?p ?next))))
   
   =>
   
   (assert (rank (value ?next) (process yes))))
   
;;; *****************
;;; enable-techniques
;;; *****************

(defrule enable-techniques

   (declare (salience 10))
   
   (phase match)
   
   (size ?)
   
   (not (possible (value any)))
   
   (not (rank))
   
   =>
   
   (assert (rank (value 1) (process yes))))


;;; ****************
;;; expand-any-start
;;; ****************

(defrule expand-any-start

   (declare (salience 10))

   (phase expand-any)
   
   (possible (row ?r) (column ?c) (value any) (id ?id))
  
   (not (possible (value any) (id ?id2&:(< ?id2 ?id))))
      
   =>
      
   (assert (iterate-rc (row ?r) (column ?c) (index 1))))

;;; **********
;;; expand-any
;;; **********

(defrule expand-any

   (declare (salience 10))

   (phase expand-any)
   
   (possible (row ?r) (column ?c) (diagonal ?d) (value any) (group ?g) (id ?id))
  
   (not (possible (value any) (id ?id2&:(< ?id2 ?id))))
   
   (size ?s)
   
   ?f <- (iterate-rc (row ?r) (column ?c) (index ?v))
   
   (size-value (size ?as&:(<= ?as ?s)) (value ?v))
   
   (not (possible (row ?r) (column ?c) (value ?v)))
     
   =>
   
   (assert (possible (row ?r) (column ?c) (diagonal ?d) (value ?v) (group ?g) (id ?id)))
   
   (modify ?f (index (+ ?v 1))))
   
;;; *****************
;;; position-expanded
;;; *****************

(defrule position-expanded

   (declare (salience 10))

   (phase expand-any)
   
   ?f1 <- (possible (row ?r) (column ?c) (value any))
     
   (size ?s)
   
   ?f2 <- (iterate-rc (row ?r) (column ?c) (index ?v))
   
   (not (size-value (size ?as&:(<= ?as ?s)) (value ?v)))

   =>
   
   (assert (unsolved (row ?r) (column ?c)))
   
   (retract ?f1 ?f2))
   
;;; ###########
;;; PHASE RULES
;;; ###########

;;; ***************
;;; expand-any-done
;;; ***************

(defrule expand-any-done

   (declare (salience 10))

   ?f <- (phase expand-any)

   (not (possible (value any)))
   
   =>
   
   (retract ?f)
   
   (assert (phase initial-output))
   (assert (print-position 1 1)))
   
;;; ***********
;;; Cak-acak
;;; ***********

(defrule acak

   (declare (salience 21))
   
   (phase initial-output)
   
   ?f <- (repeat ?p&:(> ?p 0))
      
   =>
   
   (retract ?f)
   
   (assert (repeat (- ?p 1)))
   
   (assert (acak (id (+ 1 (mod (random) 36)))   (value (+ 1 (mod (random) 6))) )))

   
;;; ***********
;;; random !!!!!
;;; ***********

(defrule random-mode-on

   (declare (salience 20))
   
   (phase initial-output)
   
   (repeat 0)
   
   ?h <- (acak (id ?id) (value ?v))
   
   ?f <- (possible (id ?id) (value ?v) (row ?r) (column ?c) (group ?g) (diagonal ?d))
      
   =>
 
   (retract ?f ?h)
   
   (assert (possible (id ?id) (value ?v) (row ?r) (column ?c) (group ?g) (diagonal ?d))))


;;; ***********
;;; begin-match
;;; ***********

(defrule begin-match

   (declare (salience -20))
   
   ?f <- (phase initial-output)
   
   ?g <- (repeat 0)
      
   =>
   
   (retract ?f ?g)
   
   (assert (phase match)))

;;; *****************
;;; begin-elimination
;;; *****************

(defrule begin-elimination

   (declare (salience -20))
   
   ?f <- (phase match)
   
   (not (not (impossible)))
   
   =>
   
   (retract ?f)
   
   (assert (phase elimination)))

;;; ******************
;;; next-rank-unsolved
;;; ******************

(defrule next-rank-unsolved

   (declare (salience -20))
   
   (phase match)
   
   (not (impossible))
   
   (rank (value ?last))
   
   (not (rank (value ?p&:(> ?p ?last))))
   
   (technique (rank ?next&:(> ?next ?last)))
   
   (not (technique (rank ?p&:(> ?p ?last)&:(< ?p ?next))))
   
   (exists (unsolved))
      
   =>
      
   (assert (rank (value ?next) (process yes))))

;;; **********************
;;; next-rank-not-unsolved
;;; **********************

(defrule next-rank-not-unsolved

   (declare (salience -20))

   (phase match)
   
   (not (impossible))
   
   (rank (value ?last))
   
   (not (rank (value ?p&:(> ?p ?last))))
   
   (technique (rank ?next&:(> ?next ?last)))
   
   (not (technique (rank ?p&:(> ?p ?last)&:(< ?p ?next))))
   
   (not (unsolved))
   
   =>
      
   (assert (rank (value ?next) (process no))))
   
;;; ************
;;; begin-output
;;; ************
(defrule begin-count
   
   (declare (salience -20))
   ?f <- (phase match)
   (not (impossible))
   (rank (value ?last))
   (not (rank (value ?p&:(> ?p ?last))))
   (not (technique (rank ?next&:(> ?next ?last))))
   (not (count ?c))
   =>
   (retract ?f)
   (assert (phase count))
   (assert (count 0)))
   
(defrule count-possible
   (declare (salience 20))
   (phase count)
   (possible (id ?id) (value ?v))
   (not (possible (id ?id) (value ~?v)))

   (not (mark ?id))
   ?f <- (count ?c)
   =>
   (assert (mark ?id))
   (assert (count (+ ?c 1)))
   (retract ?f))
   
(defrule begin-output

   (declare (salience -20))   
   ?f <- (phase count)
   (count 36)
   =>
   (retract ?f)
   (assert (phase final-output))
   (assert (print-position 1 1)))
   
(defrule finish-count
   (declare (salience -22))
   ?f <- (phase count)
   (count ?c)
   =>
   (retract ?f)
   (assert (phase match))
   (reset))
   
   
   
   
   
   
   
