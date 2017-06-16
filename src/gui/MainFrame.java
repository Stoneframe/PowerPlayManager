package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Formation;
import model.FormationBuilder;
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
	private PlayerPanel playerPanel;

	private JPanel buttonPanel;
	private JButton createFormationsButton;

	private Roster roster;

	public MainFrame()
	{
		roster = new Roster();

		PlayerEvaluator[] evaluators = new PlayerEvaluator[]
		{
				new GoaliePlayerEvaluator(),
				// new BackPlayerEvaluator(),
				// new PivotPlayerEvaluator(),
				// new WingPlayerEvaluator(),
				new DefensiveBackPlayerEvaluator(),
				new DefensivePivotPlayerEvaluator(),
				new DefensiveWingPlayerEvaluator(),
				new OffensiveBackPlayerEvaluator(),
				new OffensivePivotPlayerEvaluator(),
				new OffensiveWingPlayerEvaluator(),
		};

		parsePanel = new ParsePanel();
		parsePanel.setPlayersParseListener(new PlayersParsedListener()
		{
			public void playersParsed(Object source, PlayersParsedEvent event)
			{
				roster.addAll(event.getPlayers());
				rosterTablePanel.showRoster(roster, evaluators);
			}
		});

		rosterTablePanel = new RosterTablePanel();
		rosterTablePanel.setPlayerSelectedListener(new PlayerSelectedListener()
		{
			public void playerSelected(Object source, PlayerSelectedEvent event)
			{
				playerPanel.showPlayer(event.getPlayer());
			}
		});

		playerPanel = new PlayerPanel();

		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(createFormationsButton);

		setLayout(new BorderLayout());

		add(parsePanel, BorderLayout.NORTH);
		add(rosterTablePanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
