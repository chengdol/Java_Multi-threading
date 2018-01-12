package Chapter10_Concurrent_Design_Principle_5;


// singleton creation issue with threads
// using final keyword

// only one singleton object created!
public class Main
{
	public static void main(String[] args)
	{
		for (int i = 0; i < 20; i++)
		{
			Task task = new Task();
			new Thread(task).start();
		}
	}
}
