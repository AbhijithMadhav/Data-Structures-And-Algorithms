package sorting.common;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/*
 * 2.2.6 Write a program to compute the exact value of the number of array accesses used
 * by top-down mergesort and by bottom-up mergesort. Use your program to plot the val-
 * ues for N from 1 to 512, and to compare the exact values with the upper bound 6N lg N.
 */

// Application frame is the gui container which contains the plotted graph
public class SimpleLineChart extends ApplicationFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private XYDataset dataset;
	private String xLabel;
	private String yLabel;

	public SimpleLineChart(String title, XYDataset dataset, String xLabel, String yLabel)
	{
		super(title);
		this.title = title;
		this.dataset = dataset;
		this.xLabel = xLabel;
		this.yLabel = yLabel;
		JFreeChart chart = createChart(dataset, title, xLabel, yLabel);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 540));
		setContentPane(chartPanel);
	}

	private static JFreeChart createChart(XYDataset dataset, String title, String xLabel, String yLabel)
	{
		JFreeChart chart = ChartFactory.createXYLineChart(
				title, // chart title
				xLabel, // x axis label
				yLabel, // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tool tips
				false // urls
				);
		return chart;

	}

	public void createAndShowChart()
	{
		SimpleLineChart demo = new SimpleLineChart(title, dataset, xLabel, yLabel);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}