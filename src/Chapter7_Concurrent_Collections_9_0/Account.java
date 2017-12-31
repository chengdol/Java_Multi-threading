package Chapter7_Concurrent_Collections_9_0;

public class Account
{
	public double amount;
	public double unsafeAmount;
	
	public Account()
	{
		super();
		// this variable will be handled by VarHandler
		amount = 0;
		// normal 
		unsafeAmount = 0;
	}
}
