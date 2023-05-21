package gui.plot;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Groups;
import model.Player;
import warper.PlayerWarper;

public class PlayerPlotPanel<A extends Attributes>
	extends PlotPanel<A>
{
	private static final long serialVersionUID = -743240906909336010L;

	private Color[] colors = new Color[]
	{
		Color.RED,
		Color.BLUE,
		Color.GREEN,
		Color.ORANGE,
		Color.BLACK,
		Color.MAGENTA,
		Color.PINK,
		Color.YELLOW,
		Color.CYAN,
	};

	private final List<Player<A>> players;
	private final List<Groups<A>.Group> groups;

	private final int shift;

	public PlayerPlotPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		List<Player<A>> players,
		List<Groups<A>.Group> groups,
		int shift)
	{
		super(playerEvaluator, playerWarper);

		this.players = players;
		this.groups = groups;

		this.shift = shift;
	}

	@Override
	protected String getTitle()
	{
		return "Player Development";
	}

	@Override
	protected String getXAxisLabel()
	{
		return "Year";
	}

	@Override
	protected String getYAxisLabel()
	{
		return "Rating";
	}

	@Override
	protected XYDataset createDataset()
	{
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (Player<A> player : players)
		{
			dataset.addSeries(createSeries(player));
		}

		return dataset;
	}

	@Override
	protected XYLineAndShapeRenderer getLineRenderer()
	{
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

		Color[] groupColors = Arrays.copyOfRange(colors, 0, groups.size());
		Color[] individualColors = Arrays.copyOfRange(colors, groups.size(), colors.length);

		if (nbrOfGroupsContainingAnyPlayersToPlot(groups, players) > 1)
		{
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
					renderer.setSeriesPaint(i, groupColors[index]);
				}
				else
				{
					renderer.setSeriesPaint(i, individualColors[i % individualColors.length]);
				}
			}
		}

		return renderer;
	}

	private XYSeries createSeries(Player<A> player)
	{
		XYSeries series = new XYSeries(player.getName());

		AttributeEvaluator<A> attributeEvaluator =
			playerEvaluator.getBestEvaluatorByRating(player.getAttributes());

		series.add(0 + shift, attributeEvaluator.getRating(player.getAttributes()));

		for (int year = 1 + shift; year <= 15; year++)
		{
			A attributes = playerWarper.warp(player, attributeEvaluator, year);

			series.add(year, attributeEvaluator.getRating(attributes));
		}

		return series;
	}

	private long nbrOfGroupsContainingAnyPlayersToPlot(
		List<Groups<A>.Group> groups,
		List<Player<A>> players)
	{
		return groups
			.stream()
			.filter(g -> players.stream().anyMatch(p -> g.getPlayers().contains(p)))
			.count();
	}
}
