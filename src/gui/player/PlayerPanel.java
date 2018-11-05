package gui.player;

import evaluators.PlayerEvaluator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import model.Attributes;
import model.Player;

public class PlayerPanel<A extends Attributes>
	extends JFXPanel
{
	private static final long serialVersionUID = 8319489027333955979L;

	private InformationPane informationPane;

	private AttributesPane<A> attributePanel;

	private PlayerFormPanel<A> formPanel;

	public PlayerPanel(
			AttributesPane<A> attributePane,
			PlayerEvaluator<A> playerEvaluator)
	{
		this.attributePanel = attributePane;

		Platform.runLater(() ->
			{
				informationPane = new InformationPane();
				formPanel = new PlayerFormPanel<>();

				GridPane gridPane = new GridPane();

				gridPane.setVgap(10);
				gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

				gridPane.addRow(0, informationPane);
				gridPane.addRow(1, attributePane);
				gridPane.addRow(2, formPanel);

				TitledPane titledPane = new TitledPane();

				titledPane.setText("Player");
				titledPane.setCollapsible(false);
				titledPane.setContent(gridPane);

				ScrollPane scrollPane = new ScrollPane();

				scrollPane.setFitToWidth(true);
				scrollPane.setContent(titledPane);

				setScene(new Scene(scrollPane));
			});
	}

	public void bind(Player<A> player)
	{
		informationPane.bind(null);
		attributePanel.bind(null);
		formPanel.bind(null);
		// positionSuggestionPanel.bind(null);
		// trainingSuggestionPanel.bind(null);
		// trainingPanel.bind(null);

		if (player != null)
		{
			informationPane.bind(player);
			attributePanel.bind(player.getAttributes());
			formPanel.bind(player);
			// positionSuggestionPanel.bind(player.getAttributes());
			// trainingSuggestionPanel.bind(player.getAttributes());
			// trainingPanel.bind(player.getAttributes());
		}
	}
}
