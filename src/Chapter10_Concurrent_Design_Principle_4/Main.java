package Chapter10_Concurrent_Design_Principle_4;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main
{
	public static void main(String[] args)
	{
		// using threads
		Thread[] threads = new Thread[1000];
		Date beg, end;
		
		beg = new Date();
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(new Task());
			threads[i].start();
			// 这里不能跟着join!
			// 否则成了等待了
		}
		for (int i = 0; i < threads.length; i++)
		{
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		end = new Date();
		
		System.out.printf("Main: using threads manually execution time %s\n"
				, end.getTime() - beg.getTime());
		// using executor
		
		// 有点忘了
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		beg = new Date();
		for (int i = 0; i < threads.length; i++)
		{
			executor.execute(new Task());
		}
		executor.shutdown();
		
		try
		{
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		end = new Date();
		System.out.printf("Main: using executors execution time %s\n"
				, end.getTime() - beg.getTime());
	}
}
