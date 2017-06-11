package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.Formation;
import model.Player;
import model.PlayerEvaluator;
import model.Rooster;
import model.Side;
import model.comparators.QualityComparator;
import model.evaluators.BackPlayerEvaluator;
import model.evaluators.DefensiveBackPlayerEvaluator;
import model.evaluators.DefensivePivotPlayerEvaluator;
import model.evaluators.DefensiveWingPlayerEvaluator;
import model.evaluators.GoalkeepingPlayerEvaluator;
import model.evaluators.PivotPlayerEvaluator;
import model.evaluators.WingPlayerEvaluator;
import model.parsers.OverviewRoosterParser;
import model.parsers.PractiseRoosterParser;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private JTextArea textArea;
	private JButton practiseParseButton;
	private JButton overviewParseButton;

	public MainFrame()
	{
		PlayerEvaluator[] evaluators = new PlayerEvaluator[]
		{
				new GoalkeepingPlayerEvaluator(),
				new BackPlayerEvaluator(),
				new DefensiveBackPlayerEvaluator(),
				new PivotPlayerEvaluator(),
				new DefensivePivotPlayerEvaluator(),
				new WingPlayerEvaluator(),
				new DefensiveWingPlayerEvaluator(),
		};

		textArea = new JTextArea(2, 25);

		practiseParseButton = new JButton("Practise Parse");
		practiseParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Rooster rooster = new PractiseRoosterParser()
						.parseRooster(textArea.getText());

				printPlayers(
					rooster.sort(
							new QualityComparator(
									new GoalkeepingPlayerEvaluator())),
					evaluators);
			}
		});

		overviewParseButton = new JButton("Overview Parse");
		overviewParseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Rooster rooster = new OverviewRoosterParser()
						.parseRooster(textArea.getText());

				Formation formation1 = createFormation(
					rooster.copy(),
					new DefensivePivotPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator());

				System.out.println(formation1);

				Formation formation2 = createFormation(
					rooster.copy(),
					new DefensivePivotPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new DefensiveWingPlayerEvaluator(),
					new BackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator(),
					new DefensiveBackPlayerEvaluator());

				System.out.println(formation2);
			}
		});

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(practiseParseButton);
		add(overviewParseButton);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private static void printPlayers(
			List<Player> players,
			PlayerEvaluator[] evaluators)
	{
		for (Player player : players)
		{
			System.out.println(player);

			for (PlayerEvaluator position : evaluators)
			{
				System.out.println(String.format(
					"%s: %.1f(%.1f)",
					position.getName(),
					position.getRating(player.getAttributes()),
					position.getQuality(player.getAttributes())));
			}

			System.out.println();
		}
	}

	private static Formation createFormation(
			Rooster rooster,
			PlayerEvaluator pivotEvaluator,
			PlayerEvaluator leftWingEvaluator,
			PlayerEvaluator rightWingEvaluator,
			PlayerEvaluator centerBackEvaluator,
			PlayerEvaluator leftBackEvaluator,
			PlayerEvaluator rightBackEvaluator)
	{
		return new Formation(
				rooster.select(pivotEvaluator, Side.UNIVERSAL),
				rooster.select(leftWingEvaluator, Side.LEFT),
				rooster.select(rightWingEvaluator, Side.RIGHT),
				rooster.select(centerBackEvaluator, Side.UNIVERSAL),
				rooster.select(leftBackEvaluator, Side.LEFT),
				rooster.select(rightBackEvaluator, Side.RIGHT));
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
