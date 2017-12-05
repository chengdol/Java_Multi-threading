package Chapter5_Fork_Join_Framework_5;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer>
{
	private final int[] data;
	private int start, end;
	private int target;
	private TaskManager manager;
	
	private final static int NOT_FOUND = -1;
	
	public SearchNumberTask(int[] data, int start, int end, int target, TaskManager manager)
	{
		super();
		this.data = data;
		this.start = start;
		this.end = end;
		this.target = target;
		this.manager = manager;
	}

	@Override
	protected Integer compute()
	{
		System.out.printf("Task: start from %d to %d\n",
				start, end);
		
		int ret = NOT_FOUND;
		if (end - start < 10)
		{
			ret = lookForNumber();
		}
		else
		{
			ret = launchTasks();
		}
		
		return ret;
	}

	private int lookForNumber()
	{
		int ret = NOT_FOUND;
		for (int i = start; i < end; i++)
		{
			if (target == data[i])
			{
				System.out.printf("Task: find target %d in position %d\n",
						target, i);
				// cancel remaining tasks
				manager.cancelTask(this);
				return i;
			}
		}
		
		// wait
		try
		{
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	private int launchTasks()
	{
		int mid = (start + end) / 2;
		// create subtasks
		SearchNumberTask left = new SearchNumberTask(data, start, mid, target, manager);
		SearchNumberTask right = new SearchNumberTask(data, mid, end, target, manager);
		// add task to manager
		manager.addTask(left);
		manager.addTask(right);
		
		// asynchronous fork
		left.fork();
		right.fork();
		
		// here worker thread can continue to do...
		
		// wait for joining
		int ret = left.join();
		if (ret != NOT_FOUND)
		{
			return ret;
		}
		
		return right.join();
	}

	public void logCancelMessage()
	{
		System.out.printf("Task: cancel task from %d to %d\n",
				start, end);
	}

}
