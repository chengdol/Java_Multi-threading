package Phaser_onAdvance;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable
{
	private final Phaser phaser;
	
	public Student(Phaser phaser)
	{
		super();
		this.phaser = phaser;
	}

	@Override
	public void run()
	{
		System.out.printf("%s: arrived to do the exam\n", 
						  Thread.currentThread().getName());
		phaser.arriveAndAwaitAdvance();
		//====================================================
		System.out.printf("%s: is going to do the first exercise %s\n",
						 Thread.currentThread().getName(),
						 new Date());
		doExercise();
		System.out.printf("%s: has done the first exercise %s\n",
						 Thread.currentThread().getName(),
						 new Date());
		phaser.arriveAndAwaitAdvance();
		//====================================================
		System.out.printf("%s: is going to do the second exercise %s\n",
						 Thread.currentThread().getName(),
						 new Date());
		doExercise();
		System.out.printf("%s: has done the second exercise %s\n",
						 Thread.currentThread().getName(),
						 new Date());
		phaser.arriveAndAwaitAdvance();
		//====================================================
		System.out.printf("%s: is going to do the last exercise %s\n",
				 		 Thread.currentThread().getName(),
				 		 new Date());
		doExercise();
		System.out.printf("%s: has done the last exercise %s\n",
				 		 Thread.currentThread().getName(),
				 		 new Date());
		phaser.arriveAndAwaitAdvance();

	}
	
	private void doExercise()
	{
		long duration = (long)(Math.random() * 2000);
		try
		{
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
