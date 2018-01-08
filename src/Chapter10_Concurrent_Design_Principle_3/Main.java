package Chapter10_Concurrent_Design_Principle_3;

import java.util.Date;

// compare atomic and lock
// just in some cases!

// you can define your own atomic type
// see textbook
public class Main
{
	public static void main(String[] args)
	{
		// create each object
		TaskAtomic atomic = new TaskAtomic();
		
		TaskLock lock = new TaskLock();
		
		// create 50 threads to do the jobs
		int num = 500;
		Thread[] threads = new Thread[num];
		Date begin, end;
		
		// atomic tasks
		begin = new Date();
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(atomic);
			threads[i].start();
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		end = new Date();
		System.out.printf("Main: task atomic execution time: %s millionsecnods\n"
				, end.getTime() - begin.getTime());
		
		// lock tasks
		begin = new Date();
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(lock);
			threads[i].start();
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		end = new Date();
		System.out.printf("Main: task lock execution time: %s millionsecnods\n"
				, end.getTime() - begin.getTime());
		
	}
}
