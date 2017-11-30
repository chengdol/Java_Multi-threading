package Chapter4_Thread_Executors_3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator
{
	private final String name;

	public UserValidator(String name)
	{
		super();
		this.name = name;
	}
	
	public boolean validate(String user, String password)
	{
		Random random = new Random();
		long duration = (long)(Math.random() * 2000);
		
		try
		{
			System.out.printf("UserValidator %s: validate a user during %s msec\n",
					name, 
					duration);
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e)
		{
			return false;
		}
		
		// return random boolean
		return random.nextBoolean();
	}
	
	public String getName()
	{
		return name;
	}
}
