package Chapter8_Customizing_Concurrency_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// 这个例子主要是override一些ThreadPoolExecutor class
// 中的方法
// beforeExecute()
// afterExecute()
// shutDown()
// shutDownNow()
public class Main
{
	public static void main(String[] args)
	{
		// create my executor
		MyExecutor executor = new MyExecutor(4, 8, 1000
				, TimeUnit.MILLISECONDS
				, new LinkedBlockingDeque<>());
		
		List<Future<String>> results = new ArrayList<>();
		// create 10 tasks
		for (int i = 0; i < 10; i++)
		{
			SleepTask sleepTask = new SleepTask();
			// submit it
			Future<String> future = executor.submit(sleepTask);
			// add into list
			results.add(future);		
		}
		
		// get first 5 results
		for (int i = 0; i < 5; i++)
		{
			try
			{
				String res = results.get(i).get();
				System.out.printf("Main: task %d result is %s\n"
						, i
						, res);
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		
		// shutdown
		executor.shutdown();
		// get last 5
		for (int i = 5; i < 10; i++)
		{
			try
			{
				String res = results.get(i).get();
				System.out.printf("Main: task %d result is %s\n"
						, i
						, res);
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		
		// wait for completion of the executor
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
