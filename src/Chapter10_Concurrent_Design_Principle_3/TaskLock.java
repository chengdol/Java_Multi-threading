package Chapter10_Concurrent_Design_Principle_3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskLock implements Runnable
{
	private int number;
	private Lock lock;

	public TaskLock()
	{
		super();
		number = 0;
		lock = new ReentrantLock();
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 100000; i++)
		{
			lock.lock();
			number = i;
			lock.unlock();
		}
	}

}
