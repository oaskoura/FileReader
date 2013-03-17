import java.util.List;

public class readTest
{

	@SuppressWarnings("unused")
	public static void main(final String[] args)
	{
		final fileReader reader = new fileReader("readme", "txt"); // looks for and reads readme.txt
		final List<String> lines = reader.getList(); // gets you lines of the read file
		final List<String> tokens = reader.getTokens(","); // gets you list of word delimited by ","

		final int fileOne = reader.initiateStream("output");
		final int fileTwo = reader.initiateStream("output2");
		final int fileThree = reader.initiateStream("output3");

		reader.print(fileOne, "Okay"); // prints "Okay" to file output.txt
		reader.close(); // closes all PrintStreams
	}

}
