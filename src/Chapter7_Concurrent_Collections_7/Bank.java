package Chapter7_Concurrent_Collections_7;

// back subtract money from account
public class Bank implements Runnable
{
	private final Account account;
	
	public Bank(Account account)
	{
		super();
		this.account = account;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			account.subtractAmount(1000);
		}
	}

}
