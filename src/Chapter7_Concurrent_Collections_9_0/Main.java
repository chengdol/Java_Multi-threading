package Chapter7_Concurrent_Collections_9_0;


// practice variable handler
// the result should be 0 for both variable
// but unsafe will be incorrect.

// this is new feature of Java9
public class Main
{
	public static void main(String[] args)
	{
		Account account = new Account();
		
		// create tasks
		Thread threadIncrement = new Thread(new Incrementer(account));
		Thread threadDecrement = new Thread(new Decrementer(account));
		
		// start
		threadIncrement.start();
		threadDecrement.start();
		
		// join
		try
		{
			threadIncrement.join();
			threadDecrement.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// check
		System.out.printf("Main: safe amount %f\n", account.amount);
		System.out.printf("Main: unsafe amount %f\n", account.unsafeAmount);
	}
}
