package Chapter8_Customizing_Concurrency_6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// this is task, can be divide and conquer
public class MyRecursiveTask extends RecursiveTask<Integer>
{

	// 没加这个为什么会警告？
	// 跟编译器有关了，也不用管目前
	private static final long serialVersionUID = 1L;
	private int[] array;
	private int start;
	private int end;
	
	public MyRecursiveTask(int[] array, int start, int end)
	{
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute()
	{
		Integer ret;
		// get current thread execute the task
		MyWorkerThread myWorkerThread = (MyWorkerThread) Thread.currentThread();
		// add counter
		myWorkerThread.addTask();
		
		// divide and conquer
		if (end - start > 100)
		{
			int mid = (end + start) / 2;
			// create new task
			MyRecursiveTask left = new MyRecursiveTask(array, start, mid);
			MyRecursiveTask right = new MyRecursiveTask(array, mid, end);
			
			invokeAll(left, right);
			ret = addResults(left, right);
		}
		else
		{
			int tmp = 0;
			for (int i = start; i < end; i++)
			{
				tmp += array[i];
			}
			ret = tmp;
		}
		
		try
		{
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return ret;
	}

	private Integer addResults(MyRecursiveTask left, MyRecursiveTask right)
	{
		int ret = 0;
		try
		{
			ret = left.get().intValue() + right.get().intValue();
		} catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

}
