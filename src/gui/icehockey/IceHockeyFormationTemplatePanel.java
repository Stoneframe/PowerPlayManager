package gui.icehockey;

import java.util.List;

import javax.swing.JComboBox;

import evaluators.AttributeEvaluator;
import evaluators.icehockey.IceHockeyAttributeEvaluator;
import formation.icehockey.IceHockeyFormationTemplate;
import gui.formation.FormationTemplatePanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
	extends FormationTemplatePanel<IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = 6358768840962999641L;

	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> centerComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> rightBackComboBox;

	public IceHockeyFormationTemplatePanel(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEaluators)
	{
		leftWingComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		centerComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		rightWingComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		leftBackComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		rightBackComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();

		attributeEaluators.forEach(e -> leftWingComboBox.addItem(e));
		attributeEaluators.forEach(e -> centerComboBox.addItem(e));
		attributeEaluators.forEach(e -> rightWingComboBox.addItem(e));
		attributeEaluators.forEach(e -> leftBackComboBox.addItem(e));
		attributeEaluators.forEach(e -> rightBackComboBox.addItem(e));

		addRow("Left Wing:", leftWingComboBox);
		addRow("Center:", centerComboBox);
		addRow("Right Wing:", rightWingComboBox);
		addRow("Left Back:", leftBackComboBox);
		addRow("Right Back:", rightBackComboBox);
	}

	@Override
	public IceHockeyFormationTemplate getFormationTemplate()
	{
		return new IceHockeyFormationTemplate(
				nameTextField.getText(),
				(IceHockeyAttributeEvaluator)leftWingComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator)centerComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator)rightWingComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator)leftBackComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator)rightBackComboBox.getSelectedItem());
	}

	@Override
	public void setFormationTemplate(IceHockeyFormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			centerComboBox.setSelectedItem(template.getCenterEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameTextField.setText("");
			leftWingComboBox.setSelectedIndex(0);
			centerComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
