package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import evaluators.football.FootballCenterBackAttributeEvaluator;
import evaluators.football.FootballCenterForwardAttributeEvaluator;
import evaluators.football.FootballCenterMidfielderAttributeEvaluator;
import evaluators.football.FootballFullBackAttributeEvaluator;
import evaluators.football.FootballGoalkeeperAttributeEvaluator;
import evaluators.football.FootballPlayerEvaluator;
import evaluators.football.FootballWideForwardAttributeEvaluator;
import evaluators.football.FootballWideMidfielderAttributeEvaluator;
import evaluators.handball.HandballDefBackAttributeEvaluator;
import evaluators.handball.HandballDefPivotAttributeEvaluator;
import evaluators.handball.HandballDefWingAttributeEvaluator;
import evaluators.handball.HandballGoalieAttributeEvaluator;
import evaluators.handball.HandballOffBackAttributeEvaluator;
import evaluators.handball.HandballOffPivotAttributeEvaluator;
import evaluators.handball.HandballOffWingAttributeEvaluator;
import evaluators.handball.HandballPlayerEvaluator;
import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyDefBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyForwardAttributeEvaluator;
import evaluators.icehockey.IceHockeyGoalieAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffensiveAttributeEvaluator;
import evaluators.icehockey.IceHockeyPlayerEvaluator;
import evaluators.icehockey.IceHockeyWingAttributeEvaluator;
import gui.football.FootballMainPanel;
import gui.handball.HandballMainPanel;
import gui.icehockey.IceHockeyMainPanel;
import parsers.players.football.FootballMarketPlayersParser;
import parsers.players.football.FootballOverviewPlayersParser;
import parsers.players.football.FootballPractisePlayersParser;
import parsers.players.football.FootballPractiseProPlayersParser;
import parsers.players.handball.HandballFormationPlayersParser;
import parsers.players.handball.HandballMarketPlayersParser;
import parsers.players.handball.HandballOverviewPlayersParser;
import parsers.players.handball.HandballPractisePlayersParser;
import parsers.players.handball.HandballPractiseProPlayersParser;
import parsers.players.handball.HandballProfilePlayersParser;
import parsers.players.icehockey.IceHockeyFormationPlayersParser;
import parsers.players.icehockey.IceHockeyMarketPlayersParser;
import parsers.players.icehockey.IceHockeyOverviewPlayersParser;
import parsers.players.icehockey.IceHockeyPractiseProPlayersParser;
import parsers.players.icehockey.IceHockeyProfilePlayersParser;
import settings.SettingStorage;
import settings.Settings;

public class MainFrame
	extends JFrame
{
	private static final long serialVersionUID = -8026416994513756565L;

	private JTabbedPane tabbedPane;

	private HandballMainPanel handballPanel;
	private IceHockeyMainPanel iceHockeyPanel;
	private FootballMainPanel footballPanel;

	public MainFrame()
	{
		super("PPM Assistant");

		handballPanel = new HandballMainPanel(
				Arrays.asList(
					new HandballProfilePlayersParser(),
					new HandballMarketPlayersParser(),
					new HandballOverviewPlayersParser(),
					new HandballPractisePlayersParser(),
					new HandballPractiseProPlayersParser(),
					new HandballFormationPlayersParser()),
				new HandballPlayerEvaluator(
						new Settings(new SettingStorage("handball")),
						Arrays.asList(
							new HandballGoalieAttributeEvaluator(),
							// new HandballBackAttributeEvaluator(),
							// new HandballPivotAttributeEvaluator(),
							// new HandballWingAttributeEvaluator(),
							new HandballDefBackAttributeEvaluator(),
							new HandballDefPivotAttributeEvaluator(),
							new HandballDefWingAttributeEvaluator(),
							new HandballOffBackAttributeEvaluator(),
							new HandballOffPivotAttributeEvaluator(),
							new HandballOffWingAttributeEvaluator())));

		iceHockeyPanel = new IceHockeyMainPanel(
				Arrays.asList(
					new IceHockeyProfilePlayersParser(),
					new IceHockeyMarketPlayersParser(),
					new IceHockeyOverviewPlayersParser(),
					new IceHockeyPractiseProPlayersParser(),
					new IceHockeyFormationPlayersParser()),
				new IceHockeyPlayerEvaluator(
						new Settings(new SettingStorage("icehockey")),
						Arrays.asList(
							new IceHockeyGoalieAttributeEvaluator(),
							new IceHockeyForwardAttributeEvaluator(),
							new IceHockeyWingAttributeEvaluator(),
							new IceHockeyCenterAttributeEvaluator(),
							new IceHockeyBackAttributeEvaluator(),
							new IceHockeyDefBackAttributeEvaluator(),
							new IceHockeyOffBackAttributeEvaluator(),
							new IceHockeyOffensiveAttributeEvaluator())));

		footballPanel = new FootballMainPanel(
				Arrays.asList(
					new FootballMarketPlayersParser(),
					new FootballOverviewPlayersParser(),
					new FootballPractisePlayersParser(),
					new FootballPractiseProPlayersParser()),
				new FootballPlayerEvaluator(
						new Settings(new SettingStorage("football")),
						Arrays.asList(
							new FootballGoalkeeperAttributeEvaluator(),
							new FootballFullBackAttributeEvaluator(),
							new FootballCenterBackAttributeEvaluator(),
							new FootballWideMidfielderAttributeEvaluator(),
							new FootballCenterMidfielderAttributeEvaluator(),
							new FootballWideForwardAttributeEvaluator(),
							new FootballCenterForwardAttributeEvaluator())));

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Handball", handballPanel);
		tabbedPane.addTab("Ice Hockey", iceHockeyPanel);
		tabbedPane.addTab("Football", footballPanel);

		setLayout(new BorderLayout());

		add(tabbedPane, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
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
