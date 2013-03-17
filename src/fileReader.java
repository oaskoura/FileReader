import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This class represents a reader that redirects STD I/O to current directory
 * and reads/writes files in current directory.
 */
public class fileReader
{

	private final LinkedList<String> list = new LinkedList<String>();
	private final Set<String> set = new LinkedHashSet<String>();
	private final LinkedList<String> tokens = new LinkedList<String>();
	private final String filename;
	private final List<PrintStream> stream = new ArrayList<PrintStream>();

	/**
	 * Constructs a reader that looks in current directory for the given
	 * filename. It also populates the list and set with file lines. Note: It
	 * skips empty lines. trims and lowerCase() all unempty lines.
	 * 
	 * @param filename
	 *            The name of file in current directory to read
	 * @pre. file is in current directory
	 * @exception IOException
	 *                throws Exception if cannot read filename
	 */
	public fileReader(final String s, final String extension)
	{

		this.filename = "" + System.getProperty("user.dir") + "\\" + s + "."
				+ extension;
		final File fieldFile = new File(this.filename);
		Scanner input = null;
		try
		{
			input = new Scanner(fieldFile);
		}
		catch (final IOException e)
		{
			System.out.println("Error openinng: " + this.filename);
			System.exit(1);
		}

		while (input.hasNextLine())
		{
			final String line = input.nextLine().trim().toLowerCase();
			if (line.length() > 0) // skips empty lines
			{
				this.list.add(line);
				this.set.add(line);
			}
		}
	}

	public void close()
	{
		for (final PrintStream p : this.stream)
		{
			p.close();
		}
	}

	/**
	 * Returns linkedlist of read lines of type: String Note: the linkedlist
	 * allow duplicates.
	 */
	public LinkedList<String> getList()
	{
		final LinkedList<String> r = new LinkedList<String>(this.list);
		return r;
	}

	/**
	 * Returns a LinkedHashSet of non-duplicated lines in the read file
	 * 
	 * @return LinkedHashSet of String type
	 * 
	 */

	public Set<String> getSet()
	{
		final Set<String> r = new LinkedHashSet<String>(this.set);
		return r;
	}

	/**
	 * Returns a LinkedList of String tokens delimited by the given
	 */
	public LinkedList<String> getTokens(final String delimiter)
	{
		final String delims = "[" + delimiter + "]+";

		for (int i = 0; i < this.list.size(); i++)
		{
			final String line = this.list.get(i);
			final String[] a = line.split(delims);
			for (int j = 0; j < a.length; j++)
			{
				this.tokens.add(a[j]);
			}

		}
		return this.tokens;

	}

	public int initiateStream(final String name)
	{
		try
		{
			final PrintStream p = new PrintStream(name + ".txt");
			this.stream.add(p);
		}
		catch (final Exception e)
		{
			System.out.println("File Not Found");
		}
		return this.stream.size();
	}

	public void print(final int streamNumber, final String s)
	{
		final PrintStream p = this.stream.get(streamNumber - 1);
		p.println(s);
		System.out.println("Successfully printed: \"" + s + "\" to File No. : "
				+ streamNumber);
		p.close();

	}

}
