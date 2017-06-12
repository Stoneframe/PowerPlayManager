package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import model.evaluators.GoalkeepingPlayerEvaluator;
import model.evaluators.OffensiveBackPlayerEvaluator;
import model.evaluators.OffensivePivotPlayerEvaluator;
import model.evaluators.OffensiveWingPlayerEvaluator;
import model.evaluators.PivotPlayerEvaluator;
import model.evaluators.WingPlayerEvaluator;
import model.parsers.OverviewRosterParser;
import model.parsers.PractiseProRosterParser;
import model.parsers.PractiseRosterParser;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private JTextArea textArea;
	private JButton practiseParseButton;
	private JButton practiseProParseButton;
	private JButton overviewParseButton;

	public MainFrame()
	{
		PlayerEvaluator[] evaluators = new PlayerEvaluator[]
		{
				new GoalkeepingPlayerEvaluator(),
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

		textArea = new JTextArea(2, 25);

		practiseParseButton = new JButton("Practise Parse");
		practiseParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Roster rooster = new PractiseRosterParser()
						.parseRoster(textArea.getText());

				print(rooster, evaluators);
			}
		});

		practiseProParseButton = new JButton("Practise (Pro) Parse");
		practiseProParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Roster rooster = new PractiseProRosterParser()
						.parseRoster(textArea.getText());

				print(rooster, evaluators);
			}
		});

		overviewParseButton = new JButton("Overview Parse");
		overviewParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Roster roster = new OverviewRosterParser()
						.parseRoster(textArea.getText());

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

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(practiseParseButton);
		add(practiseProParseButton);
		add(overviewParseButton);

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
