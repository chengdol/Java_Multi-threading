package CyclicBarrier;

// 这个class object 要传递给barrier!
// 用来汇总计算结果，类似与MapReduce
public class Grouper implements Runnable
{
	private final Results results;
	
	public Grouper(Results results)
	{
		super();
		this.results = results;
	}


	@Override
	public void run()
	{
		int finalCounter = 0;
		System.out.printf("Grouper: processing result...\n");
		for (int cnt : results.getDate())
		{
			finalCounter += cnt;
		}
		System.out.printf("Grouper: total result is %d\n", finalCounter);
	}

}
