package Chapter7_Concurrent_Collections_9;

import java.util.Date;

// this class use normal flag
public class Task implements Runnable
{
	private final Flag flag;
	
	public Task(Flag flag)
	{
		super();
		this.flag = flag;
	}


	@Override
	public void run()
	{
		long cnt = 0;
		while (flag.flag == true)
		{
			cnt++;
		}
		System.out.printf("Task: value %d stop at %s\n"
				, cnt
				, new Date());
	}

}
