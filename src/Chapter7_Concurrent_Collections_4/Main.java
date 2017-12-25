package Chapter7_Concurrent_Collections_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main
{
	public static void main(String[] args)
	{
		DelayQueue<Event> dq = new DelayQueue<>();
		
		// create 5 threads
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++)
		{
			Task task = new Task(i, dq);
			threads[i] = new Thread(task);
		}
		
		// run
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].start();
		}
		
		// wait for joining 
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
		
		// write to the console the event stored in the delayed queue
		do
		{
			int counter = 0;
			// 由于在task中event是一批同一个start date
			// 所以这里一次可能读取一批event
			while (dq.poll() != null)
			{
				counter++;
			}
			System.out.printf("Main: now DelayedQueue is empty, at %s read %d events\n"
					, new Date()
					, counter);
			// sleep
			try
			{
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			// queue size represents all elements regardless of activation date
		} while (dq.size() > 0);
		
		System.out.println("Main: end");
	}
}
