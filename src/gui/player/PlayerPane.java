package gui.player;

import evaluators.PlayerEvaluator;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Attributes;
import model.Player;

public class PlayerPane<A extends Attributes>
	extends BorderPane
{
	private final InformationPane informationPane;
	private final AttributesPane<A> attributePanel;
	private final PlayerFormPanel<A> formPanel;

	public PlayerPane(
			AttributesPane<A> attributesPane,
			PlayerEvaluator<A> playerEvaluator)
	{
		this.attributePanel = attributesPane;

		informationPane = new InformationPane();
		formPanel = new PlayerFormPanel<>();

		VBox vBox = new VBox(informationPane, attributesPane, formPanel);
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10));

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vBox);

		Text text = new Text("Player");
		text.setFont(Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 20));

		BorderPane.setMargin(text, new Insets(5, 0, 5, 0));
		
		setTop(text);
		setCenter(scrollPane);
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
