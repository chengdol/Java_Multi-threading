package Chapter8_Customizing_Concurrency_5;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//这个class是用来做什么的：
// used to execute MyScheduleTask
// 如果你要自定义周期的任务，那么你也需要自定义executor
public class MyScheduledThreadPoolExecutor
extends ScheduledThreadPoolExecutor
{

	public MyScheduledThreadPoolExecutor(int size)
	{
		super(size);
	}
	
	// in parameter field
	// task used to execute the runnable object
	// 这个task是哪里来的？执行器自己造的default task
	// 这个方法的作用就是让你modify or replace 这个task! 
	// 比如这里wrap了task，加了一些东西进去
	@Override
	protected <V> RunnableScheduledFuture<V> decorateTask(Runnable r, RunnableScheduledFuture<V> task)
	{
		// return null
		// pass given default task in it
		// pass this executor in it
		MyScheduledTask<V> myScheduledTask = new MyScheduledTask<V>(r, null, task, this);
		return myScheduledTask;
	}
	
	// 这个方法用来设置周期执行间隔
	// 这里没太明白
	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable r
			, long initDelay
			, long period
			, TimeUnit unit)
	{
		ScheduledFuture<?> task = super.scheduleAtFixedRate(r, initDelay, period, unit);
		// why cast? 这种能cast过来??
		// 因为要用来设置周期period的值
		MyScheduledTask<?> myScheduledTask = (MyScheduledTask<?>) task;
		myScheduledTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
		
		return myScheduledTask;
	}
	

}
