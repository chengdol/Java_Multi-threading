package Read_Write_Lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reader implements Runnable
{
	private PricesInfo pricesInfo;
	
	public Reader(PricesInfo pricesInfo)
	{
		super();
		this.pricesInfo = pricesInfo;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.printf("%s: %s: Price1: %f\n",
								new Date(),
								Thread.currentThread().getName(),
								pricesInfo.getPrice1());
			System.out.printf("%s: %s: Price2: %f\n",
								new Date(),
								Thread.currentThread().getName(),
								pricesInfo.getPrice2());
			
			try
			{
				TimeUnit.MILLISECONDS.sleep(1400);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
