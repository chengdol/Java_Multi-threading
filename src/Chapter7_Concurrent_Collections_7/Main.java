package Chapter7_Concurrent_Collections_7;

// perform the add and subtract operation on a back account
// using AtomicLong class
// LongAdder
// DoubleAccumulator

// the company task add money into account
// the bank task subtract money from account
public class Main
{
	public static void main(String[] args)
	{
		Account account = new Account();
		account.setBalance(1000);
		
		// create company and bank task
		Company company = new Company(account);
		Bank bank = new Bank(account);
		
		Thread[] threads = new Thread[2];
		threads[0] = new Thread(company);
		threads[1] = new Thread(bank);
		
		System.out.printf("Main: initial balance is %s\n", account.getBalance());
		// run and join
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].start();
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// check
		System.out.printf("Main: final balance is %s\n", account.getBalance());
		System.out.printf("Main: final operations are %s\n", account.getOperations());
		System.out.printf("Main: final commission is %f\n", account.getCommission());
		
	}
}
	