package Thread_factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory
{
	// personalized fields
	private int cnt;
	private String baseName;
	private List<String> stats;
	
	
	public MyThreadFactory(String baseName)
	{
		super();
		this.cnt = 0;
		this.baseName = baseName;
		this.stats = new ArrayList<>();
	}


	@Override
	public Thread newThread(Runnable r)
	{
		Thread t = new Thread(r, baseName + "-thread-" + cnt++);
		stats.add(String.format("Created thread %s with name %s on %s"
								, t.getId()
								, t.getName()
								, new Date()));
		return t;
	}
	
	
	public String getStats()
	{
		StringBuilder sb = new StringBuilder();
		for (String s : stats)
		{
			sb.append(s).append("\n");
		}
		
		return sb.toString();
	}

}
