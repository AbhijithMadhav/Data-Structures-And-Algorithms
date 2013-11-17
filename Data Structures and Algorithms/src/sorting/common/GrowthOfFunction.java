package sorting.common;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//Application frame is the gui container which contains the plotted graph
public class GrowthOfFunction
{
	private static XYDataset createDataset()
	{
		XYSeries n = new XYSeries("n");
		XYSeries lg = new XYSeries(
				"lg(n)");
		XYSeries square = new XYSeries("n^2");
		XYSeries sqrt = new XYSeries("sqrt");
		XYSeries power = new XYSeries("n^n");
		XYSeries exp = new XYSeries("e^n");

		for (int k = 1; k <= 10; k++)
		{
			n.add(k, k);
			lg.add(k, Math.log(k)/Math.log(2));
			square.add(k, k*k);
			sqrt.add(k, Math.sqrt(k));
			double p = k;
			for(int i = 0; i < k; i++)
				p*=k;
			power.add(k, p);
			exp.add(k, Math.exp(k));
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		//dataset.addSeries(n);
		//dataset.addSeries(lg);
		//dataset.addSeries(square);
		//dataset.addSeries(sqrt);
		//dataset.addSeries(power);
		dataset.addSeries(exp);
		return dataset;
	}

	public static void main(String[] args)
	{
		XYDataset dataset = createDataset();
		SimpleLineChart demo = new SimpleLineChart(
				"Order of growth of commonly occuring functions", dataset,
				"", "");
		demo.createAndShowChart();
	}

}
