package gui.icehockey;

import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.icehockey.IceHockeyBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyCenterAttributeEvaluator;
import evaluators.icehockey.IceHockeyDefBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyForwardAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffBackAttributeEvaluator;
import evaluators.icehockey.IceHockeyOffensiveAttributeEvaluator;
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

	private static final int LEFT_WING = 0;
	private static final int CENTER = 1;
	private static final int RIGHT_WING = 2;
	private static final int LEFT_BACK = 3;
	private static final int RIGHT_BACK = 4;

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
							Side.RIGHT))),
		new FormationTemplate<>(
				"Power Play",
				Arrays.asList(
					new PositionTemplate<>(
							"Left Wing",
							new IceHockeyWingAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Center",
							new IceHockeyCenterAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Wing",
							new IceHockeyWingAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Back",
							new IceHockeyOffBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Back",
							new IceHockeyOffensiveAttributeEvaluator(),
							Side.UNIVERSAL))),
		new FormationTemplate<>(
				"Box Play",
				Arrays.asList(
					new PositionTemplate<>(
							"Left Wing",
							new IceHockeyWingAttributeEvaluator(),
							Side.LEFT,
							true),
					new PositionTemplate<>(
							"Center",
							new IceHockeyForwardAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Wing",
							new IceHockeyForwardAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Back",
							new IceHockeyDefBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Back",
							new IceHockeyDefBackAttributeEvaluator(),
							Side.UNIVERSAL))));

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
		return new FormationTemplate<>(nameComboBox.getText(), getPositionTemplates());
	}

	@Override
	public void setFormationTemplate(FormationTemplate<IceHockeyAttributes> template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());

			leftWingComboBox.setSelectedItem(getAttributeEvaluator(template, LEFT_WING));
			centerComboBox.setSelectedItem(getAttributeEvaluator(template, CENTER));
			rightWingComboBox.setSelectedItem(getAttributeEvaluator(template, RIGHT_WING));
			leftBackComboBox.setSelectedItem(getAttributeEvaluator(template, LEFT_BACK));
			rightBackComboBox.setSelectedItem(getAttributeEvaluator(template, RIGHT_BACK));

			leftWingSideComboBox.setSelectedItem(getSide(template, LEFT_WING));
			centerSideComboBox.setSelectedItem(getSide(template, CENTER));
			rightWingSideComboBox.setSelectedItem(getSide(template, RIGHT_WING));
			leftBackSideComboBox.setSelectedItem(getSide(template, LEFT_BACK));
			rightBackSideComboBox.setSelectedItem(getSide(template, RIGHT_BACK));

			leftWingCheckBox.setSelected(!getIsIgnored(template, LEFT_WING));
			centerCheckBox.setSelected(!getIsIgnored(template, CENTER));
			rightWingCheckBox.setSelected(!getIsIgnored(template, RIGHT_WING));
			leftBackCheckBox.setSelected(!getIsIgnored(template, LEFT_BACK));
			rightBackCheckBox.setSelected(!getIsIgnored(template, RIGHT_BACK));
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

	private List<PositionTemplate<IceHockeyAttributes>> getPositionTemplates()
	{
		return Arrays.asList(
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
					!rightBackCheckBox.isSelected()));
	}
}
