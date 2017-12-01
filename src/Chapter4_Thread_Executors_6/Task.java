package Chapter4_Thread_Executors_6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// simulate simple task
public class Task implements Runnable
{
	private final String name;
	
	public Task(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public void run()
	{
		System.out.printf("%s: executed at %s\n", name, new Date());
		
		// if execution time > periodic time
		// the subsequent execution start late, but will not concurrently execute
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("Task: current task is done\n");
	}

}
