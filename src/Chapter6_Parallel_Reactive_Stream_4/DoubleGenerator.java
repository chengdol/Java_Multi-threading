package Chapter6_Parallel_Reactive_Stream_4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class DoubleGenerator
{
	public static List<Double> generateDoubleList(int size, int max)
	{
		List<Double> ret = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			ret.add(Math.random() * max);
		}
		return ret;
	}
	
	// generate a double
	// using DoubleStream Builder interface
	public static DoubleStream generateStreamFromList(List<Double> list)
	{
		DoubleStream.Builder builder = DoubleStream.builder();
		for (double n : list)
		{
			builder.add(n);
		}
		return builder.build();
	}
}
