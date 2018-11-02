package gui.util;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class FXSimpleFormPanel
	extends JFXPanel
{
	private static final long serialVersionUID = -3816079110758345782L;

	protected GridPane gridPane;

	private int rowIndex;
	private int columnIndex;

	public FXSimpleFormPanel()
	{
		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(10, 10, 10, 10));

		rowIndex = 0;
	}

	protected void addRow(String labelText, Control... controls)
	{
		Label label = new Label(labelText);
		label.setFont(Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 12));

		columnIndex = 0;
		gridPane.add(label, columnIndex, rowIndex);

		for (Control control : controls)
		{
			columnIndex++;
			gridPane.add(control, columnIndex, rowIndex);
		}

		rowIndex++;
	}
}
