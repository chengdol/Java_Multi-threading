package Chapter4_Thread_Executors_8;

import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String>
{
	private final String name;
	
	public ResultTask(ExecutableTask callable)
	{
		super(callable);
		name = callable.getName();
	}
	
	@Override
	protected void done()
	{
		if (isCancelled())
		{
			System.out.printf("%s: has been canceled\n", name);
		}
		else
		{
			System.out.printf("%s: has finished\n", name);
		}
	}

}
