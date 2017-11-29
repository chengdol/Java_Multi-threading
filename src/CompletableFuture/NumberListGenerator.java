package CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// supplier will provide List<Long> with get() method
public class NumberListGenerator implements Supplier<List<Long>>
{
	private final int size;
	
	
	
	public NumberListGenerator(int size)
	{
		super();
		this.size = size;
	}

	@Override
	public List<Long> get()
	{
		List<Long> res = new ArrayList<>();
		System.out.printf("%s: NumberListGenerator start\n",
						 Thread.currentThread().getName());
		// create a list of long numbers
		for (int i = 0; i < size * 100000; i++)
		{
			long number = Math.round(Math.random() * Long.MAX_VALUE);
			res.add(number);
		}
		
		System.out.printf("%s: NumberListGenerator end\n",
						 Thread.currentThread().getName());
		return res;
	}

}
