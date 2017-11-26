package ReentrantLock;



// why use reentrantlock? difference with synchronized:
// http://www.geeksforgeeks.org/reentrant-lock-java/
/*
 * the synchronized keyword is quite rigid in its use. For example, 
 * a thread can take a lock only once. Synchronized blocks don’t 
 * offer any mechanism of a waiting queue and after the exit of one 
 * thread, any thread can take the lock. This could lead to starvation 
 * of resources for some other thread for a very long period of time.
 */
public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Running example with fair-mode = false");
		// randomly select sleep thread
		testPrintQueue(false);
		
		System.out.println("Running example with fair-mode = true");
		// select thread wait for the longest period of time
		testPrintQueue(true);
	}
	
	private static void testPrintQueue(boolean fair)
	{
		PrintQueue printQueue = new PrintQueue(fair);
		
		// create 10 threads
		// share the same printQueue
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++)
		{
			threads[i] = new Thread(new Job(printQueue), "Thread-" + i);
			threads[i].start();
		}
		// join them
		for (int i = 0; i < 10; i++)
		{
			try
			{
				threads[i].join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}
