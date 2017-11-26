package Read_Write_Lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo
{
	private double price1;
	private double price2;
	private ReadWriteLock lock;
	

	
	public PricesInfo()
	{
		super();
		this.price1 = 1;
		this.price2 = 2;
		this.lock = new ReentrantReadWriteLock();
	}

	// for read lock, many threads can read concurrently
	public double getPrice1()
	{
		// why we need lock here when only read? see my note
		// for updated value
		// for no other thread mutate value when read
		lock.readLock().lock();
		
		// multiple readers can enter this section
	    // if not locked for writing, and not writers waiting
	    // to lock for writing.
		double value = price1;
		lock.readLock().unlock();
		
		return value;
	}
	
	public double getPrice2()
	{
		lock.readLock().lock();
		// multiple readers can enter this section
	    // if not locked for writing, and not writers waiting
	    // to lock for writing.
		double value = price2;
		lock.readLock().unlock();
		
		return value;
	}
	
	// for write lock, only one thread can write at a time
	// when write, suspend all read and write threads 
	public void setPrice(double price1, double price2)
	{
		lock.writeLock().lock();
		// only one writer can enter this section,
	    // and only if no threads are currently reading.
		System.out.printf("%s: PricesInfo: Write lock adquired.\n", new Date());
		
		// simulate write process
		try
		{
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		this.price1 = price1;
		this.price2 = price2;
		
		System.out.printf("%s: PricesInfo: Write lock released.\n", new Date());
		lock.writeLock().unlock();
	}
	
}
