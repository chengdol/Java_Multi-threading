package Chapter7_Concurrent_Collections_3;

import java.util.concurrent.PriorityBlockingQueue;

// perform ProirityBlockingQueue
// using 5 threads add event into the queue
// sort by priority of each event
public class Main
{
	public static void main(String[] args)
	{
		PriorityBlockingQueue<Event> pq = new PriorityBlockingQueue<>();

		// create 5 threads
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++)
		{
			Task t = new Task(i, pq);
			threads[i] = new Thread(t);
		}

		// run tasks
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].start();
		}

		// join
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

		// check
		System.out.printf("Main: queue size is %d\n", pq.size());
		for (int i = 0; i < threads.length * 1000; i++)
		{
			Event e = pq.poll();
			System.out.printf("Event: thread %d with priority %d\n", e.getThread(), e.getPriority());
		}
		System.out.printf("Main: queue size is %d\n", pq.size());

	}
}
