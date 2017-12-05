package Chapter5_Fork_Join_Framework_5;

import java.util.Random;

// generate array with element range between [0 to 9]
public class ArrayGenerator
{
	public int[] generateArray(int size)
	{
		int[] ret = new int[size];
		Random random = new Random();
		for (int i = 0; i < size; i++)
		{
			ret[i] = random.nextInt(10);
		}
		return ret;
	}
}
