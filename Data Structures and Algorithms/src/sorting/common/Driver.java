package sorting.common;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import edu.princeton.cs.introcs.In;

@SuppressWarnings("rawtypes")
public class Driver
{
   
	private static int N = 10000;
	private static String inputType = "Random";

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
			formatter.printHelp("Sort", options, true);
			System.exit(1);
		}

		if (cmd.hasOption("h"))
		{
			formatter.printHelp("Sort", options, true);
			System.exit(0);
		}

		if (!(SortHelper.getAlgorithmList().contains(cmd.getOptionValue("s"))))
		{
			System.err.println("Algorithm specified must be one of '"
					+ SortHelper.getAlgorithmList().toString() + "'");
			System.exit(1);
		}

		if (cmd.hasOption("n"))
			N = Integer.valueOf(cmd.getOptionValue("n"));

		if (cmd.hasOption("i"))
		{
			inputType = cmd.getOptionValue("i");
			if (!(SortHelper.getInputNatureList().contains(inputType)))
			{
				System.err
						.println("The Nature of input specified must be one of '"
								+ SortHelper.getInputNatureList().toString());
				System.exit(1);
			}

		}

		return cmd;
	}

	private static Options getOptions(String[] args)
	{
		Options options = new Options();

		OptionGroup k = new OptionGroup();
		k.addOption(new Option("s", true,
				"Name of the sort algorithm. Can be one of '"
						+ SortHelper.getAlgorithmList().toString()));
		k.addOption(new Option("h", false, "This message"));
		k.setRequired(true);
		options.addOptionGroup(k);

		OptionGroup inputMethod = new OptionGroup();
		inputMethod.addOption(new Option("c", false,
				"Indicates that input will be given at the console"));
		inputMethod.addOption(new Option("n", true,
				"Number of elements to be generated randomly for sorting.\nDefault is "
						+ N));
		options.addOptionGroup(inputMethod);

		options.addOption(
				"i",
				true,
				"Input Type. Can be one of, "
						+ SortHelper.getInputNatureList().toString()
						+ ". To be used with -n. Will be ignored if used with -i.\nDefault is '"
						+ inputType + "'");

		return options;
	}

	public static void main(String[] args)
	{
		CommandLine cmd = getCommandLine(args);

		if (cmd.hasOption("c"))
		{
			System.out.println("Enter the unsorted array");
			@SuppressWarnings("deprecation")
			String[] a = In.readStrings();
			sort(cmd.getOptionValue("s"), a);
		}
		else
		{
			Comparable[] a = new Comparable[N];
			putInput(a, inputType);
			//System.out.print("Unsorted array : ");
			//SortHelper.show(a);
			System.out.println("Nature of unsorted array : " + inputType);
			System.out.println("Number of elements : " + N);
			sort(cmd.getOptionValue("s"), a);
		}
	}

	private static void putInput(Comparable[] a, String type)
	{
		try
		{
			SortHelper.class.getMethod("put" + type + "Input",
					Comparable[].class).invoke(null, (Object) a);
		}
		catch (Exception e)
		{
			System.out.println(e + "\n" + e.getCause());
			System.exit(1);
		}

	}

	private static void sort(String alg, Comparable[] a)
	{
		String c = (alg.split("[.]"))[0];
		String m = (alg.split("[.]"))[1];
		try
		{
			Class.forName(c).getMethod(m, Comparable[].class)
					.invoke(null, (Object) a);
			System.out.println("Sort method : " + alg);
			// SortHelper.show(a);
			assert SortHelper.isSorted(a);
			System.out.println("Sorting successful!");
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.out.println(e.getCause());
			System.exit(1);
		}
	}
}