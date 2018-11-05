package gui.player;

import gui.util.SimpleFormPane;
import javafx.scene.control.TextField;
import model.Player;

public class InformationPane
	extends SimpleFormPane
{
	private TextField nameTextField;
	private TextField ageTextField;
	private TextField sideTextField;
	private TextField trainingTextField;

	public InformationPane()
	{
		nameTextField = new TextField();
		nameTextField.setEditable(false);

		ageTextField = new TextField();
		ageTextField.setEditable(false);

		sideTextField = new TextField();
		sideTextField.setEditable(false);

		trainingTextField = new TextField();

		addRow("Name: ", nameTextField);
		addRow("Age:", ageTextField);
		addRow("Side:", sideTextField);
		addRow("Training:", trainingTextField);
	}

	public void bind(Player<?> player)
	{
		nameTextField.textProperty().unbind();
		ageTextField.textProperty().unbind();
		sideTextField.textProperty().unbind();
		trainingTextField.textProperty().unbind();

		if (player != null)
		{
			nameTextField.textProperty().bind(player.nameProperty());
			ageTextField.textProperty().bind(player.ageProperty().asString());
			sideTextField.textProperty().bind(player.sideProperty().asString());
			trainingTextField.textProperty().bind(player.trainingProperty().asString());
		}
	}
}
