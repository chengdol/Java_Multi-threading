package Semaphore;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JobQueue
{
	private final Semaphore semaphore;
	private final boolean[] freePrinters;
	private final Lock lockPrinter;
	
	public JobQueue()
	{
		// create semaphore with 3 units
		// at most 3 threads can access shared resource 
		semaphore = new Semaphore(3);
		lockPrinter = new ReentrantLock();
		
		freePrinters = new boolean[3];
		for (int i = 0; i < 3 ;i++)
		{
			freePrinters[i] = true;
		}
	}
	
	public void printJob(Object doc)
	{
		try
		{
			// acquire semaphore
			semaphore.acquire();
			int assignedPrinter = getPrinter();
			
			long duration = (long)(Math.random() * 1000);
			// output
			System.out.printf("%s - %s: PrintQueue: Printing a job in printer %d during %d msec\n", new Date(), Thread.currentThread().getName(),
					assignedPrinter, duration);	
			TimeUnit.MILLISECONDS.sleep(duration);
			
			// release printer
			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// release semaphore
			semaphore.release();
		}
	}
	
	private int getPrinter()
	{
		int ret = -1;
		try
		{
			lockPrinter.lock();
			// return the first available printer
			for (int i = 0; i < 3; i++)
			{
				if (freePrinters[i])
				{
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}	
		} 
		finally
		{
			// always put unlock in finally block
			lockPrinter.unlock();
		}
		return ret;
	}
}
