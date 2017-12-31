package Chapter7_Concurrent_Collections_9;

import java.util.Date;

// the is class use volatile flag
public class VolatileTask implements Runnable
{
	private final VolatileFlag volatileFlag;
	
	public VolatileTask(VolatileFlag volatileFlag)
	{
		super();
		this.volatileFlag = volatileFlag;
	}

	@Override
	public void run()
	{
		long cnt = 0;
		while (volatileFlag.volatileFlag == true)
		{
			cnt++;
		}
		System.out.printf("VolatileTask: value %d stop at %s\n"
				, cnt
				, new Date());
	}

}
