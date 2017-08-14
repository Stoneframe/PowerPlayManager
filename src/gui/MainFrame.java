package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import evaluators.handball.HandballDefBackPlayerEvaluator;
import evaluators.handball.HandballDefPivotPlayerEvaluator;
import evaluators.handball.HandballDefWingPlayerEvaluator;
import evaluators.handball.HandballGoaliePlayerEvaluator;
import evaluators.handball.HandballOffBackPlayerEvaluator;
import evaluators.handball.HandballOffPivotPlayerEvaluator;
import evaluators.handball.HandballOffWingPlayerEvaluator;
import gui.handball.HandballMainPanel;
import gui.icehockey.IceHockeyMainPanel;
import parsers.players.handball.HandballMarketPlayersParser;
import parsers.players.handball.HandballOverviewPlayersParser;
import parsers.players.handball.HandballPractisePlayersParser;
import parsers.players.handball.HandballPractiseProPlayersParser;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	// private MenuBar menuBar;

	private JTabbedPane tabbedPane;

	private HandballMainPanel handballPanel;
	private IceHockeyMainPanel iceHockeyPanel;

	public MainFrame()
	{
		super("PPM Assistant");

		// Menu bar
		// menuBar = new MenuBar(this, roster);

		handballPanel = new HandballMainPanel(
				Arrays.asList(
					new HandballGoaliePlayerEvaluator(),
					new HandballDefBackPlayerEvaluator(),
					new HandballDefPivotPlayerEvaluator(),
					new HandballDefWingPlayerEvaluator(),
					new HandballOffBackPlayerEvaluator(),
					new HandballOffPivotPlayerEvaluator(),
					new HandballOffWingPlayerEvaluator()),
				Arrays.asList(
					new HandballMarketPlayersParser(),
					new HandballOverviewPlayersParser(),
					new HandballPractisePlayersParser(),
					new HandballPractiseProPlayersParser()));

		iceHockeyPanel = new IceHockeyMainPanel(
				Arrays.asList(),
				Arrays.asList());

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Handball", handballPanel);
		tabbedPane.addTab("Ice Hockey", iceHockeyPanel);

		setLayout(new BorderLayout());

		// setJMenuBar(menuBar);
		add(tabbedPane, BorderLayout.CENTER);

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
