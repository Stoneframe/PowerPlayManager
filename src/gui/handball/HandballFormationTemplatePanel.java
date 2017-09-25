package gui.handball;

import java.util.List;

import javax.swing.JComboBox;

import builders.formation.handball.HandballFormationTemplate;
import evaluators.AttributeEvaluator;
import evaluators.handball.HandballAttributeEvaluator;
import gui.formation.FormationTemplatePanel;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanel
		extends
		FormationTemplatePanel<HandballFormationTemplate>
{
	private static final long serialVersionUID = -1572635059590322744L;

	private JComboBox<AttributeEvaluator<HandballAttributes>> leftWingComboBox;
	private JComboBox<AttributeEvaluator<HandballAttributes>> rightWingComboBox;
	private JComboBox<AttributeEvaluator<HandballAttributes>> pivotComboBox;
	private JComboBox<AttributeEvaluator<HandballAttributes>> leftBackComboBox;
	private JComboBox<AttributeEvaluator<HandballAttributes>> centerBackComboBox;
	private JComboBox<AttributeEvaluator<HandballAttributes>> rightBackComboBox;

	public HandballFormationTemplatePanel(
			List<AttributeEvaluator<HandballAttributes>> attributeEvaluators)
	{
		leftWingComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();
		rightWingComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();
		pivotComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();
		leftBackComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();
		centerBackComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();
		rightBackComboBox = new JComboBox<AttributeEvaluator<HandballAttributes>>();

		attributeEvaluators.forEach(e -> leftWingComboBox.addItem(e));
		attributeEvaluators.forEach(e -> rightWingComboBox.addItem(e));
		attributeEvaluators.forEach(e -> pivotComboBox.addItem(e));
		attributeEvaluators.forEach(e -> leftBackComboBox.addItem(e));
		attributeEvaluators.forEach(e -> centerBackComboBox.addItem(e));
		attributeEvaluators.forEach(e -> rightBackComboBox.addItem(e));

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
				(HandballAttributeEvaluator) pivotComboBox.getSelectedItem(),
				(HandballAttributeEvaluator) leftWingComboBox.getSelectedItem(),
				(HandballAttributeEvaluator) rightWingComboBox.getSelectedItem(),
				(HandballAttributeEvaluator) centerBackComboBox.getSelectedItem(),
				(HandballAttributeEvaluator) leftBackComboBox.getSelectedItem(),
				(HandballAttributeEvaluator) rightBackComboBox.getSelectedItem());
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
