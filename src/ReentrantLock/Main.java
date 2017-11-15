package ReentrantLock;

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
