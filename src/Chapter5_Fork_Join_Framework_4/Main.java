package Chapter5_Fork_Join_Framework_4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// throws exception in tasks
// only can throw unchecked exception
public class Main
{
	public static void main(String[] args)
	{
		ForkJoinPool pool = new ForkJoinPool();
		Task task = new Task(new int[100], 0, 100);
		
		pool.execute(task);
		pool.shutdown();
		
		// wait for termination
		try
		{
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// check if complete normally
		if (task.isCompletedAbnormally())
		{
			System.out.println("Main: an exception has occurred");
			// get exception
			System.out.printf("Main: %s\n\n", task.getException());
		}
		
		// join, now the exception will appear
		System.out.printf("Main: task result %d\n", task.join());
	}
}
