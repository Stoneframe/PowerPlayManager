package gui;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
import gui.menu.MenuBar;
import gui.menu.football.FootballFileHandler;
import gui.menu.handball.HandballFileHandler;
import gui.menu.icehockey.IceHockeyFileHandler;
import javafx.embed.swing.JFXPanel;
import parsers.players.football.FootballFormationPlayersProParser;
import parsers.players.football.FootballMarketPlayersParser;
import parsers.players.football.FootballOverviewPlayersParser;
import parsers.players.football.FootballPractisePlayersParser;
import parsers.players.football.FootballPractiseProPlayersParser;
import parsers.players.handball.HandballFormationPlayersParser;
import parsers.players.handball.HandballFormationPlayersProParser;
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
import settings.AppSettings;
import settings.SettingStorage;
import settings.SportSettings;

public class MainFrame
	extends JFrame
{
	public static final String HANDBALL_TITLE = "Handball";
	public static final String ICE_HOCKEY_TITLE = "Ice Hockey";
	public static final String FOOTBALL_TITLE = "Football";

	private static final long serialVersionUID = -8026416994513756565L;

	private AppSettings appSettings = new AppSettings(new SettingStorage("app"));

	private JTabbedPane tabbedPane;

	private IceHockeyMainPanel iceHockeyPanel;
	private FootballMainPanel footballPanel;
	private HandballMainPanel handballPanel;

	public MainFrame()
	{
		super("PPM Assistant");
		
		new JFXPanel();

		MenuBar menuBar = new MenuBar(this, tabbedPane);

		iceHockeyPanel = new IceHockeyMainPanel(
				menuBar,
				new IceHockeyFileHandler(),
				Arrays.asList(
					new IceHockeyProfilePlayersParser(),
					new IceHockeyMarketPlayersParser(),
					new IceHockeyOverviewPlayersParser(),
					new IceHockeyPractiseProPlayersParser(),
					new IceHockeyFormationPlayersParser()),
				new IceHockeyPlayerEvaluator(
						new SportSettings(new SettingStorage("icehockey")),
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
				menuBar,
				new FootballFileHandler(),
				Arrays.asList(
					new FootballMarketPlayersParser(),
					new FootballOverviewPlayersParser(),
					new FootballPractisePlayersParser(),
					new FootballPractiseProPlayersParser(),
					new FootballFormationPlayersProParser()),
				new FootballPlayerEvaluator(
						new SportSettings(new SettingStorage("football")),
						Arrays.asList(
							new FootballGoalkeeperAttributeEvaluator(),
							new FootballFullBackAttributeEvaluator(),
							new FootballCenterBackAttributeEvaluator(),
							new FootballWideMidfielderAttributeEvaluator(),
							new FootballCenterMidfielderAttributeEvaluator(),
							new FootballWideForwardAttributeEvaluator(),
							new FootballCenterForwardAttributeEvaluator())));

		handballPanel = new HandballMainPanel(
				menuBar,
				new HandballFileHandler(),
				Arrays.asList(
					new HandballProfilePlayersParser(),
					new HandballMarketPlayersParser(),
					new HandballOverviewPlayersParser(),
					new HandballPractisePlayersParser(),
					new HandballPractiseProPlayersParser(),
					new HandballFormationPlayersParser(),
					new HandballFormationPlayersProParser()),
				new HandballPlayerEvaluator(
						new SportSettings(new SettingStorage("handball")),
						Arrays.asList(
							new HandballGoalieAttributeEvaluator(),
							new HandballDefBackAttributeEvaluator(),
							new HandballDefPivotAttributeEvaluator(),
							new HandballDefWingAttributeEvaluator(),
							new HandballOffBackAttributeEvaluator(),
							new HandballOffPivotAttributeEvaluator(),
							new HandballOffWingAttributeEvaluator())));

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab(ICE_HOCKEY_TITLE, iceHockeyPanel);
		tabbedPane.addTab(FOOTBALL_TITLE, footballPanel);
		tabbedPane.addTab(HANDBALL_TITLE, handballPanel);

		tabbedPane.setSelectedIndex(appSettings.getPreviousTabIndex());
		tabbedPane.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				appSettings.setPreviousTabIndex(tabbedPane.getSelectedIndex());
			}
		});

		setLayout(new BorderLayout());

		add(tabbedPane, BorderLayout.CENTER);
		setJMenuBar(menuBar);

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

//	public static void main(String[] args)
//	{
//		UIManager.put("TextField.inactiveBackground", Color.white);
//
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				new MainFrame();
//			}
//		});
//	}
}
