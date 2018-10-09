package gui.player;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import gui.util.SimpleFormPanel;
import model.Attributes;
import model.Player;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class PlayerFormPanel<A extends Attributes>
	extends SimpleFormPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 8882584125182046203L;

	private JTextField experienceTextField;
	private JTextField chemistryTextField;
	private JTextField energyTextField;

	private Player<A> player;

	public PlayerFormPanel()
	{
		experienceTextField = new JTextField(10);
		experienceTextField.setEditable(false);

		chemistryTextField = new JTextField(10);
		chemistryTextField.setEditable(false);

		energyTextField = new JTextField(10);
		energyTextField.setEditable(false);

		addRow("Experience:", experienceTextField);
		addRow("Chemistry:", chemistryTextField);
		addRow("Energy:", energyTextField);

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Form"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	public void bind(Player<A> attributes)
	{
		if (this.player != null)
		{
			this.player.removePropertyChangedListener(this);
		}

		this.player = attributes;

		if (this.player != null)
		{
			this.player.addPropertyChangedListener(this);
		}

		update();
	}

	private void update()
	{
		experienceTextField.setText(player == null ? "" : Integer.toString(player.getExperience()));
		chemistryTextField.setText(player == null ? "" : Integer.toString(player.getChemistry()));
		energyTextField.setText(player == null ? "" : Integer.toString(player.getEnergy()));
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
