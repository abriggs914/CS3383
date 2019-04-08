/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class StringCutting {
	public static void main (String[] args) {
		System.out.println("GfG!");
		 int n = 20;
        int[] m = new int[] { 3,8,10 };
        System.out.println(findMinCutCost(m, n));
        
        
		int[] l = {11,14,17,20,25};
		for(int i = 0; i < l.length; i++){
		    l[i]--;
		}
		n = 30;
		System.out.println("\tthe min cost is: " + findMinCutCost(l, n));
	}

    public static int findMinCutCost(int[] m, int n) {
        Arrays.sort(m);
        for(int i = 0; i < m.length; i++){
            System.out.println("m["+i+"]: " + m[i]);
        }
       int cost = n * m.length;
       for (int i=0; i<m.length; i++) {
          cost = Math.min(findMinCutCostImpl(m, n, i), cost);
       }
       return cost;
    }
    
    public static int findMinCutCostImpl(int[] m, int n, int i) {
       if (m.length == 1) return n;
       int cl = 0, cr = 0;
       if (i > 0) {
          cl = Integer.MAX_VALUE;
          int[] ml = Arrays.copyOfRange(m, 0, i);
          int nl = m[i];
          for (int j=0; j<ml.length; j++) {
             cl = Math.min(findMinCutCostImpl(ml, nl, j), cl);
          }
       }
       if (i < m.length - 1) {
          cr = Integer.MAX_VALUE;
          int[] mr = Arrays.copyOfRange(m, i + 1, m.length);
          int nr = n - m[i];
          for (int j=0; j<mr.length; j++) {
             mr[j] = mr[j] - m[i];
          }
          for (int j=0; j<mr.length; j++) {
             cr = Math.min(findMinCutCostImpl(mr, nr, j), cr);
          }
       }
       return n + cl + cr;
    }
}