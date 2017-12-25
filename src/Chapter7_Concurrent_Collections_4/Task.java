package Chapter7_Concurrent_Collections_4;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable
{
	private final int id;
	private final DelayQueue<Event> dq;
	
	public Task(int id, DelayQueue<Event> dq)
	{
		super();
		this.id = id;
		this.dq = dq;
	}

	@Override
	public void run()
	{
		// set the activation date
		// all events in this task have the same activation date
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + id * 1000);
		// print out
		System.out.printf("Run: thread %d event start at %s\n"
				, id
				, delay);
		// add into delay queue
		for (int i = 0; i < 25; i++)
		{
			Event e = new Event(delay);
			dq.add(e);
		}
		
	}

}
