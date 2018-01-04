package Chapter8_Customizing_Concurrency_5;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// must extend FutureTask
// implement RunnableScheduledFuture

// class name 这里也要<V>
public class MyScheduledTask<V> 
// 书上说这里用FutureTask是因为它有现成的对应于RunnableScheduledFuture的方法可以使用
extends FutureTask<V>
implements RunnableScheduledFuture<V>
{
	// what's this here?
	// 这个是default task created by executor
	// 构造的时候传进来的
	private RunnableScheduledFuture<V> task;
	
	// why executor inside task?
	// 因为要在这里的run中去实现，去执行
	private ScheduledThreadPoolExecutor executor;
	
	private long period;
	private long startDate;
	
	public MyScheduledTask(Runnable r, V res
			, RunnableScheduledFuture<V> task
			, ScheduledThreadPoolExecutor executor)
	{
		super(r, res);
		this.task = task;
		this.executor = executor;
	}

	
	
	// these 3 methods are from interface
	@Override
	public long getDelay(TimeUnit unit)
	{
		// 这个方法哪里设置的？怎么知道是不是周期性的呢？
		// 在Main class中， 用到了executor.schedule()，就会自动设为不是周期的了
		if (!isPeriodic())
		{
			// 这个get delay又是从哪里来的呢？
			// task 又是个什么来头？
			return task.getDelay(unit);
		}
		
		if (startDate == 0)
		{
			return task.getDelay(unit);
		}
		
		// 返回距离下次执行的时间差
		Date now = new Date();
		long delay = startDate - now.getTime();
		// convert given delay in given unit to this unit
		return unit.convert(delay, TimeUnit.MILLISECONDS);
		
	}

	@Override
	public int compareTo(Delayed o)
	{
		return task.compareTo(o);
	}

	@Override
	public boolean isPeriodic()
	{
		// 哪里设置的是否周期执行呢？
		return task.isPeriodic();
	}

	// why run here? 这是FutureTask的run? 
	// 主要是用来设置周期性任务
	@Override
	public void run()
	{
		if (isPeriodic() && !executor.isShutdown())
		{
			// set next start time
			Date now = new Date();
			startDate = now.getTime() + period;
			// why 把自己add进去了？
			executor.getQueue().add(this);
		}
		
		System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
		System.out.printf("MyScheduledTask: is task period? %s\n", isPeriodic());
		
		// what is this?
		// 真正run的地方
		super.runAndReset();
		
		System.out.printf("Post-MyScheduledTask: %s\n", new Date());
		
	}
	
	public void setPeriod(long period)
	{
		this.period = period;
	}
}
