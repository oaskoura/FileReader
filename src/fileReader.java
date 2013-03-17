import java.io.*;
import java.util.*;

/** This class represents a reader that redirects STD I/O to current directory and reads/writes files in current directory. */
public class fileReader {
	
	private LinkedList <String> list = new LinkedList<String>();
	private Set <String> set = new LinkedHashSet<String>();
	private LinkedList<String> tokens = new LinkedList<String>();
	private String filename;
	private List <PrintStream> stream = new ArrayList<PrintStream>();

	/**
	Constructs a reader that looks in current directory for the given filename. It also populates the list and set with file lines.
	Note: It skips empty lines. trims and lowerCase() all unempty lines.

	@param filename The name of file in current directory to read
	@pre. file is in current directory
	@exception IOException throws Exception if cannot read filename

	*/
	public fileReader(String s, String extension)
	{
		
		this.filename = "" + System.getProperty("user.dir") +"\\" + s + "." + extension; 
		File fieldFile = new File(filename);
		Scanner input = null;
		try 
		{
			input = new Scanner(fieldFile);
		}
		catch ( IOException e)
		{
			System.out.println("Error openinng: " + this.filename );
			System.exit(1);
		}
		
		while(input.hasNextLine() )
		{
			String line = input.nextLine().trim().toLowerCase();
			if ( line.length() > 0 ) // skips empty lines
			{
			list.add(line);
			set.add(line);
			}
		}
	}

	/** 
	 * Returns a LinkedHashSet of non-duplicated lines in the read file
	 * 
	 * @return LinkedHashSet of String type
	 * 
	 */

	public Set getSet()
	{
		Set<String> r = new LinkedHashSet<String>(this.set);
		return r;
	}

	/** 
	 * Returns linkedlist of read lines of type: String
	 * Note: the linkedlist allow duplicates.
	 */
	public LinkedList<String> getList()
	{
		 LinkedList<String> r = new LinkedList(this.list);
		 return r;
	}

	/**
	 * Returns a LinkedList of String tokens delimited by the given 
	 */
	public LinkedList<String> getTokens(String delimiter)
	{
		String delims = "[" + delimiter + "]+" ;
		
		for(int i = 0; i < list.size(); i++)
		{	
			String line = list.get(i);
			String[] a = line.split(delims);
			for (int j = 0; j < a.length; j++)
			{
				this.tokens.add(a[j]);	
			}
		
		}
		return this.tokens;
		
	}

	  
	public int initiateStream(String name)
	{
		try
		{
		PrintStream p = new PrintStream(name + ".txt");
		stream.add(p);
		//p.close();
		
		}
		catch( Exception e)
		{
			System.out.println("File Not Found");
		}
		return stream.size();
	}

	public void print(int streamNumber, String s)
	{
		PrintStream p = stream.get(streamNumber-1);
		p.println(s);
		System.out.println("Successfully printed: \"" + s + "\" to File No. : " + streamNumber);
		p.close();
		
	}

	public void close()
	{
		for(PrintStream p : stream)
		{
			p.close();
		}
	}

}
