package Chapter7_Concurrent_Collections_3;

// event class that implements comparable interface
public class Event implements Comparable<Event>
{
	private final int thread;
	private final int priority;

	public Event(int thread, int priority)
	{
		super();
		this.thread = thread;
		this.priority = priority;
	}

	public int getThread()
	{
		return thread;
	}

	public int getPriority()
	{
		return priority;
	}

	@Override
	public int compareTo(Event e)
	{
		return e.getPriority() - this.getPriority();
	}

}
