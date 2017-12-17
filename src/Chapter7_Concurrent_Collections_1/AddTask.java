package Chapter7_Concurrent_Collections_1;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AddTask implements Runnable
{
	private final ConcurrentLinkedDeque<String> deque;
	
	public AddTask(ConcurrentLinkedDeque<String> deque)
	{
		super();
		this.deque = deque;
	}

	@Override
	public void run()
	{
		String name = Thread.currentThread().getName();
		// add 10000 elements in list
		for (int i = 0; i < 10000; i++)
		{
			deque.add(name + " element-" + i);
		}
	}

}
