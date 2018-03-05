package gui.plot;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import model.Roster;

public class PlotFrame<A extends Attributes>
	extends JFrame
{
	private static final long serialVersionUID = 8016338235547425768L;

	private Color[] colors = new Color[]
	{
			Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE
	};

	private PlayerEvaluator<A> playerEvaluator;

	public PlotFrame(
			PlayerEvaluator<A> playerEvaluator,
			List<Player<A>> players,
			List<Roster<A>.Group> groups)
	{
		super("Plot");

		this.playerEvaluator = playerEvaluator;

		XYDataset dataset = createDataset(players);

		JFreeChart chart = ChartFactory.createXYLineChart(
			"Player Development",
			"Year",
			"Rating",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setToolTipGenerator(new StandardXYToolTipGenerator());

		for (int i = 0; i < players.size(); i++)
		{
			Player<A> player = players.get(i);

			int index = groups.indexOf(
				groups
					.stream()
					.filter(g -> g.getPlayers().contains(player))
					.findFirst()
					.orElse(null));

			if (index != -1)
			{
				renderer.setSeriesPaint(i, colors[index]);
			}
		}

		XYPlot plot = chart.getXYPlot();
		plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		plot.getDomainAxis().setUpperBound(15);
		plot.getRangeAxis().setLowerBound(0);
		plot.setRenderer(renderer);

		ChartPanel chartPanel = new ChartPanel(chart);

		setContentPane(chartPanel);
		pack();
		setVisible(true);
	}

	private XYDataset createDataset(List<Player<A>> players)
	{
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (Player<A> player : players)
		{
			dataset.addSeries(createSeries(player));
		}

		return dataset;
	}

	private XYSeries createSeries(Player<A> player)
	{
		XYSeries series = new XYSeries(player.getName());

		for (int year = 0; year <= 15; year++)
		{
			series.add(year, playerEvaluator.calculateRatingForAge(player, player.getAge() + year));
		}

		return series;
	}
}