package Chapter8_Customizing_Concurrency_6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// using thread factory to generate custom threads
// in ForkJoin pool
public class Main
{
	public static void main(String[] args) throws Exception
	{
		// create factory
		MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
		
		// create pool
		ForkJoinPool forkJoinPool = new ForkJoinPool(5, factory, null, false);
		
		// create task
		int[] array = new int[100000];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = 1;
		}
		MyRecursiveTask myRecursiveTask = new MyRecursiveTask(array, 0, array.length);
		
		// execute
		forkJoinPool.execute(myRecursiveTask);
		
		myRecursiveTask.join();
		
		forkJoinPool.shutdown();
		forkJoinPool.awaitQuiescence(1, TimeUnit.DAYS);
		
		System.out.printf("Main: result is %d\n", myRecursiveTask.get());
	}
}
