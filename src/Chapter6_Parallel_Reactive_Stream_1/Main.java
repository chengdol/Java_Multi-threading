package Chapter6_Parallel_Reactive_Stream_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

// creating stream from different sources
// show you how to create a stream, then to process it?
public class Main
{
	public static void main(String[] args)
	{
		// create stream from collection
		System.out.println("Main: from collection");
		List<Person> persons = PersonGenerator.generatePersonList(1000);
		Stream<Person> personStream = persons.parallelStream();
		System.out.printf("Main: number of persons %d\n", personStream.count());
		
		
		// from supplier interface
		System.out.println("-------------------------------------");
		System.out.println("Main: from supplier interface");
		Supplier<String> supplier = new MySupplier();
		Stream<String> streams = Stream.generate(supplier);
		// parallel 则顺序不确定了
		streams.parallel().limit(10).forEach(s -> System.out.printf("%s\n", s));
		
		
		// from predefined set of elements
		System.out.println("-------------------------------------");
		System.out.println("Main: from predefined set of elements");
		Stream<String> predefined = Stream.of("Peter", "Join", "Marry");
		predefined.parallel().forEach(s -> System.out.printf("%s\n", s));
		
		
		// from file, e.g line by line
		System.out.println("-------------------------------------");
		System.out.println("Main: from file");
		try (BufferedReader br = new BufferedReader(new FileReader("test.txt"));)
		{
			// return a stream
			Stream<String> fileLines = br.lines();
			System.out.printf("Main: number of lines in file %d\n",
					fileLines.parallel().count());
			// close it
			br.close();
		}
		catch (FileNotFoundException e)
		{ e.printStackTrace();}
		catch (IOException e)
		{ e.printStackTrace();}
		
		
		// from directory, process folder
		System.out.println("-------------------------------------");
		System.out.println("Main: from directory");
		try {
			
			Stream<Path> directoryContent = Files.list(Paths.get(System.getProperty("user.home")));
			System.out.printf("Number of elements (files andfolders):%d\n",
			directoryContent.parallel().count());
			directoryContent.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		// from array
		System.out.println("-------------------------------------");
		System.out.println("Main: from array");
		String[] array = {"1", "2", "3", "4", "5"};
		Stream<String> streamFromArray = Arrays.stream(array);
		streamFromArray.parallel().forEach(s -> System.out.printf("%s\n", s));
		
		// from random numbers
		System.out.println("-------------------------------------");
		System.out.println("Main: from random numbers");
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(10);
		double ave = doubleStream.parallel().peek(s -> System.out.printf("%f\n", s)).average().getAsDouble();
		System.out.printf("Main: average %f\n", ave);
		
		// from concatenating streams
		System.out.println("-------------------------------------");
		System.out.println("Main: from concatenating streams");
		Stream<String> stream1 = Stream.of("1", "2", "3", "4");
		Stream<String> stream2 = Stream.of("5", "6", "7", "8");
		Stream<String> finalStream = Stream.concat(stream1, stream2);
		finalStream.parallel().forEach(s -> System.out.printf("%s ",s));
	}
}
