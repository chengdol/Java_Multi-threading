package Chapter4_Thread_Executors_7;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<String>
{

	@Override
	public String call() throws Exception
	{
		while (true)
		{
			System.out.println("Task: testing");
			
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}

}
