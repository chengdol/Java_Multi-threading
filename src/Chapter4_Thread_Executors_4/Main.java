package Chapter4_Thread_Executors_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

// send a list of tasks and wait for the finalization of all the tasks
// in the list
public class Main
{
	public static void main(String[] args)
	{
		ExecutorService executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		System.out.println("Main: starting...");
		// create 10 tasks
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++)
		{
			tasks.add(new Task("Task-" + i));
		}
		// create future object to associated with task
		List<Future<Result>> futures = null;
		
		try
		{
			// blocking here when running the tasks
			futures = executor.invokeAll(tasks);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// shutdown
		executor.shutdown();
		
		System.out.println("Main: printing result...");
		// print result of each object
		for (Future<Result> item: futures)
		{
			try
			{
				System.out.println(item.get().getName() + " - " + item.get().getValue());
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Main: end");
	}
}
