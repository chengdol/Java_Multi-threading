package Chapter4_Thread_Executors_4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// simple task 
public class Task implements Callable<Result>
{
	private final String name;
	
	public Task(String name)
	{
		super();
		this.name = name;
	}
	
	@Override
	public Result call() throws Exception
	{
		System.out.printf("%s: starting\n", name);
		// simulate process
		long duration  = (long)(Math.random() * 2000);	
		try
		{
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// simulated value
		int value = 0;
		for (int i = 0; i < 5; i++)
		{
			value += (int)(Math.random() * 1000);
		}
		
		// set result
		Result res = new Result();
		res.setName(name);
		res.setValue(value);
		System.out.printf("%s: end\n", name);
		
		return res;
	}




}
