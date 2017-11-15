package Synchronized_keyword;

public class ParkingCash
{
	private static final int cost = 2;
	private long cash;
	
	public ParkingCash()
	{
		cash = 0;
	}
	
	// using synchronized method
	public synchronized void vehiclePay()
	{
		cash += cost;
	}
	
	public void close()
	{
		System.out.println("Closing accounting:");
		long total = 0;
		// this keyword 效果和上面一样
		synchronized (this)
		{
			total = this.cash;
			this.cash = 0;
		}
		
		System.out.println("The total amount is " + total);
	}
}
