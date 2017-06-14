package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Formation;
import model.FormationBuilder;
import model.Player;
import model.PlayerEvaluator;
import model.Roster;
import model.builders.PaulsFormationBuilder;
import model.evaluators.BackPlayerEvaluator;
import model.evaluators.DefensiveBackPlayerEvaluator;
import model.evaluators.DefensivePivotPlayerEvaluator;
import model.evaluators.DefensiveWingPlayerEvaluator;
import model.evaluators.GoaliePlayerEvaluator;
import model.evaluators.OffensiveBackPlayerEvaluator;
import model.evaluators.OffensivePivotPlayerEvaluator;
import model.evaluators.OffensiveWingPlayerEvaluator;
import model.evaluators.PivotPlayerEvaluator;
import model.evaluators.WingPlayerEvaluator;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private ParsePanel parsePanel;
	private RosterTablePanel rosterTablePanel;

	public MainFrame()
	{
		PlayerEvaluator[] evaluators = new PlayerEvaluator[]
		{
				new GoaliePlayerEvaluator(),
				new BackPlayerEvaluator(),
				new PivotPlayerEvaluator(),
				new WingPlayerEvaluator(),
				new DefensiveBackPlayerEvaluator(),
				new DefensivePivotPlayerEvaluator(),
				new DefensiveWingPlayerEvaluator(),
				new OffensiveBackPlayerEvaluator(),
				new OffensivePivotPlayerEvaluator(),
				new OffensiveWingPlayerEvaluator(),
		};

		parsePanel = new ParsePanel();
		parsePanel.setParsePractiseListener(new RosterParsedListener()
		{
			public void rosterParsed(Object source, RosterParsedEvent event)
			{
				rosterTablePanel.showRoster(event.getRoster(), evaluators);
				print(event.getRoster(), evaluators);
			}
		});
		parsePanel.setParseProPractiseListener(new RosterParsedListener()
		{
			public void rosterParsed(Object source, RosterParsedEvent event)
			{
				rosterTablePanel.showRoster(event.getRoster(), evaluators);
				print(event.getRoster(), evaluators);
			}
		});
		parsePanel.setParseOverviewListener(new RosterParsedListener()
		{
			public void rosterParsed(Object source, RosterParsedEvent event)
			{
				rosterTablePanel.showRoster(event.getRoster(), evaluators);

				Roster roster = event.getRoster();

				FormationBuilder formationBuilder = new PaulsFormationBuilder();

				Roster offRoster = roster.copy();

				Formation offFormation = formationBuilder.createFormation(
					offRoster,
					new OffensivePivotPlayerEvaluator(),
					new OffensiveWingPlayerEvaluator(),
					new OffensiveWingPlayerEvaluator(),
					new OffensiveBackPlayerEvaluator(),
					new OffensiveBackPlayerEvaluator(),
					new OffensiveBackPlayerEvaluator());

				Roster defRoster = roster.copy();

				Formation defFormation = formationBuilder.createFormation(
					defRoster,
					new DefensivePivotPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator());

				Roster subRoster = Roster.intersection(offRoster, defRoster);

				Formation subFormation = formationBuilder.createFormation(
					subRoster,
					new PivotPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator());

				System.out.println(
						"Offensive formation:\n" + offFormation);
				System.out.println(
						"Defensive formation:\n" + defFormation);
				System.out.println(
						"Substituion formation:\n" + subFormation);
			}
		});

		rosterTablePanel = new RosterTablePanel();

		setLayout(new BorderLayout());

		add(parsePanel, BorderLayout.NORTH);
		add(rosterTablePanel, BorderLayout.CENTER);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static void print(
			Iterable<Player> players,
			PlayerEvaluator... evaluators)
	{
		for (Player player : players)
		{
			System.out.println(player);

			for (PlayerEvaluator evaluator : evaluators)
			{
				System.out.println(String.format(
					"\t%s: %.1f(%.1f)",
					evaluator.getName(),
					evaluator.getRating(player.getAttributes()),
					evaluator.getQuality(player.getAttributes())));
			}

			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainFrame();
			}
		});
	}
}
