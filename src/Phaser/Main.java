package Phaser;

import java.util.concurrent.Phaser;

// 实现了一个file find task
// 在几个不同文件夹下找所有指定后缀的文件，文件修改不超过24小时
// 每个thread分为2个阶段, use phaser to perform it
// 1. find all files with specific extension
// 2. filter files which modified without at last 24 hours
public class Main
{
	public static void main(String[] args)
	{
		// 3 participants
		// 每次都要等3个到齐才下一步
		Phaser phaser = new Phaser(3);
		
		// change path according to OS
		String[] initPath = new String[] {"/home/chengdol/Desktop", 
										  "/home/chengdol/opt",
										  "/usr/games"};
		String[] name = new String[] {"Desktop", "opt", "games"};
		
		Thread[] threads = new Thread[3];
		for (int i = 0; i < 3; i++)
		{
			threads[i] = new Thread(new FileSearch(initPath[i], "log", phaser),
									name[i]);
			threads[i].start();
		}
		
		for (int i = 0; i < 3; i++)
		{
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// terminate
		System.out.println("Teminated: " + phaser.isTerminated());
	}
}
