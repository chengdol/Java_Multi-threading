package Chapter4_Thread_Executors_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;


// simulation task, sleep for a random of time
public class Task implements Runnable
{
	private final Date initDate;
	private final String name;
	
	public Task(String name)
	{
		super();
		this.initDate = new Date();
		this.name = name;
	}

	@Override
	public void run()
	{
		System.out.printf("%s: task %s create on %s\n",
						 Thread.currentThread().getName(),
						 name,
						 initDate);
		System.out.printf("%s: task %s start on %s\n",
						 Thread.currentThread().getName(),
						 name,
						 new Date());
		
		// sleep
		long duration = (long)(Math.random() * 1000);
		try
		{
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("%s: task %s finished on %s\n",
						 Thread.currentThread().getName(),
						 name,
						 new Date());
	}

}
