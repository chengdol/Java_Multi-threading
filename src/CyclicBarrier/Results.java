package CyclicBarrier;

// store result, auxiliary class
public class Results
{
	private final int[] data;

	// 记录每行row 有多少个target
	public Results(int size)
	{
		super();
		this.data = new int[size];
	}
	
	public void setDate(int pos, int val)
	{
		data[pos] = val;
	}
	
	public int[] getDate() 
	{
		return data;
	}
	
}
