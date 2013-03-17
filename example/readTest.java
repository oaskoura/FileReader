import java.util.*;

public class readTest {
	

	public static void main(String[] args)
	{	
		fileReader reader = new fileReader("readme", "txt"); // looks for and reads index.htm
		List<String> lines = reader.getList(); // gets you lines of the read file
		List<String> tokens = reader.getTokens(","); // gets you list of word delimited by ","
		
		int fileOne = reader.initiateStream("output");
		int fileTwo = reader.initiateStream("output2");
		int fileThree = reader.initiateStream("output3");
		
		reader.print(fileOne, "Okay"); // prints "Okay" to file output.txt
		reader.close(); // closes all PrintStreams
	}

}
