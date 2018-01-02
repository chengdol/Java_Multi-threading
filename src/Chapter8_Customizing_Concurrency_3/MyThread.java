package Chapter8_Customizing_Concurrency_3;

import java.util.Date;

// extends Thread class
// note how to utilize super to get runnable passed in
public class MyThread extends Thread
{
	private final Date createDate;
	private Date startDate;
	private Date endDate;
	
	public MyThread(Runnable r, String name)
	{
		// pass to parent
		super(r, name);
		createDate = new Date();
	}
	
	// subclasses of Thread should override this method
	@Override
	public void run()
	{
		setStartDate();
		// parent run it
		super.run();
		setEndDate();
	}

	// why use synchronized here？
	public synchronized void setStartDate()
	{
		startDate = new Date();
	}

	public synchronized void setEndDate()
	{
		endDate = new Date();
	}
	
	public synchronized long getExecutionTime()
	{
		return endDate.getTime() - startDate.getTime();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(": ");
		sb.append("creation time: ").append(createDate);
		sb.append(", running time: ").append(getExecutionTime()).append(" millionseconds");
		
		return sb.toString();
	}
}
