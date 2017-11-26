package producer_consumer_buffer;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage
{
	private int maxSize;
	private Queue<Date> storage;
	
	public EventStorage()
	{
		super();
		maxSize = 10;
		storage = new LinkedList<>();
	}
	
	// here use synchronized method, damn slow
	public synchronized void set()
	{
		// guard command
		while (maxSize == storage.size())
		{
			try
			{
				// when wait, will release current thread held object monitor 
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		storage.offer(new Date());
		System.out.println("Queue size: " + storage.size());
		// wake up all sleep threads
		notifyAll();
	}
	
	public synchronized void get()
	{
		while (storage.isEmpty())
		{
			try
			{
				// when wait, will release current thread held object monitor
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		Date date = storage.poll();
		System.out.println("Queue size: " + storage.size() + " Date: " + date.toString());
		// wake up all sleep threads
		notifyAll();
	}
	
	
}
