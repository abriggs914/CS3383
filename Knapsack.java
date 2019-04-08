// A Dynamic Programming based solution for 0-1 Knapsack problem
class Knapsack{

	// A utility function that returns maximum of two integers
	static int max(int a, int b){
		 return (a > b)? a : b;
	 }

// Returns the maximum value that can be put in a knapsack of capacity W
	static int knapSack(int W, int wt[], int val[], int n){
		int i, w;
		int K[][] = new int[n+1][W+1];

		// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++){   // rows
			for (w = 0; w <= W; w++){  //columns
				if (i==0 || w==0){ // create table indexes
					K[i][w] = 0;
				}
				else if (wt[i-1] <= w){
					K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
				}
				else{
					K[i][w] = K[i-1][w]; // value immediately above
				}
			}
		}

      for(int m = 0; m < K.length; m++){ // rows
       	for(int q = 0; q < K[m].length; q++){ //columns
        	System.out.print("k["+m+"]["+q+"]: " + K[m][q] + ", ");
        }
        System.out.println();
      }

			return K[n][W];
	}


	// Driver program to test above function
	public static void main(String args[]){
		int val[] = new int[]{4,7,1,5};
		int wt[] = new int[]{3,5,1,4};
		int W = 7;
		int n = val.length;
		System.out.println(knapSack(W, wt, val, n)); //weight limit, weight array, values array, number of items.
	}
}
/*This code is contributed by Rajat Mishra */
