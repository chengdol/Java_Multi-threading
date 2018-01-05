package Chapter8_Customizing_Concurrency_7;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;


// create custom abstract class
public abstract class MyWorkerTask extends ForkJoinTask<Void>
{
	private String name;
	
	public String getName()
	{
		return name;
	}
	
	// 这个是实现任务用的
	// RecursiveTask/ RecursiveAction都定义了这个方法
	// 还有注意返回值,这里是void,也可以不是
	protected abstract void compute();
	
	public MyWorkerTask(String name)
	{
		super();
		this.name = name;
	}

	// why 用execute去执行task就不会输出了？
	@Override
	protected boolean exec()
	{
		// compute the execution time
		Date start = new Date();
		compute();
		Date end = new Date();
		long delay = end.getTime() - start.getTime();
		System.out.printf("MyWorkerTask: %s, %d millionseconds to complete\n"
				, name
				, delay);
		return true;
	}

	@Override
	public Void getRawResult()
	{
		return null;
	}

	@Override
	protected void setRawResult(Void v) {}

}
