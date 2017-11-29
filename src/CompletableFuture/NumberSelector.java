package CompletableFuture;

import java.util.List;
import java.util.function.Function;

// function interface, 可以做一些方法的参数，和lambda有关
// apply() method will receive List<long> and return long
public class NumberSelector implements Function<List<Long>, Long>
{

	@Override
	public Long apply(List<Long> t)
	{
		System.out.printf("%s: task3 start\n", Thread.currentThread().getName());
		
		// stream utilities
		long max = t.stream().max(Long::compare).get();
		long min = t.stream().min(Long::compare).get();
		long res = (max + min) / 2;
		
		System.out.printf("%s: task3 end, result %d\n",
						 Thread.currentThread().getName(),
						 res);
		return res;
	}

}
