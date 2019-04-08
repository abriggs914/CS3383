#lang racket/base
(require plot)


(plot (list (function (lambda (n) (* 2 (* n n))) #:color 'red #:label "2n^2") 
            (function (lambda (x) (* (* x x) x)) #:color 'blue #:label "n^3"))
      #:x-min 1 #:x-max 10
      #:x-label "n"
      #:y-label ""
      #:height 500
      #:width  800
      #:out-file "lin-vs-quad.pdf"
      #:out-kind 'pdf)

