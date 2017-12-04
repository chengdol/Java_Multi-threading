package Chapter5_Fork_Join_Framework_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

// document task
// contains two type of tasks:
// 1. document task itself
// 2. line process task
public class DocumentTask extends RecursiveTask<Integer>
{
	private String[][] doc;
	private int start, end;
	private String target;
	
	
	public DocumentTask(String[][] doc, int start, int end, String target)
	{
		super();
		this.doc = doc;
		this.start = start;
		this.end = end;
		this.target = target;
	}

	@Override
	protected Integer compute()
	{
		Integer result = null;
		// divide and conquer
		if (end - start < 10)
		{
			result = processLine(doc, start, end, target);
		}
		else
		{
			// create subtasks
			int mid = (start + end) / 2;
			DocumentTask left = new DocumentTask(doc, start, mid, target);
			DocumentTask right = new DocumentTask(doc, mid, end, target);
			// execute
			invokeAll(left, right);
			
			// get result
			try
			{
				result = groupResult(left.get(), right.get());
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// auxiliary method
	private Integer groupResult(Integer res1, Integer res2)
	{
		return res1 + res2;
	}

	// line process task
	private Integer processLine(String[][] doc, int start, int end, String target)
	{
		// invoke other task
		List<LineTask> tasks = new ArrayList<>();
		
		for (int i = start; i < end; i++)
		{
			LineTask lineTask = new LineTask(doc[i], 0, doc[i].length, target);
			tasks.add(lineTask);
		}
		// invoke all tasks
		// return when tasks completed
		// here we ignore the return value
		invokeAll(tasks);
		
		int result = 0;
		for (LineTask task : tasks)
		{
			try
			{
				// get() method also will wait task completion
				result += task.get();
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

}
