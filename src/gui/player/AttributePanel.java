package gui.player;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.Attribute;

public class AttributePanel
	extends GridPane
{
	private final Label nameLabel;
	private final TextField ratingTextField;
	private final TextField qualityTextField;

	public AttributePanel(String name)
	{
		nameLabel = new Label(name);
		nameLabel.setFont(Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 12));
		nameLabel.setPrefWidth(30);

		ratingTextField = new TextField();
		ratingTextField.setPrefWidth(50);
		ratingTextField.setEditable(false);

		qualityTextField = new TextField();
		qualityTextField.setPrefWidth(50);
		qualityTextField.setEditable(false);

		setHgap(30);

		add(nameLabel, 0, 0);
		add(ratingTextField, 1, 0);
		add(qualityTextField, 2, 0);
	}

	public void bind(Attribute attribute)
	{
		ratingTextField.textProperty().unbind();
		qualityTextField.textProperty().unbind();

		if (attribute != null)
		{
			ratingTextField.textProperty().bind(attribute.ratingProperty().asString());
			qualityTextField.textProperty().bind(attribute.qualityProperty().asString());
		}
	}
}
