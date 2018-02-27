package gui.formation;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import formation.Formation;
import formation.Position;
import gui.util.SimpleFormPanel;
import model.Attributes;
import model.Player;

public class FormationPanel<A extends Attributes>
	extends SimpleFormPanel
{
	private static final long serialVersionUID = 3994325227659282169L;

	public FormationPanel(Formation<A> formation)
	{
		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder(formation.getName()),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		for (Position<A> position : formation.getPositions())
		{
			addRow(position.getName(), getTextField(position.getPlayer()));
		}
	}

	private static JTextField getTextField(Player<?> player)
	{
		JTextField textField = new JTextField(15);
		textField.setEditable(false);
		textField.setText(player.getName());
		return textField;
	}
}
