import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Globals {
}

class ParFib extends RecursiveTask<Long>{
    Long n;
    ParFib(Long param) {
		n = param;
    }

    protected Long compute() {
		if (n <= 1) {
			return n;
		} 
		else{
			ParFib left = new ParFib(n-1);
			left.fork();
			ParFib right = new ParFib(n-2);
					
			Long y = right.compute();
			Long x = left.join();
			return x + y;
		}	    
    }
	
    public static void main(String [] args) {
		ForkJoinPool pool = new ForkJoinPool();

		Long num = (long) 10;
		//num = Long.parseLong(args[0]);
		Long result = pool.invoke (new ParFib(num));
		System.out.println(result);
    }
}