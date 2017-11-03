package Group_threads_and_processing_uncontroller_exceptions;


public class Main
{
	// main thread
	public static void main(String[] args)
	{
		int numOfThreads = 2 * Runtime.getRuntime().availableProcessors();
		
		MyThreadGroup threadgroup = new MyThreadGroup("ThreadGroup");
		Runnable task = new Task();
		
		// bind into group and start threads
		for (int i = 0; i < numOfThreads; i++)
		{
			Thread t = new Thread(threadgroup, task);
			t.start();
		}
		
		// write thread group info at console
		System.out.printf("Number of threads: %d\n", threadgroup.activeCount());
		System.out.println("info about thread group:");
		threadgroup.list();
		
		// print status of threads
		Thread[] threads = new Thread[threadgroup.activeCount()];
		threadgroup.enumerate(threads);
		for (Thread t : threads)
		{
			System.out.printf("Thread name: %s, state: %s\n", t.getName(), t.getState());
		}
	}
}
