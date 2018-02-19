package gui.handball;

import java.util.Arrays;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.handball.HandballDefBackAttributeEvaluator;
import evaluators.handball.HandballDefPivotAttributeEvaluator;
import evaluators.handball.HandballDefWingAttributeEvaluator;
import evaluators.handball.HandballOffBackAttributeEvaluator;
import evaluators.handball.HandballOffPivotAttributeEvaluator;
import evaluators.handball.HandballOffWingAttributeEvaluator;
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
		super(
				Arrays.asList(
					new HandballFormationTemplate(
							"Offensive",
							new HandballOffPivotAttributeEvaluator(),
							new HandballOffWingAttributeEvaluator(),
							new HandballOffWingAttributeEvaluator(),
							new HandballOffBackAttributeEvaluator(),
							new HandballOffBackAttributeEvaluator(),
							new HandballOffBackAttributeEvaluator()),
					new HandballFormationTemplate(
							"Defensive",
							new HandballDefPivotAttributeEvaluator(),
							new HandballDefWingAttributeEvaluator(),
							new HandballDefWingAttributeEvaluator(),
							new HandballDefBackAttributeEvaluator(),
							new HandballDefBackAttributeEvaluator(),
							new HandballDefBackAttributeEvaluator())));

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

	@Override
	public HandballFormationTemplate getFormationTemplate()
	{
		return new HandballFormationTemplate(
				nameComboBox.getText(),
				pivotComboBox.getSelection(),
				leftWingComboBox.getSelection(),
				rightWingComboBox.getSelection(),
				centerBackComboBox.getSelection(),
				leftBackComboBox.getSelection(),
				rightBackComboBox.getSelection());
	}

	public void setFormationTemplate(HandballFormationTemplate template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());
			pivotComboBox.setSelectedItem(template.getPivotEvaluator());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			centerBackComboBox.setSelectedItem(template.getCenterBackEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameComboBox.setText("");
			pivotComboBox.setSelectedIndex(0);
			leftWingComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			centerBackComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
