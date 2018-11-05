package gui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.player.PlayersParsedEvent;
import gui.player.PlayersParsedListener;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import model.Attributes;
import model.Roster;
import parsers.players.PlayersParser;

public class EditPane<A extends Attributes>
	extends FlowPane
{
	private final Button addPlayersButton;

	private JFrame parseFrame;
	private ParsePanel<A> parsePanel;

	public EditPane(Roster<A> roster, List<PlayersParser<A>> playersParsers)
	{
		parsePanel = new ParsePanel<>(playersParsers);
		parsePanel.setPlayersParseListener(new PlayersParsedListener<A>()
		{
			public void playersParsed(Object source, PlayersParsedEvent<A> event)
			{
				roster.addAll(event.getPlayers());
			}
		});

		parseFrame = new JFrame("Add Players");
		parseFrame.setContentPane(parsePanel);
		parseFrame.pack();

		addPlayersButton = new Button("Add players");
		addPlayersButton.setOnAction(event ->
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						if (parseFrame.isVisible())
						{
							parseFrame.toFront();
						}
						else
						{
							parseFrame.setVisible(true);
						}
					}
				});
			});
		
		getChildren().add(addPlayersButton);
	}
}
