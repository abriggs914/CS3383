import java.io.*;
// Java program to print largest contiguous array sum
import java.util.*;

class Kadane
{
	public static void main (String[] args)
	{
		int [] a = {-7, -3, -4, -1, -2, -1, -5, -3};
		for(int i = 0; i <  a.length; i++){
			System.out.println(a[i]);
		}
		System.out.println(Integer.MIN_VALUE);
		System.out.println("Maximum contiguous sum is " +
									maxSubArraySum(a));
	}

	static int maxSubArraySum(int a[])
	{
		int size = a.length;
		int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

		for (int i = 0; i < size; i++){
			max_ending_here = max_ending_here + a[i];
			if (max_so_far < max_ending_here){
				max_so_far = max_ending_here;
				System.out.println("a");
			}
			if (max_ending_here < 0){
				max_ending_here = 0;
				System.out.println("b");
			}
			System.out.println("max_so_far: " + max_so_far + "\tmax_ending_here: " + max_ending_here);
		}
		return max_so_far;
	}
}
