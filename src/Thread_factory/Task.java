package Thread_factory;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable
{

	@Override
	public void run()
	{	
		// just sleep 2 seconds
		try
		{
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
