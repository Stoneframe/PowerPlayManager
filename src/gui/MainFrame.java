package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import gui.formation.FormationBuilderFrame;
import gui.menu.MenuBar;
import gui.parse.ParsePanel;
import gui.parse.PlayerEvaluatorsParsedEvent;
import gui.parse.PlayerEvaluatorsParsedListener;
import gui.parse.PlayersParsedEvent;
import gui.parse.PlayersParsedListener;
import gui.player.PlayerPanel;
import gui.roster.PlayerSelectedEvent;
import gui.roster.PlayerSelectedListener;
import gui.roster.RosterTablePanel;
import model.PlayerEvaluator;
import model.Roster;
import model.evaluators.DefensiveBackPlayerEvaluator;
import model.evaluators.DefensivePivotPlayerEvaluator;
import model.evaluators.DefensiveWingPlayerEvaluator;
import model.evaluators.GoaliePlayerEvaluator;
import model.evaluators.OffensiveBackPlayerEvaluator;
import model.evaluators.OffensivePivotPlayerEvaluator;
import model.evaluators.OffensiveWingPlayerEvaluator;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private MenuBar menuBar;
	private ParsePanel parsePanel;
	private RosterTablePanel rosterTablePanel;
	private PlayerPanel playerPanel;

	private JPanel buttonPanel;
	private JButton createFormationsButton;
	private JButton clearRosterButton;

	private Roster roster = new Roster();

	private List<PlayerEvaluator> evaluators = new LinkedList<PlayerEvaluator>(
			Arrays.asList(
				new GoaliePlayerEvaluator(),
				// new BackPlayerEvaluator(),
				// new PivotPlayerEvaluator(),
				// new WingPlayerEvaluator(),
				new DefensiveBackPlayerEvaluator(),
				new DefensivePivotPlayerEvaluator(),
				new DefensiveWingPlayerEvaluator(),
				new OffensiveBackPlayerEvaluator(),
				new OffensivePivotPlayerEvaluator(),
				new OffensiveWingPlayerEvaluator()));

	public MainFrame()
	{
		super("PPM Assistant");

		// Menu bar
		menuBar = new MenuBar(this, roster);

		// Parse panel
		parsePanel = new ParsePanel();
		parsePanel.setPlayersParseListener(new PlayersParsedListener()
		{
			public void playersParsed(Object source, PlayersParsedEvent event)
			{
				roster.addAll(event.getPlayers());
			}
		});
		parsePanel.setPlayerEvaluatorsParsedListener(
			new PlayerEvaluatorsParsedListener()
			{
				public void playerEvaluatorsParsed(
						Object source,
						PlayerEvaluatorsParsedEvent event)
				{
					evaluators = event.getPlayerEvaluators();

					rosterTablePanel
							.setPlayerEvaluators(event.getPlayerEvaluators());
					playerPanel
							.setPlayerEvaluators(event.getPlayerEvaluators());
				}
			});

		// Roster table panel
		rosterTablePanel = new RosterTablePanel();
		rosterTablePanel.bind(roster);
		rosterTablePanel.setPlayerEvaluators(evaluators);
		rosterTablePanel.setPlayerSelectedListener(new PlayerSelectedListener()
		{
			public void playerSelected(Object source, PlayerSelectedEvent event)
			{
				playerPanel.bind(event.getPlayer());
			}
		});

		// Player panel
		playerPanel = new PlayerPanel();
		playerPanel.setPlayerEvaluators(evaluators);

		// Button panel
		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new FormationBuilderFrame(roster.copy(), evaluators);
					}
				});
			}
		});

		clearRosterButton = new JButton("Clear Roster");
		clearRosterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				roster.clear();
			}
		});

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(createFormationsButton);
		buttonPanel.add(clearRosterButton);

		setLayout(new BorderLayout());

		setJMenuBar(menuBar);
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
		UIManager.put("TextField.inactiveBackground", Color.white);

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new MainFrame();
			}
		});
	}
}
