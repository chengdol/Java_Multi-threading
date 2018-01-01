package Chapter8_Customizing_Concurrency_1;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


// here we override 4 utility method
// extends ThreadPoolExecutor class
public class MyExecutor extends ThreadPoolExecutor
{

	private final ConcurrentHashMap<Runnable, Date> startTime;
	
	// constructor
	public MyExecutor(int corePoolSize, int maxPoolSize, long keepAliveTime
			, TimeUnit unit, BlockingQueue<Runnable> queue)
	{
		super(corePoolSize, maxPoolSize, keepAliveTime, unit, queue);
		startTime = new ConcurrentHashMap<>();
	}
	
	@Override
	public void shutdown()
	{
		System.out.println("MyExecutor: going to shutdown");
		System.out.printf("MyExecutor: executed tasks %d\n", this.getActiveCount());
		System.out.printf("MyExecutor: running tasks %d\n", this.getCompletedTaskCount());
		System.out.printf("MyExecutor: pending tasks %d\n", this.getQueue().size());
		
		super.shutdown();
	}
	
	@Override
	public List<Runnable> shutdownNow()
	{
		System.out.println("MyExecutor: going to shutdown immediately");
		System.out.printf("MyExecutor: executed tasks %d\n", this.getActiveCount());
		System.out.printf("MyExecutor: running tasks %d\n", this.getCompletedTaskCount());
		System.out.printf("MyExecutor: pending tasks %d\n", this.getQueue().size());
		return super.shutdownNow();
	}
	
	@Override
	protected void beforeExecute(Thread thread, Runnable runnable)
	{
		startTime.put(runnable, new Date());
		// print the thread name and task hash code
		System.out.printf("MyExecutor: task is beginning, name %s, hash code %s\n"
				, thread.getName()
				, runnable.hashCode());
		
	}
	
	@Override
	protected void afterExecute(Runnable runnable, Throwable ex)
	{
		// why here can cast to future?
		Future<?> future = (Future<?>) runnable;
		
		try
		{
			System.out.println("------------------------------------------");
			System.out.println("MyExecutor: task is finishing");
			System.out.printf("MyExecutor: result is %s\n", future.get());
			
			Date startDate = startTime.remove(runnable);
			Date finishDate = new Date();
			// compute the diff
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.printf("MyExecutor: duration is %s\n", diff);
			System.out.println("------------------------------------------");
			
		} catch (InterruptedException | ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
