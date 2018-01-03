package Chapter8_Customizing_Concurrency_4;

import java.util.concurrent.TimeUnit;

// only sleep 1 second
public class MyTask implements Runnable
{
	@Override
	public void run()
	{
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
