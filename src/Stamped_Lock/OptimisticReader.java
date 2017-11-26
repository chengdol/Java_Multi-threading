package Stamped_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class OptimisticReader implements Runnable
{
	private final Position position;
	private final StampedLock lock;
	
	public OptimisticReader(Position pos, StampedLock lk)
	{
		position = pos;
		lock = lk;
	}
	
	@Override
	public void run()
	{
		long stamp;
		for (int i = 0; i < 100; i++)
		{
			stamp = lock.tryOptimisticRead();
			// read first
			int x = position.getX();
			int y = position.getY();
			// then check
			if (lock.validate(stamp))
			{
				System.out.printf("OptimisticReader: %d - (%d, %d)\n",
								  stamp,
								  x,
								  y);
			}
			// if fail, do nothing
			else
			{
				System.out.printf("OPtimisitcReader: %d - Not free\n", stamp);
			}
			
			try
			{
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
