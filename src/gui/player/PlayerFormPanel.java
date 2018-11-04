package gui.player;

import gui.util.SimpleFormPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import model.Attributes;
import model.Player;

public class PlayerFormPanel<A extends Attributes>
	extends JFXPanel
{
	private static final long serialVersionUID = 8882584125182046203L;

	private final TextField experienceTextField;
	private final TextField chemistryTextField;
	private final TextField energyTextField;

	public PlayerFormPanel()
	{
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
				SimpleFormPane formPane = new SimpleFormPane();

				formPane.addRow("Experience:", experienceTextField);
				formPane.addRow("Chemistry:", chemistryTextField);
				formPane.addRow("Energy:", energyTextField);

				TitledPane titledPane = new TitledPane();

				titledPane.setText("Form");
				titledPane.setCollapsible(false);
				titledPane.setContent(formPane);

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
