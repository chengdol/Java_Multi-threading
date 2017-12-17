package Chapter7_Concurrent_Collections_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable
{
	private final ConcurrentLinkedDeque<String> deque;
	
	public PollTask(ConcurrentLinkedDeque<String> deque)
	{
		super();
		this.deque = deque;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 5000; i++)
		{
			deque.pollFirst();
			deque.pollLast();
		}
	}

}
