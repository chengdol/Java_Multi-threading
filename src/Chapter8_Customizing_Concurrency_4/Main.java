package Chapter8_Customizing_Concurrency_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// pass custom thread factory into executor
// why the print of thread after main printing??
// the thread printing is in run method..
public class Main
{
	public static void main(String[] args)
	{
		// create custom factory
		MyThreadFactory factory = new MyThreadFactory("Custom");
		
		// pass into utility method
		ExecutorService executor = Executors.newCachedThreadPool(factory);
		
		// submit task
		MyTask myTask = new MyTask();
		executor.submit(myTask);
		
		// shutdown
		executor.shutdown();
		
		// await
		try
		{
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		

		System.out.println("Main: end");
	}
}
