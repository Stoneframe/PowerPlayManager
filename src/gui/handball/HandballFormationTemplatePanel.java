package gui.handball;

import java.util.Arrays;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.handball.HandballDefBackAttributeEvaluator;
import evaluators.handball.HandballDefPivotAttributeEvaluator;
import evaluators.handball.HandballDefWingAttributeEvaluator;
import evaluators.handball.HandballOffBackAttributeEvaluator;
import evaluators.handball.HandballOffPivotAttributeEvaluator;
import evaluators.handball.HandballOffWingAttributeEvaluator;
import formation.FormationTemplate;
import formation.PositionTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.Side;
import model.handball.HandballAttributes;

public class HandballFormationTemplatePanel
	extends FormationTemplatePanel<HandballAttributes>
{
	private static final long serialVersionUID = -1572635059590322744L;

	private static List<FormationTemplate<HandballAttributes>> defaultTemplates = Arrays.asList(
		new FormationTemplate<HandballAttributes>(
				"Offensive",
				Arrays.asList(
					new PositionTemplate<>(
							"Pivot",
							new HandballOffPivotAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Wing",
							new HandballOffWingAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Right Wing",
							new HandballOffWingAttributeEvaluator(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Left Back",
							new HandballOffBackAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center Back",
							new HandballOffBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Back",
							new HandballOffBackAttributeEvaluator(),
							Side.RIGHT))),
		new FormationTemplate<HandballAttributes>(
				"Defensive",
				Arrays.asList(
					new PositionTemplate<>(
							"Pivot",
							new HandballDefPivotAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Wing",
							new HandballDefWingAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Right Wing",
							new HandballDefWingAttributeEvaluator(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Left Back",
							new HandballDefBackAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center Back",
							new HandballDefBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Back",
							new HandballDefBackAttributeEvaluator(),
							Side.RIGHT))));

	private PpmComboBox<AttributeEvaluator<HandballAttributes>> pivotComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> centerBackComboBox;
	private PpmComboBox<AttributeEvaluator<HandballAttributes>> rightBackComboBox;

	public HandballFormationTemplatePanel(
			PlayerEvaluator<HandballAttributes> playerEvaluator)
	{
		super(defaultTemplates);

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
	public FormationTemplate<HandballAttributes> getFormationTemplate()
	{
		return new FormationTemplate<>(
				nameComboBox.getText(),
				Arrays.asList(
					new PositionTemplate<>(
							"Pivot",
							pivotComboBox.getSelection(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Wing",
							leftWingComboBox.getSelection(),
							Side.LEFT),
					new PositionTemplate<>(
							"Right Wing",
							rightWingComboBox.getSelection(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Left Back",
							leftBackComboBox.getSelection(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center Back",
							centerBackComboBox.getSelection(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Back",
							rightBackComboBox.getSelection(),
							Side.RIGHT)));
	}

	public void setFormationTemplate(FormationTemplate<HandballAttributes> template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());

			pivotComboBox.setSelectedItem(
				template.getPositions().get(0).getAttributeEvaluator());
			leftWingComboBox.setSelectedItem(
				template.getPositions().get(1).getAttributeEvaluator());
			rightWingComboBox.setSelectedItem(
				template.getPositions().get(2).getAttributeEvaluator());
			centerBackComboBox.setSelectedItem(
				template.getPositions().get(3).getAttributeEvaluator());
			leftBackComboBox.setSelectedItem(
				template.getPositions().get(4).getAttributeEvaluator());
			rightBackComboBox.setSelectedItem(
				template.getPositions().get(5).getAttributeEvaluator());
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
