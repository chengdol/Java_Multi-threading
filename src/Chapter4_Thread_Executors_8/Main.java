package Chapter4_Thread_Executors_8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// control the finish of a task
// FutureTask and override done() method
// cancel() can only cancel task not start yet or in progress
// isCancelled() return true if task was cancelled before it completed normally
public class Main
{
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		
		System.out.println("Main: starting...");
		// create 5 tasks
		ResultTask[] tasks = new ResultTask[5];
		for (int i = 0; i < tasks.length; i++)
		{ 
			tasks[i] = new ResultTask(new ExecutableTask("Task-" + i));
			// submit callable task
			executor.submit(tasks[i]);
		}
		
		// wait
		try
		{
			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// cancel tasks
		for (int i = 0; i < tasks.length; i++)
		{
			tasks[i].cancel(true);
		}
		
		// write result of completed tasks
		for (int i = 0; i < tasks.length; i++)
		{
			if (!tasks[i].isCancelled())
			{
				try
				{
					System.out.printf("Main: task result: %s\n",
							tasks[i].get());
				} catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}
		// shutdown
		executor.shutdown();
		System.out.println("Main: end");
	}
}
