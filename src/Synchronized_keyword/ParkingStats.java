package Synchronized_keyword;

public class ParkingStats
{
	private long numCars;
	private long numMotos;
	
	private ParkingCash cash;
	
	// two objects used to control different critical sections
	// so they can run concurrently
	private final Object carControl, motoControl;

	public ParkingStats(ParkingCash cash)
	{
		super();
		this.cash = cash;
		carControl = new Object();
		motoControl = new Object();
		
		numCars = 0;
		numMotos = 0;
	}
	
	public void carIn()
	{
		synchronized (carControl)
		{
			numCars++;
		}
	}
	
	public void motoIn()
	{
		synchronized (motoControl)
		{
			numMotos++;
		}
	}
	
	public void carOut()
	{
		synchronized (carControl)
		{
			numCars--;
		}
		cash.vehiclePay();
	}
	
	public void moteOut()
	{
		synchronized (motoControl)
		{
			numMotos--;
		}
		cash.vehiclePay();
	}
	
	public synchronized long getCarNum()
	{
		return numCars;
	}
	
	public synchronized long getMotoNum()
	{
		return numMotos;
	}
	
}
