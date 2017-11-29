package Chapter4_Thread_Executors_2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalc implements Callable<Integer>
{
	private final int number;
	
	
	public FactorialCalc(int number)
	{
		super();
		this.number = number;
	}


	@Override
	public Integer call() throws Exception
	{
		if (number == 0 || number == 1) { return 1; }
		int result = 1;
		for (int i = 1; i < number; i++)
		{
			result *= i;
			TimeUnit.MILLISECONDS.sleep(100);
		}
		System.out.printf("%s: result is %d\n",
				Thread.currentThread().getName(),
				result);
		
		return result;
	}

}
