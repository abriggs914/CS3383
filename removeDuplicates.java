import java.io.*;

class removeDuplicates {
    
	static boolean sorted = false;
	static int size = 0;
    
	public static void main (String[] args) {
		//int[] arr = {1,1,2,2,3,3};
		//int[] arr = {1,3,4,5,1,2,4,5,1,2,11,32,54,77,12,11,4,5,77,5,4,1,2,5,4,4,5,4,5,4,5,6,88,8988,86,65,55222,4,5,4,666,7};
		//int[] arr = {1,2,3,3,3,3,3,3,3,3,3};
		int[] arr = {-1,0,1,3,5};
		//int[] arr = {0};
		show(arr);
        System.out.println(searchIndicies(arr,0,arr.length));
        System.out.println(dist(arr,arr[4],arr[0]));
        show(removeDuplicates(arr));
	}
	
	public static int searchIndicies(int[] a, int low, int high){
	    int i = (high + low)/2;
	    //System.out.println("i: " + i + ", high: " + high+ ", low: " + low);
	    if(a[i] == i){
	        return i;
	    }
	    else if(a[i] < i){
	        if(i == 0 || low == high){
	            return -1;
	        }
	        return searchIndicies(a, low, i);
	    }
	    else{
	        if(i == a.length-1 || low == high){
	            return -1;
	        }
	        return searchIndicies(a, i+1, high);
	    }
	}
	
	public static int[] removeDuplicates(int[] arr){
		sort(arr);
		int c = 1;
		int[] b = new int[arr.length];
		if(arr.length <= 1){
		    size = arr.length;
		    return arr;
		}
		int i, j = arr[0];
		b[0] = arr[0];
		for(i = 1; i < arr.length; i++){
		    if(arr[i] == j){
		        //System.out.println(arr[i]);
		    }
		    else{
		        //System.out.println("j: " + j + " arr["+ i + "]: " + arr[i]);
		        b[c-1] = j;
		        j = arr[i];
		        c++;
		    }
		    if(i == arr.length-1){
		        b[c-1] = j;
		        //System.out.println("C: " + c);
		    }
		}
		size = c;
		return b;
	}
	
	public static int dist(int[] a, int p, int q){
	    int first = 0, second = 0, i, n = a.length;
	    for(i = 0; i < n; i++){
	        if(a[i] == q){
	            first = i;
	            break;
	        }
	    }
	    for(i = n-1; i > 0; i--){
	        if(a[i] == p){
	            second = i;
	            break;
	        }
	    }
	    System.out.println("first: " + first + ", second: " + second);
	    return second - first;
	}
		
	public static void mergeSort(int[] arr, int l, int r){ 
		if(l < r){ 			
			int m = (l + r)/2;
			mergeSort(arr, l, m); 
			mergeSort(arr , m + 1, r); 			 
			merge(arr, l, m, r); 
		}
	} 
	
	public static void sort(int[] arr){
		int n = arr.length;
		mergeSort(arr, 0, n - 1);
		sorted = true;
	}
	
	
	public static void show(int[] A){
		int i, n = A.length;
		if(!sorted){
			System.out.print("A: {");
		}
		else{
			System.out.print("Merge Sort\nA: {");			
			n = size;
		}
		for(i = 0; i < n; i++){
			System.out.print(A[i]);
			if(i < n - 1){
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}	
	
	public static void merge(int[] arr, int l, int m, int r){ 
		int nLeft = m - l + 1; 
		int nRight = r - m; 
		int i = 0, j = 0, k; 
		int[] left = new int[nLeft]; 
		int[] right = new int[nRight]; 
		for(i = 0; i < nLeft; i++){
			left[i] = arr[l + i]; 
		}
		for(j = 0; j < nRight; j++){
			right[j] = arr[m + 1 + j];
		} 
		i = 0;
		j = 0;
		k = l; 
		while(i < nLeft && j < nRight){ 
			if(left[i] <= right[j]){ 
				arr[k] = left[i]; 
				i++; 
			} 
			else{ 
				arr[k] = right[j]; 
				j++; 
			} 
			k++; 
		} 
		while(i < nLeft){ 
			arr[k] = left[i]; 
			i++; 
			k++; 
		} 
		while(j < nRight){ 
			arr[k] = right[j]; 
			j++; 
			k++; 
		}
	}
}