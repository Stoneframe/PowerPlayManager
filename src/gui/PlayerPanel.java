package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Player;

public class PlayerPanel extends JPanel
{
	private static final long serialVersionUID = 8319489027333955979L;

	private JLabel nameLabel;
	private JTextField nameTextField;

	private JLabel sideLabel;
	private JTextField sideTextField;

	public PlayerPanel()
	{
		nameLabel = new JLabel("Name:", JLabel.TRAILING);
		nameTextField = new JTextField(15);
		nameTextField.setEditable(false);

		sideLabel = new JLabel("Side:", JLabel.TRAILING);
		sideTextField = new JTextField(15);
		sideTextField.setEditable(false);

		setBorder(BorderFactory.createTitledBorder("Player"));

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;

		add(nameLabel, c);

		c.gridx = 1;

		add(nameTextField, c);

		c.gridx = 0;
		c.gridy = 1;

		add(sideLabel, c);

		c.weighty = 100;
		c.gridx = 1;

		add(sideTextField, c);
	}

	public void showPlayer(Player player)
	{
		if (player != null)
		{
			nameTextField.setText(player.getName());
			sideTextField.setText(player.getSide().toString());
		}
		else
		{
			nameTextField.setText("");
			sideTextField.setText("");
		}
	}
}
