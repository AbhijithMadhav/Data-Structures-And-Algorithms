package ds.st;
import java.io.BufferedReader;
import java.io.FileReader;

import edu.princeton.cs.introcs.StdOut;

/*************************************************************************
 * Compilation: javac FrequencyCounter.java Execution: java FrequencyCounter L <
 * input.txt Dependencies: ST.java StdIn.java StdOut.java Data files:
 * http://algs4.cs.princeton.edu/41elementary/tnyTale.txt
 * http://algs4.cs.princeton.edu/41elementary/tale.txt
 * http://algs4.cs.princeton.edu/41elementary/tale.txt
 * http://algs4.cs.princeton.edu/41elementary/leipzig100K.txt
 * http://algs4.cs.princeton.edu/41elementary/leipzig300K.txt
 * http://algs4.cs.princeton.edu/41elementary/leipzig1M.txt
 * 
 * Read in a list of words from standard input and print out the most frequently
 * occurring word.
 * 
 * % java FrequencyCounter 1 < tinyTale.txt it 10
 * 
 * % java FrequencyCounter 8 < tale.txt business 122
 * 
 * % java FrequencyCounter 10 < leipzig1M.txt government 24763
 * 
 * 
 *************************************************************************/

public class FrequencyCounter
{

	public static void main(String[] args)
	{
		int distinct = 0, words = 0, processed = 0;
		int minlen = Integer.parseInt(args[0]);
		String lastWordInserted = null;
		String filename = args[1];
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(
				10000);

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));

			// compute frequency counts
			String line;
			while ((line = input.readLine()) != null)
			{

				String keys[] = line.split(" +");
				for (int i = 0; i < keys.length; i++)
				{
					if (keys[i].length() < minlen)
						continue;
					words++;
					Integer count = st.get(keys[i]);
					if (count != null)
						st.put(keys[i], count + 1);
					else
					{
						lastWordInserted = keys[i];
						processed = words;
						st.put(keys[i], 1);
						distinct++;
					}
				}
			}
			input.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		// find a key with the highest frequency count
		String max = "";
		st.put(max, 0);
		for (String word : st.keys())
		{
			if (st.get(word) > st.get(max))
				max = word;
		}

		StdOut.println("Minimum length : " + minlen);
		StdOut.println("Word with the maximum frequency : " + max);
		StdOut.println("Frequency : " + st.get(max));
		StdOut.println("distinct = " + distinct);
		StdOut.println("words = " + words);
		StdOut.println("Last word Inserted : " + lastWordInserted);
		StdOut.println("No. of words processed until '" + lastWordInserted
				+ "' was inserted : " + processed);
		// StdOut.println(a);

		// System.out.println("Number of comparisions = " + st.cmp);
	}
}