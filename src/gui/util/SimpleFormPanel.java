package gui.util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleFormPanel extends JPanel
{
	private static final long serialVersionUID = -4219336409881101811L;

	private GridBagConstraints gbc = new GridBagConstraints();

	public SimpleFormPanel()
	{
		setLayout(new GridBagLayout());

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weighty = 1;
		gbc.gridy = 0;
	}

	public void addRow(String labelText, JComponent... components)
	{
		gbc.gridx = 0;
		add(new JLabel(labelText), gbc);

		for (JComponent component : components)
		{
			gbc.gridx++;
			add(component, gbc);
		}

		gbc.gridy++;
	}
}
