package Chapter4_Thread_Executors_8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// simulate simple task
public class ExecutableTask implements Callable<String>
{
	private final String name;
	
	public ExecutableTask(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception
	{
		long duration = (long)(Math.random() * 3000);
		
		try
		{
			System.out.printf("%s: waiting %d msec for results\n",
					name,
					duration);
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e)
		{
			// do nothing
		}
		
		return name + " : finished";
	}

	public String getName()
	{
		return name;
	}
	

}
