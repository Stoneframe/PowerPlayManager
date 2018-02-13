package gui.handball;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.handball.HandballAttributeEvaluator;
import formation.handball.HandballFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanel
	extends FormationTemplatePanel<HandballFormationTemplate>
{
	private static final long serialVersionUID = -1572635059590322744L;

	private PpmComboBox<AttributeEvaluator<HandballAttributes>> pivotComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> centerBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightBackComboBox;

	public HandballFormationTemplatePanel(
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		pivotComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		leftWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		leftBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		centerBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));

		addRow("Pivot:", pivotComboBox);
		addRow("Left Wing:", leftWingComboBox);
		addRow("Right Wing:", rightWingComboBox);
		addRow("Left Back:", leftBackComboBox);
		addRow("Center Back:", centerBackComboBox);
		addRow("Right Back:", rightBackComboBox);
	}

	public HandballFormationTemplate getFormationTemplate()
	{
		return new HandballFormationTemplate(
				nameTextField.getText(),
				(HandballAttributeEvaluator)pivotComboBox.getSelectedItem(),
				(HandballAttributeEvaluator)leftWingComboBox.getSelectedItem(),
				(HandballAttributeEvaluator)rightWingComboBox.getSelectedItem(),
				(HandballAttributeEvaluator)centerBackComboBox.getSelectedItem(),
				(HandballAttributeEvaluator)leftBackComboBox.getSelectedItem(),
				(HandballAttributeEvaluator)rightBackComboBox.getSelectedItem());
	}

	public void setFormationTemplate(HandballFormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			pivotComboBox.setSelectedItem(template.getPivotEvaluator());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			centerBackComboBox.setSelectedItem(template.getCenterBackEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameTextField.setText("");
			pivotComboBox.setSelectedIndex(0);
			leftWingComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			centerBackComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
