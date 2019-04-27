#lang racket
 
(struct Q (x y) #:transparent)
 
(define-syntax-rule (lcons x y) (cons x (lazy y)))
 
(define (lazy-filter p? lst)
  (define flst (force lst))
  (if (null? flst) '()
      (let ([x (car flst)])
        (if (p? x)
            (lcons x (lazy-filter p? (cdr flst)))
            (lazy-filter p? (cdr flst))))))
 
(define (lazy-foldr f base lst)
  (define flst (force lst))
  (if (null? flst) base
      (f (car flst) (lazy (lazy-foldr f base (cdr flst))))))
 
(define (tails lst)
  (if (null? lst) '(())
      (cons lst (tails (cdr lst)))))
 
(define (safe? q1 q2)
  (match* (q1 q2)
    [((Q x1 y1) (Q x2 y2))
     (not (or (= x1 x2) (= y1 y2)
              (= (abs (- x1 x2)) (abs (- y1 y2)))))]))
 
(define (safe-lst? lst)
  (or (null? lst)
      (let ([q1 (car lst)])
        (for/and ([q2 (cdr lst)]) (safe? q1 q2)))))
 
(define (valid? lst) (andmap safe-lst? (tails lst)))
 
(define (nqueens n)
  (define all-possible-solutions
    (for/fold ([qss-so-far '(())]) ([row (in-range n)])
      (lazy-foldr
       (λ (qs new-qss)
         (append (for/list ([col (in-range n)]) (cons (Q row col) qs))
                 new-qss))
       '() qss-so-far)))
  (lazy-filter valid? all-possible-solutions))

;===========
 
(car (nqueens 4))
;; => (list (Q 7 3) (Q 6 1) (Q 5 6) (Q 4 2) (Q 3 5) (Q 2 7) (Q 1 4) (Q 0 0))

;---==
(define (force-and-print qs)
  (define forced (force qs))
  (unless (null? forced)
    (printf "~v\n" (car forced))
    (force-and-print (cdr forced))))
(force-and-print (nqueens 4))
; =>
;(list (Q 7 3) (Q 6 1) (Q 5 6) (Q 4 2) (Q 3 5) (Q 2 7) (Q 1 4) (Q 0 0))
;(list (Q 7 4) (Q 6 1) (Q 5 3) (Q 4 6) (Q 3 2) (Q 2 7) (Q 1 5) (Q 0 0))
;(list (Q 7 2) (Q 6 4) (Q 5 1) (Q 4 7) (Q 3 5) (Q 2 3) (Q 1 6) (Q 0 0))
;(list (Q 7 2) (Q 6 5) (Q 5 3) (Q 4 1) (Q 3 7) (Q 2 4) (Q 1 6) (Q 0 0))...
;(list (Q 7 5) (Q 6 3) (Q 5 6) (Q 4 0) (Q 3 2) (Q 2 4) (Q 1 1) (Q 0 7))
;(list (Q 7 3) (Q 6 6) (Q 5 4) (Q 4 1) (Q 3 5) (Q 2 0) (Q 1 2) (Q 0 7))
;(list (Q 7 4) (Q 6 6) (Q 5 1) (Q 4 5) (Q 3 2) (Q 2 0) (Q 1 3) (Q 0 7))