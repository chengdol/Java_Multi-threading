package Chapter4_Thread_Executors_7;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// cancel task
// using cancel() method
public class Main
{
	public static void main(String[] args)
	{
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		Task task = new Task();
		System.out.println("Main: executing the task...");
		Future<String> result = executor.submit(task);
		
		// sleep
		try
		{
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Main: cancel the task...");
		result.cancel(true);
		
		// check
		System.out.println("Main: canceled " + result.isCancelled());
		System.out.println("Main: done " + result.isDone());
		
		// shutdown
		executor.shutdown();
		System.out.println("Main: end");
		
	}
}
