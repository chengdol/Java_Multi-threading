package Chapter8_Customizing_Concurrency_1;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// the task just sleep 1 second
public class SleepTask implements Callable<String>
{
	
	@Override
	public String call() throws Exception
	{
		TimeUnit.SECONDS.sleep(1);
		return new Date().toString();
	}

}
