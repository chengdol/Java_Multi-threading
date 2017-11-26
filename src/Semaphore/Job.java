package Semaphore;

public class Job implements Runnable
{
	private final JobQueue jobQueue;
	
	public Job(JobQueue jq)
	{
		jobQueue = jq;
	}
	
	@Override
	public void run()
	{
		System.out.printf("%s: Going to print a job\n", 
						  Thread.currentThread().getName());
		// do print job
		// semaphore will limit threads to use the printer
		jobQueue.printJob(new Object());
		
		System.out.printf("%s: Finish a print job\n",
						 Thread.currentThread().getName());
		
	}

}
