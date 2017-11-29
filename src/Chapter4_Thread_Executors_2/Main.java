package Chapter4_Thread_Executors_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


// run task in executor
// return result from task
// implement callable and future interface
public class Main
{
	public static void main(String[] args)
	{
		// pool with 2 threads
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);
		// Future object list
		List<Future<Integer>> resultList = new ArrayList<>();
		Random random = new Random();
		
		// 10 tasks
		for (int i = 0; i < 10; i++)
		{
			int number = random.nextInt(10);
			FactorialCalc task = new FactorialCalc(number);
			
			Future<Integer> resultFuture = executor.submit(task);
			// add into list
			resultList.add(resultFuture);
		}
		
		// monitor executor
		do
		{
			System.out.printf("Main: number of completed tasks %d\n",
					executor.getCompletedTaskCount());
			
			// check future finished or not
			int idx = 0;
			for (Future<Integer> task : resultList)
			{
				System.out.printf("Main: task %d state %s\n",
						idx++,
						task.isDone());
			}
			
			// wait
			try
			{
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while(executor.getCompletedTaskCount() < resultList.size());
		
		// print result
		System.out.println("Main: result:");
		int idx = 0;
		for (Future<Integer> task : resultList)
		{
			Integer number = null;
			try
			{
				number = task.get();
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
			System.out.printf("Main: task %d result %d\n",
					idx++,
					number);
		}
		
		// don't forget shutdown
		executor.shutdown();
	}
}
