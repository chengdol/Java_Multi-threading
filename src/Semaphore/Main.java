package Semaphore;

public class Main
{
	public static void main(String[] args)
	{
		JobQueue jobQueue =  new JobQueue();
		
		// create 12 threads to print something
		Thread[] threads = new Thread[12];
		for (int i = 0; i < 12; i++)
		{
			threads[i] = new Thread(new Job(jobQueue), "Task-" + i);
		}
		
		// start printer
		for (int i = 0; i < 12; i++)
		{
			threads[i].start();
		}
	}
}
