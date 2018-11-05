package gui.util;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class NamedPane
	extends BorderPane
{
	public NamedPane(String name)
	{
		setBorder(
			new Border(
					new BorderStroke(
							Color.GRAY,
							BorderStrokeStyle.SOLID,
							new CornerRadii(10),
							BorderWidths.DEFAULT)));

		Label nameLabel = new Label(name);

		nameLabel.setFont(Font.font("Dialog", FontWeight.MEDIUM, FontPosture.REGULAR, 14));

		VBox nameBox = new VBox();

		nameBox.getChildren().add(nameLabel);
		nameBox.setPadding(new Insets(2, 10, 2, 10));
		nameBox.setBackground(
			new Background(
					new BackgroundFill(
							Color.LIGHTGRAY,
							new CornerRadii(10, 10, 0, 0, false),
							Insets.EMPTY)));

		setTop(nameBox);
		setBackground(
			new Background(
					new BackgroundFill(
							Color.TRANSPARENT,
							new CornerRadii(0, 0, 10, 10, false),
							Insets.EMPTY)));
	}

	public void setContent(Node node)
	{
		setCenter(node);
	}
}
