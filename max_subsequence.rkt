#lang racket

#|

  CS3383 Assignment 6
  Mar.1/19
  Avery Briggs
  3471065

  Simple program to find the maximum
  consecutive subsequence of an array
  of integers. Runs in O(n).

|#

(define (maxSum arr)
  (cond
    [(empty? arr) (cons 0 (cons empty empty))]
    [else
     (let ([max_so_far -9999999]
           [max_ending_here 0]
           [acc (list-ref arr 0)])
       (for ([i arr])
         (set! max_ending_here (+ max_ending_here i))
         (if (< max_so_far max_ending_here) (begin (set! acc (cons i acc)) (set! max_so_far max_ending_here)) (set! acc (cons i acc)))
         (if (< max_ending_here 0) (begin (set! acc empty) (set! max_ending_here 0)) max_ending_here))
       (for ([j acc])
         (if (not (= (sum acc) max_so_far)) (set! acc (rest acc)) acc))
       (cons max_so_far (cons (reverse acc) empty)))]))

(define (sum a)
  (foldl (lambda (x acc) (+ x acc)) 0 a))

(module+ test
  (require rackunit)
  (define a '(5 -8 7 41 12 0))
  (define b '(-2 -3 4 -1 -2 1 5 -1 -1 -1 -1 -1 -1))
  (define c '(-1 -1 -1 -1 -1 -1 -1 -1 -1 -4 -1))
  (define d '(-2 -3 4 -1 -2 1 5 -1 -1 -1 -1 -3 40))
  (define e '(-2 -3 4 -1 -2 1 5 -1 -1 -1 -1 -4 40))
  (check-equal? (maxSum a) '(60 (7 41 12 0)))
;(sum a)
  (check-equal? (maxSum b) '(7 (4 -1 -2 1 5)))
  (check-equal? (maxSum c) '(-1 ()))
  (check-equal? (maxSum d) '(40 (4 -1 -2 1 5 -1 -1 -1 -1 -3 40)))
  (check-equal? (maxSum e) '(40 (40)))
  (check-equal? (maxSum empty) '(0 ())))
;(sum b)
