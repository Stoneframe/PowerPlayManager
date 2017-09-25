package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import evaluators.FacilityEvaluator;
import evaluators.handball.HandballBackAttributeEvaluator;
import evaluators.handball.HandballDefBackAttributeEvaluator;
import evaluators.handball.HandballDefPivotAttributeEvaluator;
import evaluators.handball.HandballDefWingAttributeEvaluator;
import evaluators.handball.HandballGoalieAttributeEvaluator;
import evaluators.handball.HandballOffBackAttributeEvaluator;
import evaluators.handball.HandballOffPivotAttributeEvaluator;
import evaluators.handball.HandballOffWingAttributeEvaluator;
import evaluators.handball.HandballPivotAttributeEvaluator;
import evaluators.handball.HandballWingAttributeEvaluator;
import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyGoalieAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyWingAttributeEvaluator;
import gui.handball.HandballMainPanel;
import gui.icehockey.IceHockeyMainPanel;
import parsers.players.handball.HandballMarketPlayersParser;
import parsers.players.handball.HandballOverviewPlayersParser;
import parsers.players.handball.HandballPractisePlayersParser;
import parsers.players.handball.HandballPractiseProPlayersParser;
import parsers.players.icehockey.IceHockeyMarketPlayersParser;
import parsers.players.icehockey.IceHockeyOverviewPlayersParser;
import parsers.players.icehockey.IceHockeyPractiseProPlayersParser;
import parsers.players.icehockey.IceHockeyProfilePlayersParser;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private JTabbedPane tabbedPane;

	private HandballMainPanel handballPanel;
	private IceHockeyMainPanel iceHockeyPanel;

	public MainFrame()
	{
		super("PPM Assistant");

		handballPanel = new HandballMainPanel(
				new FacilityEvaluator(),
				Arrays.asList(
					new HandballGoalieAttributeEvaluator(),
					new HandballBackAttributeEvaluator(),
					new HandballPivotAttributeEvaluator(),
					new HandballWingAttributeEvaluator(),
					new HandballDefBackAttributeEvaluator(),
					new HandballDefPivotAttributeEvaluator(),
					new HandballDefWingAttributeEvaluator(),
					new HandballOffBackAttributeEvaluator(),
					new HandballOffPivotAttributeEvaluator(),
					new HandballOffWingAttributeEvaluator()),
				Arrays.asList(
					new HandballMarketPlayersParser(),
					new HandballOverviewPlayersParser(),
					new HandballPractisePlayersParser(),
					new HandballPractiseProPlayersParser()));

		iceHockeyPanel = new IceHockeyMainPanel(
				new FacilityEvaluator(),
				Arrays.asList(
					new IceHockeyGoalieAttributeEvaluator(),
					new IceHockeyBackAttributeEvaluator(),
					new IceHockeyWingAttributeEvaluator(),
					new IceHockeyCenterAttributeEvaluator(),
					new IceHockeyOffBackAttributeEvaluator()),
				Arrays.asList(
					new IceHockeyProfilePlayersParser(),
					new IceHockeyMarketPlayersParser(),
					new IceHockeyOverviewPlayersParser(),
					new IceHockeyPractiseProPlayersParser()));

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Handball", handballPanel);
		tabbedPane.addTab("Ice Hockey", iceHockeyPanel);

		setLayout(new BorderLayout());

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
