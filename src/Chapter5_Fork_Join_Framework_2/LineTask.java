package Chapter5_Fork_Join_Framework_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

// line process task
public class LineTask extends RecursiveTask<Integer>
{
	private String[] line;
	private int start, end;
	private String target;
	
	public LineTask(String[] line, int start, int end, String target)
	{
		super();
		this.line = line;
		this.start = start;
		this.end = end;
		this.target = target;
	}

	@Override
	protected Integer compute()
	{
		int result = 0;
		if (end - start < 10)
		{
			result = count(line, start, end, target);
		}
		else
		{
			int mid = (start + end) / 2;
			LineTask left = new LineTask(line, start, mid, target);
			LineTask right = new LineTask(line, mid, end, target);
			
			// invoke all
			// here we ignore the return value
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


	private int groupResult(Integer res1, Integer res2)
	{
		return res1 + res2;
	}

	private int count(String[] line, int start, int end, String target)
	{
		int result = 0;
		for (int i = start; i < end; i++)
		{
			if (line[i].equals(target))
			{
				result++;
			}
		}
		// simulate process
		try
		{
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

}
