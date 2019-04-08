public class MatrixMultiplication{

	public static void main(String[] args){
		int[][] a = {{1,1,1},{1,1,1},{1,1,1}};
		int[][] b = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] c = {{-1,1,1,1,1},{1,-1,1,1,1},{1,1,-1,1,1},{1,1,1,-1,1},{1,1,1,1,-1}};
		int[][] d = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		int[][] e = {{8,-2,7,15},{-4,0,-4,1}};
		int[][] f = {{100,2},{-3,4},{0,7},{8,-9}};
		int[][] res;
		res = matrixMult(a, b);
		printMatrix(res);
		res = matrixMult(c, d);
		printMatrix(res);
		res = matrixMult(e, f);
		printMatrix(res);
		int[][] g = genRandomMatrix();
		int[][] h = genRandomMatrix();
		res = matrixMult(g, h);
		printMatrix(res);
		
		System.out.println("\tTranspose\n");
		printMatrix(d);
		matrixTransposeRecursive(d, 0,0, 5);
		printMatrix(d);
		
		System.out.println("\tAdd\n");
		printMatrix(a);
		printMatrix(b);
		printMatrix(matrixAdd(a, d));		
	}
	
	public static int[][] genRandomMatrix(){
		int r = (int) Math.ceil(Math.random() * 10);
		int c = (int) Math.ceil(Math.random() * 10);
		//System.out.println("{"+r+","+c+"}");
		int[][] res = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				int x = (int) Math.ceil(Math.random() * 3);
				int y = (int) Math.ceil(Math.random() * 10);
				res[i][j] = ((x == 1)? y : (-1 * y));
			}
		}
		return res;
	}
	
	public static void printMatrix(int[][] m){
		System.out.println("\nMatrix");
		String border = "";
		if(m.length == 0 || m[0].length == 0){
			System.out.println("Error, unable to print matrix.");
			return;
		}
		for(int i = 0; i < m[0].length; i++){
			System.out.print("\t " + (i + 1));
			border += "_________";
		}
		System.out.println("\n" + border);
		border = "";
		for(int i = 0; i < m.length; i++){
			System.out.print((i + 1) + "\t|");			
			for(int j = 0; j < m[i].length; j++){
				System.out.print(((j == 0)? "" : "\t") + m[i][j]); //"\tm["+i+"]["+j+"]: " +
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int[][] matrixMult(int[][] a, int[][] b){
		int[][] res = null;
		res = new int[1][1];
		int resMCols = b[0].length;
		int resMRows = a.length;
		int numMultiplications = 0;
		if(a[0].length != b.length){
			res[0][0] = 0;
			System.out.println("ERROR NON-MATCHING DIMENSIONS");
			return res;
		}
		res = new int[resMRows][resMCols];
		int sum = 0;
		for(int r = 0; r < resMRows; r++){
			for(int c = 0; c < resMCols; c++){
				for(int x = 0; x < b.length; x++){
					sum += a[r][x] * b[x][c];
					numMultiplications++;
				}
				res[r][c] = sum;
				sum = 0;
			}
		}
		System.out.println("INPUT DIMENSIONS: a: {" + a.length + "," + a[0].length+"}, b: {"+ b.length +","+ b[0].length+"}");		
		System.out.println("numMultiplications to compute: " + numMultiplications);
		return res;
	}
	
	public static void matrixTransposeRecursive(int[][] A, int r, int c, int s){
	    if(s == 1){
	        return;
	    }
	    else{
	        int x = (int) Math.floor(s / 2);
	        matrixTransposeRecursive(A, r, c, x);
	        matrixTransposeRecursive(A, r + x, c + x, s - x);
	        matrixTransposeSwap(A, r, c + x, r + x, c, x, s - x);
	    }
	}
	
	public static void matrixTransposeSwap(int[][] A, int r1, int c1, int r2, int c2, int s1, int s2){
	    if(s1 < s2){
	        matrixTransposeSwap(A, r2, c2, r1, c1, s2, s1);
	    }
	    else if(s1 == 1){
	        int temp = A[r1][c1];
	        A[r1][c1] = A[r2][c2];
	        A[r2][c2] = temp;
	    }
	    else{
	        int x = (int) Math.floor(s1 / 2);
	        matrixTransposeSwap(A, r2, c2, r1, c1, s2, x);
	        matrixTransposeSwap(A, r2, c2 + x, r1 + x, c1, s2, s1 - x);
	    }
	}
	
	public static int[][] matrixAdd(int[][] a, int[][] b){
		int ar = a.length;
		int br = b.length;
		int[][] res = new int[0][0];
		if(ar == 0 || br == 0){
			return res;
		}
		else{
			int ac = a[0].length;
			int bc = b[0].length;
			if (ac != bc || ar != br || ac != br){
				return res;
			}
			else{
				res = new int[ar][ac];
				for(int i = 0; i < ar; i++){
					for(int j = 0; j < ac; j++){
						res[i][j] = a[i][j] + b[i][j];
					}
				}
				return res;
			}
		}
	}
}