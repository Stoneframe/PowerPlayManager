package gui.formation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

import formation.FormationTemplate;
import gui.util.PpmComboBox;
import gui.util.PpmTextField;
import gui.util.SimpleFormPanel;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class FormationTemplatePanel<FT extends FormationTemplate>
	extends SimpleFormPanel
{
	private static final long serialVersionUID = -1572635059590322744L;

	private PropertyChangedListener nameTextPropertyChangedListener;

	private PpmComboBox<FT> templateCheckBox;

	protected PpmTextField nameTextField;

	protected FormationTemplatePanel(List<FT> formationTemplates)
	{
		templateCheckBox = new PpmComboBox<>(formationTemplates);
		templateCheckBox.setSelectedIndex(-1);
		templateCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FT template = templateCheckBox.getSelection();

				setFormationTemplate(template);
			}
		});

		nameTextField = new PpmTextField(15);
		nameTextField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (nameTextPropertyChangedListener != null)
				{
					nameTextPropertyChangedListener.propertyChanged(
						nameTextField,
						new PropertyChangedEvent(nameTextField, "Name", nameTextField.getText()));
				}
			}
		});

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Formation Template"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		addRow("Templates:", templateCheckBox);
		addRow("Name:", nameTextField);
	}

	public void setNameTextPropertyChangedListener(PropertyChangedListener listener)
	{
		nameTextPropertyChangedListener = listener;
	}

	public abstract FT getFormationTemplate();

	public abstract void setFormationTemplate(FT template);
}
