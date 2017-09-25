package gui.formation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import builders.formation.FormationTemplate;
import gui.util.SimpleFormPanel;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class FormationTemplatePanel<FT extends FormationTemplate> extends SimpleFormPanel
{
	private static final long serialVersionUID = -1572635059590322744L;

	private PropertyChangedListener nameTextPropertyChangedListener;

	protected JTextField nameTextField;

	protected FormationTemplatePanel()
	{
		nameTextField = new JTextField(15);
		nameTextField.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
				if (nameTextPropertyChangedListener != null)
				{
					nameTextPropertyChangedListener.propertyChanged(
						nameTextField,
						new PropertyChangedEvent(nameTextField, "Name", nameTextField.getText()));
				}
			}

			public void keyReleased(KeyEvent e)
			{
			}

			public void keyPressed(KeyEvent e)
			{
			}
		});

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Formation Template"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		addRow("Name:", nameTextField);
	}

	public void setNameTextPropertyChangedListener(PropertyChangedListener listener)
	{
		nameTextPropertyChangedListener = listener;
	}

	public abstract FT getFormationTemplate();

	public abstract void setFormationTemplate(FT template);
}
