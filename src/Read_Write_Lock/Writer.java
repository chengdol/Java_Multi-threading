package Read_Write_Lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Writer implements Runnable
{
	private PricesInfo pricesInfo;
	
	
	public Writer(PricesInfo pricesInfo)
	{
		super();
		this.pricesInfo = pricesInfo;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 3; i++)
		{
			System.out.printf("%s: Writer: Attemp tp modify the prices\n", new Date());
			pricesInfo.setPrice(Math.random()*10, Math.random()*10);
			System.out.printf("%s: Writer: Prices have been modified\n", new Date());
			
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
