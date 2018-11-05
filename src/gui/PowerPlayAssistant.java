package gui;

import java.util.Arrays;

import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyDefBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyForwardAttributeEvaluator;
import evaluators.icehockey.IceHockeyGoalieAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffensiveAttributeEvaluator;
import evaluators.icehockey.IceHockeyPlayerEvaluator;
import evaluators.icehockey.IceHockeyWingAttributeEvaluator;
import gui.icehockey.IceHockeyMainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parsers.players.icehockey.IceHockeyFormationPlayersParser;
import parsers.players.icehockey.IceHockeyMarketPlayersParser;
import parsers.players.icehockey.IceHockeyOverviewPlayersParser;
import parsers.players.icehockey.IceHockeyPractiseProPlayersParser;
import parsers.players.icehockey.IceHockeyProfilePlayersParser;
import settings.SettingStorage;
import settings.SportSettings;

public class PowerPlayAssistant
	extends Application
{
	private IceHockeyMainPane iceHockeyPane;

	@Override
	public void start(Stage stage) throws Exception
	{
		iceHockeyPane = new IceHockeyMainPane(
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
							new IceHockeyOffensiveAttributeEvaluator())),
				Arrays.asList(
					new IceHockeyProfilePlayersParser(),
					new IceHockeyMarketPlayersParser(),
					new IceHockeyOverviewPlayersParser(),
					new IceHockeyPractiseProPlayersParser(),
					new IceHockeyFormationPlayersParser()));

		stage.setScene(new Scene(iceHockeyPane));
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
