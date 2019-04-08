/*
  CS3383 Assignment 6
  Mar.1/19
  Avery Briggs
  3471065

  Simple program to find the maximum
  consecutive subsequence of an array
  of integers. Runs in O(n).
*/

import java.util.Arrays;

public class LongestSubsequence{

  public static void main(String[] args) {
      int[] a = {1, 2, 3, 5}; // {1, 2, 3, 5}
      int[] b = {-2, -3, 4, -1, -2, 1, 5, -1, -1, -1, -1, -1, -1}; // {4, -1, -2, 1, 5}
      int[] c = {}; // {}
      int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -1}; // {-1}
      int[] e = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -1, 0, 1, -1}; // {0, -1}
      int[] f = {-2, -2, -2, -2, -2, -1}; // {-1}
      int[] g = {-1, -1, -1, -1, -1, 0}; // {0}
      int[] h = {0, -1, -1, -1, -1, -1}; // {0}
      System.out.println("\nTest a: ");
      show(a);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(a));

      System.out.println("\nTest b: ");
      show(b);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(b));

      System.out.println("\nTest c: ");
      show(c);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(c));

      System.out.println("\nTest d: ");
      show(d);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(d));

      System.out.println("\nTest e: ");
      show(e);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(e));

      System.out.println("\nTest f: ");
      show(f);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(f));

      System.out.println("\nTest g: ");
      show(g);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(g));

      System.out.println("\nTest h: ");
      show(h);
      System.out.println("maximum consecutive subsequence: ");
      show(longestSubsequence(h));
  }

  // longestSubsequence takes in an int array and returns the longest path
  // made by the maximum sum of consecutive integers in the given array.
  // Runs in linear time as the size of the given array changes O(n).
  public static int[] longestSubsequence(int[] arr){
    int len = arr.length;
    int currMax = -9999999, lastIndex = 0;
    int i, j, k;
    int[] temp = new int[len]; // initializes new array of size n
    for(i = 0, j = 0; i < len; i++, j++){ // for loop runs at most len (n) times
      lastIndex += arr[i];
      if(currMax < lastIndex){
        currMax = lastIndex; // currMax is updated to sum of lastIndex visited
      }
      if(lastIndex < 0){ // addition of lastIndex is now negative, reset temp
        lastIndex = 0;
        temp = new int[len-j];
        j = -1;
      }
      else{
        temp[j] = arr[i];
      }
    }
    len = temp.length;
    if(len == 0){
      return new int[0]; // empty array given, therefore returned
    }
    int[] res = new int[len]; // initializes new array of size n
    j = 0;
    if(currMax < 0){
      for(i = 0; i < arr.length; i++){ // for loop runs at most len (n) times
        if(arr[i] == currMax){
          res[j] = arr[i]; //locate and return negative int with least magnitude
        }
      }                                      // all integers < 0 in given array
      return Arrays.copyOfRange(res, 0, j+1);// returns greatest single integer
    }
    k = temp[0];
    res[j] = k; //
    j++;
    for(i = 1; i < len; i++){ // for loop runs at most len (n) times
      if(k >= currMax){
        break;
      }
      else{
        res[j] = temp[i]; // remove negatives from end of max path. Extras
        j++;              // were added to temp array due to lastIndex still
        k += temp[i];     // greater than 0 as list sum < currMax value.
      }
    }
    return Arrays.copyOfRange(res, 0, j);
  }

// show is called with an int[] array and displays it in a format
  public static void show(int[] arr){
    System.out.print("{");
    for(int i = 0; i < arr.length; i++){
      if(i < arr.length-1){
        System.out.print(arr[i] + ", ");
      }
      else{
        System.out.print(arr[i]);
      }
    }
    System.out.println("}");
  }

}
