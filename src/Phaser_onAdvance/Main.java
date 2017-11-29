package Phaser_onAdvance;


// implement a simulation exam
// students will have to do 3 exercises
// for each exercise end, print some message on console
public class Main
{
	public static void main(String[] args)
	{
		MyPhaser phaser = new MyPhaser();
		
		Student[] students = new Student[5];
		// register
		for (int i = 0; i < 5; i++)
		{
			students[i] = new Student(phaser);
			// 只是注册了个数，没有其他含义
			phaser.register();
		}
		
		// create 5 threads to run
		Thread[] threads = new Thread[5];
		for (int i = 0; i < students.length; i++)
		{
			threads[i] = new Thread(students[i], "Student-" + i);
			threads[i].start();
		}
		
		// join
		try
		{
			for (int i = 0; i < threads.length; i++)
			{
				threads[i].join();
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// termination state
		System.out.printf("Main: the phaser has finished %s\n",
						 phaser.isTerminated());
	}
}
