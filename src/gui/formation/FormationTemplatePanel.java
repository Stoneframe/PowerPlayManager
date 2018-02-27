package gui.formation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

import formation.FormationTemplate;
import gui.util.PpmComboBox;
import gui.util.SimpleFormPanel;
import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class FormationTemplatePanel<A extends Attributes>
	extends SimpleFormPanel
{
	private static final long serialVersionUID = -1572635059590322744L;

	private PropertyChangedListener nameTextPropertyChangedListener;

	protected PpmComboBox<FormationTemplate<A>> nameComboBox;

	protected FormationTemplatePanel(List<FormationTemplate<A>> formationTemplates)
	{
		nameComboBox = new PpmComboBox<>(formationTemplates);
		nameComboBox.setEditable(true);
		nameComboBox.setSelectedIndex(-1);
		nameComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FormationTemplate<A> template = nameComboBox.getSelection();

				if (template != null)
				{
					setFormationTemplate(template);
				}

				if (nameTextPropertyChangedListener != null)
				{
					nameTextPropertyChangedListener.propertyChanged(
						nameComboBox,
						new PropertyChangedEvent(nameComboBox, "Name", nameComboBox.getText()));
				}
			}
		});

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Formation Template"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		addRow("Name:", nameComboBox);
	}

	public void setNameTextPropertyChangedListener(PropertyChangedListener listener)
	{
		nameTextPropertyChangedListener = listener;
	}

	public abstract FormationTemplate<A> getFormationTemplate();

	public abstract void setFormationTemplate(FormationTemplate<A> template);
}
