package Chapter4_Thread_Executors_6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// periodic task dome
// using scheduleAtFixedRate() method
public class Main
{
	public static void main(String[] args)
	{
		// ScheduledExecutorService is implemented by ScheduledThreadPoolExecutor class
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		System.out.printf("Main: starting at %s\n", new Date());
		// create one task
		Task task = new Task("Task");
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		
		// write the time remaining to next execution
		for (int i = 0; i < 28; i++)
		{
			// write the left time to next execution
			System.out.printf("Main: delay to next execution %s\n",
					result.getDelay(TimeUnit.MILLISECONDS));
			
			// wait 250 msec
			try
			{
				TimeUnit.MILLISECONDS.sleep(250);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// shutdown after current task finished
		System.out.println("Main: shutdown...");
		executor.shutdown();
		
		// wait 4 sec to verify task is done
		try
		{
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.printf("Main: task is done %s\n", result.isDone());
		System.out.printf("Main: end at %s\n", new Date());
	}
}
