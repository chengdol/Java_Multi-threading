package ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue
{
	private Lock queueLock;

	public PrintQueue(boolean fair)
	{
		super();
		// create a reentrant lock
		this.queueLock = new ReentrantLock(fair);
	}
	
	public void printJob(Object doc)
	{
		// get the lock, otherwise thread sleep
		queueLock.lock();
		
		Long duration = (long)(Math.random() * 1000);
		System.out.println(Thread.currentThread().getName() + 
							": PrintQueue: print a job during " + 
							duration / 1000.0 + " second");
		try
		{
			Thread.sleep(duration);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// must unlock !
		finally 
		{
			queueLock.unlock();
		}
		
		// second part, repeat above work
		// used to see the difference when using different fair mode
		queueLock.lock();
		
		duration = (long)(Math.random() * 1000);
		System.out.println(Thread.currentThread().getName() + 
							": PrintQueue: print a job during " + 
							duration / 1000.0 + " second");
		try
		{
			Thread.sleep(duration);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// must unlock!
		finally 
		{
			queueLock.unlock();
		}
	}
}
