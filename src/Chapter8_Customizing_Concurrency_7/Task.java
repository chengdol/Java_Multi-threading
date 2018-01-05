package Chapter8_Customizing_Concurrency_7;

// 这个task就是对数组自加
public class Task extends MyWorkerTask
{
	private int[] array;
	private int start;
	private int end;
	
	public Task(String name, int[] array, int start, int end)
	{
		super(name);
		this.array = array;
		this.start = start;
		this.end = end;
	}

	// 这个就是最关键的函数
	@Override
	protected void compute()
	{
		if (end - start > 100)
		{
			int mid = (start + end) / 2;
			// create new tasks
			Task left = new Task(this.getName() + "1", array, start, mid);
			Task right = new Task(this.getName() + "2", array, mid, end);
			
			invokeAll(left, right);
		}
		else
		{
			for (int i = start; i < end; i++)
			{
				array[i]++;
			}
		}
		
		try
		{
			Thread.sleep(10);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
