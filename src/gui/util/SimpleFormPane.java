package gui.util;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SimpleFormPane
	extends GridPane
{
	private int rowIndex = 0;

	public SimpleFormPane()
	{
		setHgap(30);
		setVgap(5);
		setPadding(new Insets(10, 20, 10, 20));
	}

	public void addRow(String text, Control control)
	{
		Label label = new Label(text);

		label.setFont(Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 12));

		addRow(rowIndex++, label, control);
	}
}
