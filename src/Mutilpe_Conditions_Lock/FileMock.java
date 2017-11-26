package Mutilpe_Conditions_Lock;

public class FileMock
{
	private String[] content;
	private int index;
	
	public FileMock(int size, int length)
	{
		// create random string for content
		content = new String[size];
		for (int i = 0; i < size; i++)
		{
			StringBuilder sb = new StringBuilder(length);
			for (int j = 0; j < length; j++)
			{
				int randomChar = (int)(Math.random() * 255);
				sb.append((char)randomChar);
			}
			content[i] = sb.toString();
		}
		
		index = 0;
	}
	
	public boolean hasMoreLines()
	{
		return index < content.length;
	}
	
	public String  getLine()
	{
		if (this.hasMoreLines())
		{
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}
}
