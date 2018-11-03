package gui.player;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.Attributes;
import model.Player;

public class PlayerFormPanel<A extends Attributes>
	extends JFXPanel
{
	private static final long serialVersionUID = 8882584125182046203L;

	private static final Font PPM_FONT =
			Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 12);

	private final Label experienceLabel;
	private final Label chemistryLabel;
	private final Label energyLabel;

	private final TextField experienceTextField;
	private final TextField chemistryTextField;
	private final TextField energyTextField;

	public PlayerFormPanel()
	{
		experienceLabel = new Label("Experience:");
		experienceLabel.setFont(PPM_FONT);

		chemistryLabel = new Label("Chemistry:");
		chemistryLabel.setFont(PPM_FONT);

		energyLabel = new Label("Energy:");
		energyLabel.setFont(PPM_FONT);

		experienceTextField = new TextField();
		experienceTextField.setPrefWidth(90);
		experienceTextField.setEditable(false);

		chemistryTextField = new TextField();
		chemistryTextField.setPrefWidth(90);
		chemistryTextField.setEditable(false);

		energyTextField = new TextField();
		energyTextField.setPrefWidth(90);
		energyTextField.setEditable(false);

		Platform.runLater(new Runnable()
		{
			public void run()
			{
				GridPane gridPane = new GridPane();

				gridPane.setHgap(30);
				gridPane.setVgap(5);
				gridPane.setPadding(new Insets(10, 20, 10, 20));

				gridPane.addRow(0, experienceLabel, experienceTextField);
				gridPane.addRow(1, chemistryLabel, chemistryTextField);
				gridPane.addRow(2, energyLabel, energyTextField);

				TitledPane titledPane = new TitledPane();

				titledPane.setText("Form");
				titledPane.setCollapsible(false);
				titledPane.setContent(gridPane);

				setScene(new Scene(titledPane));
			}
		});
	}

	public void bind(Player<A> player)
	{
		experienceTextField.textProperty().unbind();
		chemistryTextField.textProperty().unbind();
		energyTextField.textProperty().unbind();

		if (player != null)
		{
			experienceTextField.textProperty().bind(player.experienceProperty().asString());
			chemistryTextField.textProperty().bind(player.chemistryProperty().asString());
			energyTextField.textProperty().bind(player.energyProperty().asString());
		}
	}
}
