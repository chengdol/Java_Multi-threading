package Chapter5_Fork_Join_Framework_5;

import java.util.concurrent.ConcurrentLinkedDeque;

public class TaskManager
{
	private final ConcurrentLinkedDeque<SearchNumberTask> tasks;

	public TaskManager()
	{
		super();
		this.tasks = new ConcurrentLinkedDeque<>();
	}
	
	public void addTask(SearchNumberTask task)
	{
		tasks.add(task);
	}
	
	public void cancelTask(SearchNumberTask cancelTask)
	{
		for (SearchNumberTask task : tasks)
		{
			if (task != cancelTask)
			{
				// maybe cancel failure if task is running or in pool
				if (task.cancel(true))
				{
					task.logCancelMessage();
				}
			}
		}
	}
}
