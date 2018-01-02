package Chapter8_Customizing_Concurrency_3;


// implements thread factory interface
// assign thread prefix and sequence number
public class Main
{
	public static void main(String[] args)
	{
		// create thread factory
		MyThreadFactory factory = new MyThreadFactory("Custom");
		
		// create task
		MyTask myTask = new MyTask();
		
		// get thread to start
		// here only create one, you can create several 
		Thread thread = factory.newThread(myTask);
		
		thread.start();
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// check
		System.out.println("Main: thread information:");
		System.out.println(thread);
		System.out.println("Main: end");
		
	}
}
