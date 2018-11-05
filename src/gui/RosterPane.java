package gui;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Attributes;
import model.Player;
import model.Roster;

public class RosterPane<A extends Attributes>
	extends BorderPane
{
	private final TableView<Player<A>> table;

	public RosterPane(Roster<A> roster)
	{
		TableColumn<Player<A>, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Player<A>, String> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		table = new TableView<>(roster.getPlayers());
		table.setEditable(false);

		table.getColumns().add(nameColumn);
		table.getColumns().add(ageColumn);

		Text text = new Text("Roster");
		text.setFont(Font.font("Dialog", FontWeight.BOLD, FontPosture.REGULAR, 20));

		BorderPane.setMargin(text, new Insets(5, 0, 5, 0));

		setTop(text);
		setCenter(table);
	}
}
