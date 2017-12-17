package Chapter7_Concurrent_Collections_1;

import java.util.concurrent.ConcurrentLinkedDeque;

// using none-blocking thread-safe deque
// ConcurrentLinkedDeque
// add 10000 elements
// poll 10000 elements from both head and tail
public class Main
{
	public static void main(String[] args)
	{
		ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
		// create 100 threads to do add task
		Thread[] threads = new Thread[100];
		for (int i = 0; i < 100; i++)
		{
			AddTask task = new AddTask(deque);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d add task threads have been launched\n", threads.length);
		
		// join them 
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
		// if use array list class, cannot get 10_0000 elements!
		System.out.printf("Main: %d elements in deque now\n", deque.size());
		
		// remove all elements in list
		for (int i = 0; i < threads.length; i++)
		{
			PollTask task = new PollTask(deque);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %s poll task threads have been launched\n", threads.length);
		
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
		System.out.printf("Main: %d elements in deque now\n", deque.size());
				
	}
}
