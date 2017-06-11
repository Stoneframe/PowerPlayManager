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
import model.Rooster;
import model.builders.DumbFormationBuilder;
import model.builders.PaulsFormationBuilder;
import model.evaluators.BackPlayerEvaluator;
import model.evaluators.DefensiveBackPlayerEvaluator;
import model.evaluators.DefensivePivotPlayerEvaluator;
import model.evaluators.DefensiveWingPlayerEvaluator;
import model.evaluators.GoalkeepingPlayerEvaluator;
import model.evaluators.PivotPlayerEvaluator;
import model.evaluators.WingPlayerEvaluator;
import model.parsers.OverviewRoosterParser;
import model.parsers.PractiseProRoosterParser;
import model.parsers.PractiseRoosterParser;

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
				// new DefensiveBackPlayerEvaluator(),
				// new DefensivePivotPlayerEvaluator(),
				// new DefensiveWingPlayerEvaluator(),
		};

		textArea = new JTextArea(2, 25);

		practiseParseButton = new JButton("Practise Parse");
		practiseParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Rooster rooster = new PractiseRoosterParser()
						.parseRooster(textArea.getText());

				print(rooster, evaluators);
			}
		});

		practiseProParseButton = new JButton("Practise (Pro) Parse");
		practiseProParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Rooster rooster = new PractiseProRoosterParser()
						.parseRooster(textArea.getText());

				print(rooster, evaluators);
			}
		});

		overviewParseButton = new JButton("Overview Parse");
		overviewParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Rooster rooster = new OverviewRoosterParser()
						.parseRooster(textArea.getText());

				FormationBuilder formationBuilder = new DumbFormationBuilder();

				Formation formation1 = formationBuilder.create(
					rooster.copy(),
					new PivotPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator());

				System.out.println(formation1);

				Formation formation2 = formationBuilder.create(
					rooster.copy(),
					new DefensivePivotPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator());

				System.out.println(formation2);

				new PaulsFormationBuilder().create(
					rooster,
					new PivotPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new WingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new BackPlayerEvaluator());
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
					"%s: %.1f(%.1f)",
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
