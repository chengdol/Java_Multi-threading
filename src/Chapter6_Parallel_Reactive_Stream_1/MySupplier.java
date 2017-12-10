package Chapter6_Parallel_Reactive_Stream_1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// implement funtional interface
public class MySupplier implements Supplier<String>
{
	// used later for parallel cases
	private final AtomicInteger count;
	
	public MySupplier()
	{
		super();
		this.count = new AtomicInteger(0);
	}

	@Override
	public String get()
	{
		int value = count.getAndAdd(1);
		return "Item " + value;
	}

}
