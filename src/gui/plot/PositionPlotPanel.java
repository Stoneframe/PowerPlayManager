package gui.plot;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import evaluators.PlayerEvaluator;
import formation.Formation;
import formation.FormationBuilder;
import formation.FormationTemplate;
import formation.PaulsFormationBuilder;
import formation.Position;
import formation.PositionTemplate;
import formation.manipulators.PlayerWarpManipulator;
import model.Attributes;
import model.Player;
import model.Roster;
import warper.PlayerWarper;

public class PositionPlotPanel<A extends Attributes>
	extends PlotPanel<A>
{
	private final Color[] colors = new Color[]
	{
			Color.RED,
			Color.BLUE,
			Color.GREEN,
			Color.ORANGE,
			Color.MAGENTA,
			Color.PINK,
			Color.YELLOW,
			Color.CYAN,
	};

	private final List<FormationTemplate<A>> formationTemplates;
	private final Roster<A> roster;

	public PositionPlotPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		List<FormationTemplate<A>> formationTemplates,
		Roster<A> roster)
	{
		super(playerEvaluator, playerWarper);

		this.formationTemplates = formationTemplates;
		this.roster = roster;
	}

	private static final long serialVersionUID = 5475892862092901980L;

	@Override
	protected String getTitle()
	{
		return "Position Development";
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

		FormationBuilder<A> formationBuilder = new PaulsFormationBuilder<A>();

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
			{
				dataset.addSeries(
					new XYSeries(formationTemplate.getName() + " - " + positionTemplate.getName()));
			}

			dataset.addSeries(new XYSeries(formationTemplate.getName()));
		}

		for (int year = 0; year <= 15; year++)
		{
			PlayerWarpManipulator<A> manipulator = new PlayerWarpManipulator<A>(
				playerEvaluator,
				playerWarper,
				year);

			List<Formation<A>> formations = formationBuilder.createFormations(
				roster.copy(),
				formationTemplates,
				manipulator);

			int i = 0;

			for (int x = 0; x < formationTemplates.size(); x++)
			{
				FormationTemplate<A> formationTemplate = formationTemplates.get(x);
				Formation<A> formation = formations.get(x);

				double totalRating = 0;

				for (int y = 0; y < formationTemplate.getPositions().size(); y++)
				{
					PositionTemplate<A> positionTemplate = formationTemplate.getPositions().get(y);
					Position<A> position = formation.getPositions().get(y);

					Player<A> player = position.getPlayer();

					double rating = manipulator.manipulate(
						player,
						positionTemplate.getAttributeEvaluator());

					totalRating += rating;

					dataset.getSeries(i).add(year, rating);

					i++;
				}

				dataset.getSeries(i).add(year, totalRating / formation.getPositions().size());

				i++;
			}
		}

		return dataset;
	}

	@Override
	protected XYLineAndShapeRenderer getLineRenderer()
	{
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setToolTipGenerator(new StandardXYToolTipGenerator());

		int i = 0;

		for (int x = 0; x < formationTemplates.size(); x++)
		{
			FormationTemplate<A> formationTemplate = formationTemplates.get(x);

			for (int y = 0; y < formationTemplate.getPositions().size(); y++)
			{
				renderer.setSeriesPaint(i, colors[x % colors.length]);

				i++;
			}

			renderer.setSeriesPaint(i, Color.BLACK);

			i++;
		}

		return renderer;
	}
}
