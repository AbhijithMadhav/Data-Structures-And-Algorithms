package sorting.common;
import java.util.LinkedList;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

import edu.princeton.cs.introcs.Stopwatch;

/*
 * 2.2.26 Array creation. Use SortCompare to get a rough idea of the effect
 * on perfor- mance on your machine of creating aux[] in merge() rather than
 * in sort().
 */

@SuppressWarnings("rawtypes")
public class SortCompare
{
	/**
	 * Times a single run of the specified sort algorithm on the specified array
	 * 
	 * @param alg
	 *            Name of the sort algorithm to run
	 * @param a
	 *            Array
	 * @return Time taken to run 'alg'
	 */
	private static double time(String alg, Comparable[] a)
	{
		double t = -1.0;
		String c = (alg.split("[.]"))[0];
		String m = (alg.split("[.]"))[1];

		Stopwatch timer;

		try
		{
			timer = new Stopwatch();
			Class.forName(c).getMethod(m, Comparable[].class)
					.invoke(null, (Object) a);
			t = timer.elapsedTime();
			assert SortHelper.isSorted(a);
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println(e.getCause());
			System.exit(1);
		}
		return t;
	}

	/**
	 * 
	 * @param alg1
	 *            Sort algorithm to compare with alg2
	 * @param alg2
	 *            Sort algorithm to compare with alg1
	 * @param N
	 *            Number of elements in the array to be sorted
	 * @param T
	 *            Number of trials to be run
	 * @param inputType
	 *            Type of the input distribution to be used while generating
	 *            elements
	 * @return The times alg1 is faster than the alg2
	 */
	private static double compare(String alg1, String alg2, int N, int T,
			String inputType, String dataType)
	{
		Comparable[] a = new Comparable[N];
		Comparable[] b = new Comparable[N];

		try
		{
			SortHelper.class.getMethod("put" + inputType + "Input",
					Comparable[].class).invoke(null, (Object) a);
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println(e.getCause());
			System.exit(1);
		}

		System.arraycopy(a, 0, b, 0, N);
		double total1 = 0.0;
		double total2 = 0.0;

		for (int t = 0; t < T; t++)
		{ // Perform one experiment (generate and sort an array).
			total1 += time(alg1, a);
			// System.out.println(alg1 + " : " + total1);
			total2 += time(alg2, b);
			// System.out.println(alg2 + " : " + total2);
		}
		return total2 / total1;
	}

	static final int defaultN = 1000;
	static final int defaultNumTrials = 100;
	static final String defaultInputType = "Random";
	static final String dataTypes = "object | primitive";
	static final String defaultDataType = "Object";

	/**
	 * @param args
	 *            Command line arguments
	 * @return 'Options' object containing command line arguments
	 */
	private static Options getOptions(String[] args)
	{
		Options options = new Options();

		LinkedList<String> l = SortHelper.getAlgorithmList();
		Option opt = new Option("a1", true,
				"Name of the first algorithm. Can be one of '" + l.toString()
						+ "'");
		opt.setRequired(true);
		options.addOption(opt);

		opt = new Option("a2", true,
				"Name of the second algorithm. Can be one of '" + l.toString()
						+ "'");
		opt.setRequired(true);
		options.addOption(opt);

		options.addOption("n", true,
				"Number of elements in the array\nDefault is " + defaultN);

		options.addOption("t", true, "Number of trial runs. Default is "
				+ defaultNumTrials);

		options.addOption("i", true, "Input Type. Can be one of, "
				+ SortHelper.getInputNatureList().toString() + "\nDefault is "
				+ defaultInputType);

		options.addOption("d", true,
				"Type of data to be generated. Can be one of, " + dataTypes
						+ "\nDefault is " + defaultDataType);

		options.addOption("h", false, "This message");

		return options;
	}

	private static CommandLine getCommandLine(String args[])
	{
		HelpFormatter formatter = new HelpFormatter();
		Options options = getOptions(args);
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try
		{
			cmd = parser.parse(options, args);
		}
		catch (ParseException e)
		{
			System.err.println("Error : " + e.getMessage());
			formatter.printHelp("SortCompare", options, true);
			System.exit(1);
		}

		if (cmd.hasOption("h"))
		{
			formatter.printHelp("SortCompare", options, true);
			System.exit(0);
		}
		return cmd;
	}

	/*
	 * usage:java SortCompare -a1 <algorithm-1> <algorithm-2> -n <Array-size> -t
	 * <No-of-experiments> -i <input-type>
	 */
	public static void main(String[] args)
	{
		CommandLine cmd = getCommandLine(args);
		String alg1 = cmd.getOptionValue("a1");
		String alg2 = cmd.getOptionValue("a2");
		int N = Integer.parseInt(cmd.getOptionValue("n",
				Integer.toString(defaultN)));
		int T = Integer.parseInt(cmd.getOptionValue("t",
				Integer.toString(defaultNumTrials)));
		String inputType = cmd.getOptionValue("i", defaultInputType);
		String dataType = cmd.getOptionValue("d", defaultDataType);

		double t = compare(alg1, alg2, N, T, inputType, dataType);
		System.out.println("Number of unsorted keys(Integers) : " + N);
		System.out.println("Nature of Input : " + inputType);
		System.out.println("Number of Trials : " + T);
		System.out.format(alg1 + " = %.2f" + " * " + alg2, t);

	}
}