package Synchronized_keyword;

public class Main
{
	public static void main(String[] args)
	{
		// here, many threads access stats object and indirectly access cash object
		// they all share the same data
		ParkingCash cash = new ParkingCash();
		ParkingStats stats = new ParkingStats(cash);
		
		// my current JVM is 4 cores
		// total fee should be 480
		// 0 car and 0 moto left
		int len = Runtime.getRuntime().availableProcessors() * 2;
		Thread[] threads = new Thread[len];
		for (int i = 0; i < len; i++)
		{
			// use the same stats object and cash object
			// so shared data
			Sensor sensor = new Sensor(stats);
			threads[i] = new Thread(sensor);
			threads[i].start();
		}
		
		// wait finish
		for (int i = 0; i < len; i++)
		{
			try
			{
				threads[i].join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// output
		System.out.println("number of cars: " + stats.getCarNum());
		System.out.println("number of motos " + stats.getMotoNum());
		// cash object
		cash.close();
	}
}
