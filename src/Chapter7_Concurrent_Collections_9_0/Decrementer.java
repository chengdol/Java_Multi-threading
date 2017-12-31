package Chapter7_Concurrent_Collections_9_0;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class Decrementer implements Runnable
{
	private final Account account;
	
	public Decrementer(Account account)
	{
		super();
		this.account = account;
	}


	@Override
	public void run()
	{
		VarHandle handler;
		try
		{
			handler = MethodHandles.lookup().in(Account.class)
						// here is double.class, the amount's type, primitive double
						.findVarHandle(Account.class, "amount", double.class);
			for (int i = 0; i < 10000; i++)
			{
				// here the handler will target the amount field in account object
				handler.getAndAdd(account, -100);
				account.unsafeAmount -= 100;
			}
			
		} catch (NoSuchFieldException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		
	}

}
