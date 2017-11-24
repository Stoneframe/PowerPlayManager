package gui.handball;

import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.handball.HandballAttributeEvaluator;
import formation.handball.HandballFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanel
	extends FormationTemplatePanel<HandballFormationTemplate>
{
	private static final long serialVersionUID = -1572635059590322744L;

	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> pivotComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> centerBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightBackComboBox;

	public HandballFormationTemplatePanel(
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		leftWingComboBox = new PpmComboBox<>(attributeEvaluators);
		rightWingComboBox = new PpmComboBox<>(attributeEvaluators);
		pivotComboBox = new PpmComboBox<>(attributeEvaluators);
		leftBackComboBox = new PpmComboBox<>(attributeEvaluators);
		centerBackComboBox = new PpmComboBox<>(attributeEvaluators);
		rightBackComboBox = new PpmComboBox<>(attributeEvaluators);

		addRow("Left Wing:", leftWingComboBox);
		addRow("Right Wing:", rightWingComboBox);
		addRow("Pivot:", pivotComboBox);
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
