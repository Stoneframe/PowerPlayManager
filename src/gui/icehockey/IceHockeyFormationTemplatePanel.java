package gui.icehockey;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.icehockey.IceHockeyAttributeEvaluator;
import formation.icehockey.IceHockeyFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
	extends FormationTemplatePanel<IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = 6358768840962999641L;

	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> centerComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightBackComboBox;

	public IceHockeyFormationTemplatePanel(
			List<AttributeEvaluator<IceHockeyAttributes>> attributeEaluators)
	{
		leftWingComboBox = new PpmComboBox<>(attributeEaluators);
		centerComboBox = new PpmComboBox<>(attributeEaluators);
		rightWingComboBox = new PpmComboBox<>(attributeEaluators);
		leftBackComboBox = new PpmComboBox<>(attributeEaluators);
		rightBackComboBox = new PpmComboBox<>(attributeEaluators);

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
