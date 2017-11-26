package Mutilpe_Conditions_Lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// shared by producer and consumer
public class Buffer
{
	private final LinkedList<String> buffer;
	private final int maxSize;
	private final ReentrantLock lock;
	
	// conditions
	// one lock cannot reach high concurrency?
	// consumers still read one by one
	// in class also one mutex
	private final Condition lines;
	private final Condition spaces;
	
	// whether there are lines in buffer?
	private boolean pendingLines;
	
	public Buffer(int size)
	{
		buffer = new LinkedList<>();
		maxSize = size;
		lock = new ReentrantLock();
		
		lines = lock.newCondition();
		spaces = lock.newCondition();
		
		pendingLines = true;
	}
	
	public void insert(String line)
	{
		lock.lock();
		try
		{
			while (buffer.size() == maxSize)
			{
				// await in spaces condition queue
				spaces.await();
			}
			// insert line in buffer
			buffer.offer(line);
			System.out.printf("%s: insert line: %d\n",
							  Thread.currentThread().getName(),
							  buffer.size());
			
			// signal
			lines.signalAll();
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public String get()
	{
		String line = null;
		lock.lock();
		
		try
		{
			while (buffer.size() == 0 && hasPendingLines())
			{
				lines.await();
			}
			
			if (hasPendingLines())
			{
				// 没有insert完的时候就可以读了
				line = buffer.poll();
				System.out.printf("%s: line read: %d\n",
								 Thread.currentThread().getName(),
								 buffer.size());
				spaces.signalAll();
			}
			
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
		return line;
	}
	
	// 这里同样要使用synchronized keyword
	public synchronized boolean hasPendingLines()
	{
		return pendingLines || buffer.size() > 0;
	}
	
	// 这里同样要使用synchronized keyword
	public synchronized void setPendingLines(boolean flag)
	{
		pendingLines = flag;
	}
}
