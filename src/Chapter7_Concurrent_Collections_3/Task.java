package Chapter7_Concurrent_Collections_3;

import java.util.concurrent.PriorityBlockingQueue;

// create task and add event into priority blocking queue
public class Task implements Runnable
{
	private final int id;
	private final PriorityBlockingQueue<Event> pq;

	public Task(int id, PriorityBlockingQueue<Event> pq)
	{
		super();
		this.id = id;
		this.pq = pq;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 1000; i++)
		{
			Event e = new Event(id, i);
			pq.add(e);
		}
	}

}
