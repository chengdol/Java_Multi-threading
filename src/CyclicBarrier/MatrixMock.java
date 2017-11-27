package CyclicBarrier;

import java.util.Random;

// auxiliary class
public class MatrixMock
{
	private final int data[][];
	
	public MatrixMock(int size, int length, int number)
	{
		super();
		data = new int[size][length];
		int counter = 0;
		Random random = new Random();
		
		// create mock matrix
		// number between 1 to 10
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < length; j++)
			{
				data[i][j] = random.nextInt(10) + 1;
				if (data[i][j] == number)
				{
					counter++;
				}
			}
		}
		
		// for validate result
		System.out.printf("Mock: There are %d of occurences of %d in matrix\n",
						  counter, number);
	}
	
	public int[] getRow(int row)
	{
		if (row >= 0 && row < data.length)
		{
			return data[row];
		}
		return null;
	}
	
}
