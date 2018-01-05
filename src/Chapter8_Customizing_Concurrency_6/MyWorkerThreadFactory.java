package Chapter8_Customizing_Concurrency_6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

// this factory used to create custom worker thread
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory
{

	@Override
	public ForkJoinWorkerThread newThread(ForkJoinPool pool)
	{
		return new MyWorkerThread(pool);
	}

}
