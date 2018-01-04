package Chapter8_Customizing_Concurrency_5;

import java.util.concurrent.TimeUnit;

// runnable object
// sleep 1 second
public class Task implements Runnable
{

	@Override
	public void run()
	{
		System.out.println("Task: begin");
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Task: end");
	}

}
