package gui.plot;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import model.Roster;
import warper.PlayerWarper;

public class PositionPlotPanel<A extends Attributes>
	extends PlotPanel<A>
{
	private static final long serialVersionUID = 5475892862092901980L;

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

	private final XYSeriesCollection dataset = new XYSeriesCollection();

	private final FormationBuilder<A> formationBuilder = new PaulsFormationBuilder<A>();

	private final List<FormationTemplate<A>> formationTemplates;
	private final Roster<A> roster;

	private final Map<Integer, XYSeries> formationSeries = new HashMap<>();
	private final Map<Integer, XYSeries> positionSeries = new HashMap<>();

	private PlayerWarpManipulator<A> manipulator;

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
		createDataSeries();

		for (int year = 0; year <= 15; year++)
		{
			generateYearData(year);
		}

		return dataset;
	}

	@Override
	protected XYLineAndShapeRenderer getLineRenderer()
	{
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

		int i = 0;

		for (int x = 0; x < formationTemplates.size(); x++)
		{
			FormationTemplate<A> formationTemplate = formationTemplates.get(x);

			renderer.setSeriesPaint(i++, Color.BLACK);

			long nbrOfPositions = formationTemplate.getPositions()
				.stream()
				.filter(p -> !p.isIgnored())
				.count();

			for (int y = 0; y < nbrOfPositions; y++)
			{
				renderer.setSeriesPaint(i++, colors[x % colors.length]);
			}
		}

		return renderer;
	}

	private void createDataSeries()
	{
		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			addFormationSeries(formationTemplate);

			for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions()
				.stream()
				.filter(p -> !p.isIgnored())
				.collect(Collectors.toList()))
			{
				addPositionSeries(formationTemplate, positionTemplate);
			}
		}
	}

	private void generateYearData(int year)
	{
		List<Formation<A>> formations = createFormationsForYear(year);

		for (FormationTemplate<A> formationTemplate : formationTemplates)
		{
			generateFormationYearData(formationTemplate, formations, year);
		}
	}

	private List<Formation<A>> createFormationsForYear(int year)
	{
		manipulator = new PlayerWarpManipulator<A>(playerEvaluator, playerWarper, year);

		return formationBuilder.createFormations(roster.copy(), formationTemplates, manipulator);
	}

	private void generateFormationYearData(
		FormationTemplate<A> formationTemplate,
		List<Formation<A>> formations,
		int year)
	{
		Formation<A> formation = formations.stream()
			.filter(f -> f.getName().equals(formationTemplate.getName()))
			.findFirst()
			.get();

		double totalRating = 0;

		for (PositionTemplate<A> positionTemplate : formationTemplate.getPositions())
		{
			if (positionTemplate.isIgnored()) continue;

			Position<A> position = formation.getPositions()
				.stream()
				.filter(p -> p.getName().equals(positionTemplate.getName()))
				.findFirst()
				.get();

			double rating = manipulator.manipulate(
				position.getPlayer(),
				positionTemplate.getAttributeEvaluator());

			getPositionSeries(formationTemplate, positionTemplate).add(year, rating);

			totalRating += rating;
		}

		double averageRating = totalRating / formation.getPositions().size();

		getFormationSeries(formationTemplate).add(year, averageRating);
	}

	private void addFormationSeries(FormationTemplate<A> formation)
	{
		XYSeries series = new XYSeries(formation.getName());

		dataset.addSeries(series);
		formationSeries.put(formation.hashCode(), series);
	}

	private void addPositionSeries(FormationTemplate<A> formation, PositionTemplate<A> position)
	{
		XYSeries series = new XYSeries(formation.getName() + " - " + position.getName());

		dataset.addSeries(series);
		positionSeries.put(formation.hashCode() ^ position.hashCode(), series);
	}

	private XYSeries getFormationSeries(FormationTemplate<A> formationTemplate)
	{
		return formationSeries.get(formationTemplate.hashCode());
	}

	private XYSeries getPositionSeries(FormationTemplate<A> formation, PositionTemplate<A> position)
	{
		return positionSeries.get(formation.hashCode() ^ position.hashCode());
	}
}
