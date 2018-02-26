package gui.icehockey;

import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import formation.Position;
import formation.icehockey.IceHockeyFormationTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.Side;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
	extends FormationTemplatePanel<IceHockeyAttributes, IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = 6358768840962999641L;

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
		super(IceHockeyFormationTemplate.getStandardFormationTemplates());

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
	public IceHockeyFormationTemplate getFormationTemplate()
	{
		return new IceHockeyFormationTemplate(
				nameComboBox.getText(),
				Arrays.asList(
					new Position<>(
							leftWingComboBox.getSelection(),
							leftWingSideComboBox.getSelection(),
							!leftWingCheckBox.isSelected()),
					new Position<>(
							centerComboBox.getSelection(),
							centerSideComboBox.getSelection(),
							!centerCheckBox.isSelected()),
					new Position<>(
							rightWingComboBox.getSelection(),
							rightWingSideComboBox.getSelection(),
							!rightWingCheckBox.isSelected()),
					new Position<>(
							leftBackComboBox.getSelection(),
							leftBackSideComboBox.getSelection(),
							!leftBackCheckBox.isSelected()),
					new Position<>(
							rightBackComboBox.getSelection(),
							rightBackSideComboBox.getSelection(),
							!rightBackCheckBox.isSelected())));
	}

	@Override
	public void setFormationTemplate(IceHockeyFormationTemplate template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			centerComboBox.setSelectedItem(template.getCenterEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());

			leftWingSideComboBox.setSelectedItem(template.getPositions().get(0).getSide());
			centerSideComboBox.setSelectedItem(template.getPositions().get(1).getSide());
			rightWingSideComboBox.setSelectedItem(template.getPositions().get(2).getSide());
			leftBackSideComboBox.setSelectedItem(template.getPositions().get(3).getSide());
			rightBackSideComboBox.setSelectedItem(template.getPositions().get(4).getSide());
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
		}
	}
}
