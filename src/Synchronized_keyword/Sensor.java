package Synchronized_keyword;

import java.util.concurrent.TimeUnit;

public class Sensor implements Runnable
{
	private ParkingStats stats;
	
	public Sensor(ParkingStats stats)
	{
		super();
		this.stats = stats;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			// two cars in
			stats.carIn();
			stats.carIn();
			
			try
			{
				TimeUnit.MICROSECONDS.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			// one moto in
			stats.motoIn();
			
			try
			{
				TimeUnit.MICROSECONDS.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			stats.carOut();
			stats.carOut();
			stats.moteOut();
		}
	}
	
}
