package Chapter4_Thread_Executors_5;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// run tasks after a delay
// using ScheduledExecutorService interface
public class Main
{
	public static void main(String[] args)
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		System.out.printf("Main: starting at %s\n", new Date());
		// create 5 tasks
		for (int i = 0; i < 5; i++)
		{
			Task task = new Task("Task-" + i);
			// delay to run
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		
		// shutdown after executing all tasks
		executor.shutdown();
		
		// wait for finalization
		try
		{
			// block here
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("Main: end at %s\n", new Date());
	}
}
