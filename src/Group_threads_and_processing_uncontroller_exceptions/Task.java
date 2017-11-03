package Group_threads_and_processing_uncontroller_exceptions;

import java.util.Random;

public class Task implements Runnable
{
	@Override
	public void run()
	{
		// create arithmetic exception
		Random random = new Random(Thread.currentThread().getId());
		while (true)
		{
			// if exception, this thread die
			int res = 5000 / (int)(random.nextDouble() * 100000);
			
			// interrupted?
			if (Thread.currentThread().isInterrupted())
			{
				System.out.printf("Thread %d interrupted\n", Thread.currentThread().getId());
				// end the thread
				return;
			}
		}
	}
}
