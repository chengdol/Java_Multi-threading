package Chapter7_Concurrent_Collections_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Decrementer implements Runnable
{	
	private final AtomicIntegerArray array;
	
	public Decrementer(AtomicIntegerArray array)
	{
		super();
		this.array = array;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < array.length(); i++)
		{
			array.getAndDecrement(i);
		}
	}

}
