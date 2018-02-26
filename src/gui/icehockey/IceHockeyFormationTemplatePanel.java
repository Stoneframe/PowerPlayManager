package gui.icehockey;

import java.util.Arrays;

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

	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> centerComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private PpmComboBox<AttributeEvaluator<IceHockeyAttributes>> rightBackComboBox;

	public IceHockeyFormationTemplatePanel(
			PlayerEvaluator<IceHockeyAttributes> playerEvaluator)
	{
		super(IceHockeyFormationTemplate.getStandardFormationTemplates());

		leftWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		centerComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightWingComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		leftBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		rightBackComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));

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
				nameComboBox.getText(),
				Arrays.asList(
					new Position<>(leftWingComboBox.getSelection(), Side.LEFT),
					new Position<>(centerComboBox.getSelection(), Side.UNIVERSAL),
					new Position<>(rightWingComboBox.getSelection(), Side.RIGHT),
					new Position<>(leftBackComboBox.getSelection(), Side.LEFT),
					new Position<>(rightBackComboBox.getSelection(), Side.RIGHT)));
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
		}
		else
		{
			nameComboBox.setText("");
			leftWingComboBox.setSelectedIndex(0);
			centerComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
