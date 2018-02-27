package gui.formation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

import evaluators.AttributeEvaluator;
import formation.FormationTemplate;
import gui.util.PpmComboBox;
import gui.util.SimpleFormPanel;
import model.Attributes;
import model.Side;
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

	protected static AttributeEvaluator<?> getAttributeEvaluator(
			FormationTemplate<?> template,
			int index)
	{
		return template.getPositions().get(index).getAttributeEvaluator();
	}

	protected static Side getSide(FormationTemplate<?> template, int index)
	{
		return template.getPositions().get(index).getSide();
	}

	protected static boolean getIsIgnored(FormationTemplate<?> template, int index)
	{
		return template.getPositions().get(index).isIgnored();
	}
}
