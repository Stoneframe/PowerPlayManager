package gui.icehockey;

import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyWingAttributeEvaluator;
import formation.FormationTemplate;
import formation.PositionTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.Side;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
	extends FormationTemplatePanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = 6358768840962999641L;

	private static List<FormationTemplate<IceHockeyAttributes>> defaultTemplates = Arrays.asList(
		new FormationTemplate<IceHockeyAttributes>(
				"Normal",
				Arrays.asList(
					new PositionTemplate<>(
							"Left Wing",
							new IceHockeyWingAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center",
							new IceHockeyCenterAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Wing",
							new IceHockeyWingAttributeEvaluator(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Left Back",
							new IceHockeyBackAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Right Back",
							new IceHockeyBackAttributeEvaluator(),
							Side.RIGHT))));

	private JCheckBox leftWingCheckBox;
	private JCheckBox centerCheckBox;
	private JCheckBox rightWingCheckBox;
	private JCheckBox leftBackCheckBox;
	private JCheckBox rightBackCheckBox;

	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> centerComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightBackComboBox;

	private PpmComboBox<Side> leftWingSideComboBox;
	private PpmComboBox<Side> centerSideComboBox;
	private PpmComboBox<Side> rightWingSideComboBox;
	private PpmComboBox<Side> leftBackSideComboBox;
	private PpmComboBox<Side> rightBackSideComboBox;

	public IceHockeyFormationTemplatePanel(
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(defaultTemplates);

		leftWingCheckBox = new JCheckBox("Left Wing:", true);
		centerCheckBox = new JCheckBox("Center:", true);
		rightWingCheckBox = new JCheckBox("Right Wing:", true);
		leftBackCheckBox = new JCheckBox("Left Back:", true);
		rightBackCheckBox = new JCheckBox("Right Back:", true);

		leftWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		centerComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		leftBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));

		List<Side> sides = Arrays.asList(Side.LEFT, Side.RIGHT, Side.UNIVERSAL);

		leftWingSideComboBox = new PpmComboBox<>(sides, Side.LEFT);
		centerSideComboBox = new PpmComboBox<>(sides, Side.UNIVERSAL);
		rightWingSideComboBox = new PpmComboBox<>(sides, Side.RIGHT);
		leftBackSideComboBox = new PpmComboBox<>(sides, Side.LEFT);
		rightBackSideComboBox = new PpmComboBox<>(sides, Side.RIGHT);

		addRow(leftWingCheckBox, leftWingComboBox, leftWingSideComboBox);
		addRow(centerCheckBox, centerComboBox, centerSideComboBox);
		addRow(rightWingCheckBox, rightWingComboBox, rightWingSideComboBox);
		addRow(leftBackCheckBox, leftBackComboBox, leftBackSideComboBox);
		addRow(rightBackCheckBox, rightBackComboBox, rightBackSideComboBox);
	}

	@Override
	public FormationTemplate<IceHockeyAttributes> getFormationTemplate()
	{
		return new FormationTemplate<>(
				nameComboBox.getText(),
				Arrays.asList(
					new PositionTemplate<>(
							"Left Wing",
							leftWingComboBox.getSelection(),
							leftWingSideComboBox.getSelection(),
							!leftWingCheckBox.isSelected()),
					new PositionTemplate<>(
							"Center",
							centerComboBox.getSelection(),
							centerSideComboBox.getSelection(),
							!centerCheckBox.isSelected()),
					new PositionTemplate<>(
							"Right Wing",
							rightWingComboBox.getSelection(),
							rightWingSideComboBox.getSelection(),
							!rightWingCheckBox.isSelected()),
					new PositionTemplate<>(
							"Left Back",
							leftBackComboBox.getSelection(),
							leftBackSideComboBox.getSelection(),
							!leftBackCheckBox.isSelected()),
					new PositionTemplate<>(
							"Right Back",
							rightBackComboBox.getSelection(),
							rightBackSideComboBox.getSelection(),
							!rightBackCheckBox.isSelected())));
	}

	@Override
	public void setFormationTemplate(FormationTemplate<IceHockeyAttributes> template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());

			leftWingComboBox.setSelectedItem(
				template.getPositions().get(0).getAttributeEvaluator());
			centerComboBox.setSelectedItem(
				template.getPositions().get(1).getAttributeEvaluator());
			rightWingComboBox.setSelectedItem(
				template.getPositions().get(2).getAttributeEvaluator());
			leftBackComboBox.setSelectedItem(
				template.getPositions().get(3).getAttributeEvaluator());
			rightBackComboBox.setSelectedItem(
				template.getPositions().get(4).getAttributeEvaluator());

			leftWingSideComboBox.setSelectedItem(template.getPositions().get(0).getSide());
			centerSideComboBox.setSelectedItem(template.getPositions().get(1).getSide());
			rightWingSideComboBox.setSelectedItem(template.getPositions().get(2).getSide());
			leftBackSideComboBox.setSelectedItem(template.getPositions().get(3).getSide());
			rightBackSideComboBox.setSelectedItem(template.getPositions().get(4).getSide());

			leftWingCheckBox.setSelected(!template.getPositions().get(0).isIgnored());
			centerCheckBox.setSelected(!template.getPositions().get(1).isIgnored());
			rightWingCheckBox.setSelected(!template.getPositions().get(2).isIgnored());
			leftBackCheckBox.setSelected(!template.getPositions().get(3).isIgnored());
			rightBackCheckBox.setSelected(!template.getPositions().get(4).isIgnored());
		}
		else
		{
			nameComboBox.setText("");

			leftWingComboBox.setSelectedIndex(0);
			centerComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);

			leftWingSideComboBox.setSelectedItem(Side.LEFT);
			centerSideComboBox.setSelectedItem(Side.UNIVERSAL);
			rightWingSideComboBox.setSelectedItem(Side.RIGHT);
			leftBackSideComboBox.setSelectedItem(Side.LEFT);
			rightBackSideComboBox.setSelectedItem(Side.RIGHT);

			leftWingCheckBox.setSelected(true);
			centerCheckBox.setSelected(true);
			rightWingCheckBox.setSelected(true);
			leftBackCheckBox.setSelected(true);
			rightBackCheckBox.setSelected(true);
		}
	}
}
