package Chapter8_Customizing_Concurrency_6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

// task will be executed in this thread
// 注意这个是thread，是用来执行task的
// pool用这个class去造thread object
public class MyWorkerThread extends ForkJoinWorkerThread
{
	private final ThreadLocal<Integer> taskCounter = new ThreadLocal<>();
	
	// here pass the pool!
	protected MyWorkerThread(ForkJoinPool pool)
	{
		super(pool);
	}
	
	// provided by ForkJoinWorkerThread class
	@Override
	protected void onStart()
	{
		// must call this at beginning
		super.onStart();
		System.out.printf("MyWorkerThread %d, start task\n"
				, getId());
		// set counter value
		taskCounter.set(0);
	}

	// provided by ForkJoinWorkerThread class
	@Override
	protected void onTermination(Throwable t)
	{
		System.out.printf("MyWorkerThread %d, task number is %d\n"
				, getId()
				, taskCounter.get());
		
		// must call this at end
		super.onTermination(t);
	}
	
	public void addTask()
	{
		taskCounter.set(taskCounter.get() + 1);
	}

}
