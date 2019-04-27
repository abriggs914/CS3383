#lang racket/base
(require plot)

(parameterize ([plot-y-transform log-transform]
               [plot-y-ticks (log-ticks)])
  (plot (list (function (lambda (x) (expt x 10)) #:label "y=x^10") 
              (function (lambda (x) (expt 1.01 x)) #:color 'blue #:label "y= 1.01^x")
              ;(function (lambda (x) (expt 1.05 x)) #:color 'green #:label "y=1.05 ^x")
              )
        #:x-min 1 #:x-max 20000
       ; #:y-min 1 #:y-max 20000
        
	#:x-label "n"
	#:y-label "Log-transform"
	#:height 250
	#:width  350
        #:out-file "exp-vs-poly.pdf"; Log-transform
        #:out-kind 'pdf))

