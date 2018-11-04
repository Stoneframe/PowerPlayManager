package gui.player;

import gui.util.SimpleFormPane;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import model.Attributes;
import model.Player;

public class PlayerFormPanel<A extends Attributes>
	extends TitledPane
{
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

		SimpleFormPane formPane = new SimpleFormPane();

		formPane.setPadding(new Insets(10, 20, 10, 20));

		formPane.addRow("Experience:", experienceTextField);
		formPane.addRow("Chemistry:", chemistryTextField);
		formPane.addRow("Energy:", energyTextField);

		setText("Form");
		setCollapsible(false);
		setContent(formPane);
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
