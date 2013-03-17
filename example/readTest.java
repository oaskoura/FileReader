import java.util.List;

public class readTest
{
	public static void main(final String[] args)
	{
		final fileReader reader = new fileReader("readme.txt"); // looks for and reads readme.txt
		final List<String> lines = reader.getList(); // gets you lines of the read file
		final List<String> tokens = reader.getTokens(","); // gets you list of word delimited by ","

		reader.initiateStream("output.txt");
		reader.initiateStream("output2.txt");
		reader.initiateStream("output3.txt");

		// Write a string to output
		reader.print("output.txt", "Okay");
		
		// Copy all lines line-by-line with line breaks added where "," are found
		// Put it in output2
		for (String s : tokens)
		{
			reader.print("output2.txt", s);
		}
		
		// Copy all lines line-by-line into output3
		for (String s : lines)
		{
			reader.print("output3.txt", s);
		}
		
		reader.close();
	}

}
