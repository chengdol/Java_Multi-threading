package Chapter8_Customizing_Concurrency_3;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


// using this factory to create MyThread object, then return it
public class MyThreadFactory implements ThreadFactory
{
	private AtomicInteger counter;
	private String prefix;
	
	
	
	public MyThreadFactory(String prefix)
	{
		super();
		counter = new AtomicInteger(0);
		this.prefix = prefix;
	}
	

	@Override
	public Thread newThread(Runnable r)
	{
		Thread thread = new MyThread(r, prefix + "-" + counter.getAndIncrement());
		return thread;
	}

}
