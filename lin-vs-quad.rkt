#lang racket/base
(require plot)


(plot (list (function (lambda (n) (+ 20 (* n 2))) #:color 'red #:label "2n+20") 
            (function (lambda (x) (expt x 2)) #:color 'blue #:label "n^2"))
      #:x-min 1 #:x-max 10
      #:x-label "n"
      #:y-label ""
      #:height 220
      #:width  300
      #:out-file "lin-vs-quad.pdf"
      #:out-kind 'pdf)

