package gui.player;

import javafx.beans.property.ReadOnlyIntegerProperty;
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
		bindRating(attribute.ratingProperty());
		bindQuality(attribute.qualityProperty());
	}

	public void bindRating(ReadOnlyIntegerProperty ratingProperty)
	{
		ratingTextField.textProperty().unbind();

		if (ratingProperty != null)
		{
			ratingTextField.textProperty().bind(ratingProperty.asString());
		}
	}

	public void bindQuality(ReadOnlyIntegerProperty qualityProperty)
	{
		qualityTextField.textProperty().unbind();

		if (qualityProperty != null)
		{
			qualityTextField.textProperty().bind(qualityProperty.asString());
		}
	}
}
