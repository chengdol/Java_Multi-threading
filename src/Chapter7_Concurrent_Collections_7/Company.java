package Chapter7_Concurrent_Collections_7;


// add money into account
public class Company implements Runnable
{
	private final Account account;
	
	public Company(Account account)
	{
		super();
		this.account = account;
	}



	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			account.addAmount(1000);
		}
	}

}
