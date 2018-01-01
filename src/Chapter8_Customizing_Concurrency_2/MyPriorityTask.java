package Chapter8_Customizing_Concurrency_2;

import java.util.concurrent.TimeUnit;

// define priority-based task
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask>
{
	private String name;
	private int priority;
	
	public MyPriorityTask(String name, int priority)
	{
		super();
		this.name = name;
		this.priority = priority;
	}

	public int getPriority()
	{
		return priority;
	}


	@Override
	public int compareTo(MyPriorityTask o)
	{
		// priority from high to low
		return Integer.compare(o.getPriority(), priority);
	}

	@Override
	public void run()
	{
		// sleep 1 second
		System.out.printf("MyPriorityTask: %s, priority %s\n"
				, name
				, priority);
		
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
