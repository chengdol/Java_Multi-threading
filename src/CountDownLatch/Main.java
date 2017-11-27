package CountDownLatch;


// 一个video conference system, wait for the arrival of all
// the participants before it begins
public class Main
{
	public static void main(String[] args)
	{
		// one video conference thread
		// will call await method
		VideoConference conference = new VideoConference(10);
		new Thread(conference).start();
		
		
		// 10 participants threads
		// when 10 participant arrive, video threads wake up to run
		// these threads will call countDown method
		for (int i = 0; i < 10; i++)
		{
			Runnable p = new Participant(conference, "Participant-" + i);
			new Thread(p).start();
		}
		
		// 都是对conference class object 进行的操作
		// 前面提到过，如果thread share the same runnable object，里面的field是共享的	
	}
}
