/*package whatever //do not write package name here */

import java.io.*;

class FibDPvsREC {

    public static int n;
    public static final int MAX = 100;
    public static final int NIL = -1;
    public static int[] F;

	public static void main (String[] args) {
// 		System.out.println("GfG!");
        n = 0;
            fibDPHelper(i);
            fibRecHelper(i);
        // for(int i = 0; i < 10; i++){
        //     fibDPHelper(i);
        //     fibRecHelper(i);
        // }
	}

	public static void fibDPHelper(int n){
	    F = new int[MAX];
        _initialize();
	    System.out.println("the " + n + "th digit in the fibonacci sequence is:");
	    System.out.println(fibDP(n));
	}

	public static void fibRecHelper(int n){
	   // F = new int[MAX];
        // _initialize();
	    System.out.println("the " + n + "th digit in the fibonacci sequence is:");
	    System.out.println(fibRec(n));
	}

	public static void _initialize(){
        for (int i = 0; i < MAX; i++){
            F[i] = NIL;
        }
    }

	public static int fibDP(int n){
	    if (F[n] == NIL){
            if (n <= 1) {
                F[n] = n;
            }
            else{
                F[n] = fibDP(n-1) + fibDP(n-2);
            }
	    }
        return F[n];
    }

    public static int fibRec(int n){
        if(n == 0){
            return 0;
        }
	    else if (n <= 2){
            return 1;
        }
        else{
            return fibRec(n-1) + fibRec(n-2);
        }
    }
}
