package gui.plot;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import evaluators.PlayerEvaluator;
import model.Attributes;
import warper.PlayerWarper;

public abstract class PlotPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 8016338235547425768L;

	protected final PlayerEvaluator<A> playerEvaluator;
	protected final PlayerWarper<A> playerWarper;

	public PlotPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper)
	{
		this.playerEvaluator = playerEvaluator;
		this.playerWarper = playerWarper;
	}

	public void draw()
	{
		JFreeChart chart = ChartFactory.createXYLineChart(
			getTitle(),
			getXAxisLabel(),
			getYAxisLabel(),
			createDataset(),
			PlotOrientation.VERTICAL,
			true,
			true,
			false);

		XYPlot plot = chart.getXYPlot();
		plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		plot.getDomainAxis().setUpperBound(15);
		plot.getRangeAxis().setLowerBound(0);
		plot.setRenderer(getLineRenderer());
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.GRAY);
		plot.setRangeGridlinePaint(Color.GRAY);

		ChartPanel chartPanel = new ChartPanel(chart);

		setLayout(new BorderLayout());

		add(chartPanel, BorderLayout.CENTER);
	}

	protected abstract String getTitle();

	protected abstract String getXAxisLabel();

	protected abstract String getYAxisLabel();

	protected abstract XYDataset createDataset();

	protected abstract XYLineAndShapeRenderer getLineRenderer();
}