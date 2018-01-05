package Chapter8_Customizing_Concurrency_7;

import java.util.concurrent.ForkJoinPool;

// use ForkJoinTask to create customized task

public class Main
{
	public static void main(String[] args) throws Exception
	{
		int[] array = new int[1000];
		// create pool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// create task
		Task task = new Task("MyTask", array, 0, array.length);
		
		// why execute will not print?
		forkJoinPool.invoke(task);
		// 为什么这个不会输出了？ 用print到处试了一下，无法解释这个现象。。。
//		forkJoinPool.execute(task);
		
		// shutdown
		forkJoinPool.shutdown();
		
		System.out.println("Main: end");
	}
}
