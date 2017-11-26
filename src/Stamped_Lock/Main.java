package Stamped_Lock;

import java.util.concurrent.locks.StampedLock;

// StampedLocks vs. ReadWriteLocks and Synchronized
// https://goo.gl/DVcWsY

// 比较了这几种lock的在不同使用情况下的效率
/*
 * It seems that on average the best performance overall is still being 
 * delivered by the intrinsic synchronized lock
 */

public class Main
{
	public static void main(String[] args)
	{
		Position position = new Position(0, 0);
		StampedLock lock = new StampedLock();
		
		// create 3 different threads
		Thread writer = new Thread(new Writer(position, lock));
		Thread reader = new Thread(new Reader(position, lock));
		// optimistic read can access read lock!
		Thread optimisticReader = new Thread(new OptimisticReader(position, lock));
		
		// start
		writer.start();
		reader.start();
		optimisticReader.start();
		
		// join
		try
		{
			writer.join();
			reader.join();
			optimisticReader.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
