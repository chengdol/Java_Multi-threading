package Chapter5_Fork_Join_Framework_4;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// this is a simple task
// divide and conquer
// always return 0
public class Task extends RecursiveTask<Integer>
{
	private int[] array;
	private int start, end;
	
	public Task(int[] array, int start, int end)
	{
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute()
	{
		System.out.printf("Task: start from %d to %d\n",
				start, end);
		if (end - start < 10)
		{
			// if index 3 inside the range
			// throw an exception unchecked
			if (end > 3 && start < 3)
			{
				throw new RuntimeException("Task: throw runtime exception from " + start
						 + " to " + end);
			}
		}
		else
		{
			int mid = (start + end) / 2;
			// create sub tasks
			Task left = new Task(array, start, mid);
			Task right = new Task(array, mid, end);
			// invoke all
			invokeAll(left, right);
			// simulate wait
			try
			{
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.printf("Task: end from %d to %d\n", start, end);
		return 0;
	}

}
