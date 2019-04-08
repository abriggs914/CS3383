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
}