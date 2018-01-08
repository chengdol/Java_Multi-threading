package Chapter10_Concurrent_Design_Principle_3;

import java.util.concurrent.atomic.AtomicInteger;

public class TaskAtomic implements Runnable
{
	private final AtomicInteger number;
	
	
	public TaskAtomic()
	{
		super();
		number = new AtomicInteger(0);
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 100000; i++)
		{
			number.set(i);
		}
	}

}
