#include <stdio.h>
#include <stdlib.h>

int intervals(int a[], int n){
    int x = 0, y=0, i=0, j=0, k=0, h=0;
	        //printf("x: %d, y: %d, i: %d, j: %d, k: %d, h: %d\n",x,y,i,j,k,h);
    for(i = 1; i < n; i++){ //i -> 1 -> n 
        for(j = i; j < n; j++){ // j -> 1-> n
	        printf("x: %d, y: %d, i: %d, j: %d, k: %d, h: %d\n",x,y,i,j,k,h);
            x = 0;
            for(k = 1; k < j; k++){ // k -> 1-> k O(n^2)  
                x = x + a[k-1];
                for(h = k; h < i; h++){ // h -> k -> i O(n^2 * log(n))
	                //printf("h: %d, a[h]: %d\n", h, a[h-1]);
                    x = x * a[h-1];
                }
            }
            y = y + x;
        }
    }
    return y;
}

//((((n^2*log(n)) * n2) * (n-1)) * n) + c) => (n^2*log(n))*(n^4 - n^3) 

int main() {
	//code
	int a[] = {1,1,1,1,1};
	int n = 5;
	int res = intervals(a,n);
	printf("res: %d\n", res);
	n = 6;
	res = intervals(a,n);
	printf("res: %d\n", res);
	n = 7;
	res = intervals(a,n);
	printf("res: %d\n", res);
	return 0;
}