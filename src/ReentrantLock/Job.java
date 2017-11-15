package ReentrantLock;

public class Job implements Runnable
{
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue)
	{
		super();
		this.printQueue = printQueue;
	}

	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName() + " start to work");
		// here we put the work inside the printqueue
		printQueue.printJob(new Object());
		System.out.println(Thread.currentThread().getName() + " finish work");
	}

}
