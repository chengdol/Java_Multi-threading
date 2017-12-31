package Chapter7_Concurrent_Collections_9;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// practice volatile 
// the application will not end

public class Main
{
	public static void main(String[] args)
	{
		// create flags
		Flag flag = new Flag();
		VolatileFlag volatileFlag = new VolatileFlag();
		
		// create runnable objects
		Task task = new Task(flag);
		VolatileTask volatileTask = new VolatileTask(volatileFlag);
		
		// create threads and run
		Thread threadTask = new Thread(task);
		threadTask.start();
		Thread threadVolatileTask = new Thread(volatileTask);
		threadVolatileTask.start();
		
		// sleep main thread
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// change the value of volatile variable
		// to stop the volatile task
		System.out.printf("Main：going to stop the volatile task: %s\n", new Date());
		volatileFlag.volatileFlag = false;
		
		// sleep 
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.printf("Main: going to stop task: %s\n", new Date());
		flag.flag = false;
		
		// sleep 
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// never end...
	}
}
