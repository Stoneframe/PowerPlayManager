package gui;

import java.util.List;

import evaluators.PlayerEvaluator;
import gui.player.AttributesPane;
import gui.player.PlayerPane;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Attributes;
import model.Roster;
import parsers.players.PlayersParser;

public class MainPane<A extends Attributes>
	extends BorderPane
{
	private RosterPane<A> rosterPane;
	private PlayerPane<A> playerPane;
	private EditPane<A> editPane;

	private Roster<A> roster = new Roster<>();

	public MainPane(
			AttributesPane<A> attributesPane,
			PlayerEvaluator<A> playerEvaluator,
			List<PlayersParser<A>> playersParsers)
	{
		rosterPane = new RosterPane<>(roster);
		playerPane = new PlayerPane<>(attributesPane, playerEvaluator);
		editPane = new EditPane<>(roster, playersParsers);

		VBox centerPane = new VBox(rosterPane);
		centerPane.setPadding(new Insets(5));

		HBox rightPane = new HBox(playerPane);
		rightPane.setPadding(new Insets(5));

		BorderPane.setMargin(rosterPane, new Insets(5));
		BorderPane.setMargin(playerPane, new Insets(5));

		setCenter(rosterPane);
		setRight(playerPane);
		setBottom(editPane);
	}
}
