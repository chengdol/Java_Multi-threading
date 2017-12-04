package Chapter5_Fork_Join_Framework_2;

import java.util.Random;

// document mock class
// generate fake document
public class DocumentMock
{
	private String words[]={"the","hello","goodbye","packt",
			"java","thread","pool","random",
			"class","main"};
	
	public String[][] generateDocument(int numLines, int wordPerLine, String target)
	{
		String[][] doc = new String[numLines][wordPerLine];
		int count = 0;
		Random random = new Random();
		
		for (int i = 0; i < numLines; i++)
		{
			for (int j = 0; j < wordPerLine; j++)
			{
				doc[i][j] = words[random.nextInt(words.length)];
				if (doc[i][j].equals(target))
				{
					count++;
				}
			}
		}
		
		System.out.printf("DocumentMock: the word '%s' number is %d\n", target, count);
		return doc;
	}
}
