package Chapter4_Thread_Executors_5;

import java.util.Date;
import java.util.concurrent.Callable;

// simulate a simple task
// also can be Runnable
public class Task implements Callable<String>
{
	private final String name;
	
	public Task(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception
	{
		System.out.printf("%s: starting at %s\n", name, new Date());
		// do nothing here
		return "Hello, world!";
	}
	
}
