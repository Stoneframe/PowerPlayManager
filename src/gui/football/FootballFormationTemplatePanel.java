package gui.football;

import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import evaluators.football.FootballCenterBackAttributeEvaluator;
import evaluators.football.FootballCenterForwardAttributeEvaluator;
import evaluators.football.FootballCenterMidfielderAttributeEvaluator;
import evaluators.football.FootballFullBackAttributeEvaluator;
import evaluators.football.FootballGoalkeeperAttributeEvaluator;
import evaluators.football.FootballWideMidfielderAttributeEvaluator;
import formation.FormationTemplate;
import formation.PositionTemplate;
import gui.formation.FormationTemplatePanel;
import gui.util.PpmComboBox;
import model.Side;
import model.football.FootballAttributes;

public class FootballFormationTemplatePanel
	extends FormationTemplatePanel<FootballAttributes>
{
	private static final long serialVersionUID = 1924097433762497846L;

	private static List<FormationTemplate<FootballAttributes>> defaultTemplates = Arrays.asList(
		new FormationTemplate<>(
				"4-4-2",
				Arrays.asList(
					new PositionTemplate<>(
							"Goalkeeper",
							new FootballGoalkeeperAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Left Full Back",
							new FootballFullBackAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center Back",
							new FootballCenterBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Center Back",
							new FootballCenterBackAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Full Back",
							new FootballFullBackAttributeEvaluator(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Left Wide Midfielder",
							new FootballWideMidfielderAttributeEvaluator(),
							Side.LEFT),
					new PositionTemplate<>(
							"Center Midfielder",
							new FootballCenterMidfielderAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Center Midfielder",
							new FootballCenterMidfielderAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Right Wide Midfielder",
							new FootballWideMidfielderAttributeEvaluator(),
							Side.RIGHT),
					new PositionTemplate<>(
							"Center Forward",
							new FootballCenterForwardAttributeEvaluator(),
							Side.UNIVERSAL),
					new PositionTemplate<>(
							"Center Forward",
							new FootballCenterForwardAttributeEvaluator(),
							Side.UNIVERSAL))));

	private JCheckBox goalkeeperCheckBox;
	private JCheckBox fieldPlayer1CheckBox;
	private JCheckBox fieldPlayer2CheckBox;
	private JCheckBox fieldPlayer3CheckBox;
	private JCheckBox fieldPlayer4CheckBox;
	private JCheckBox fieldPlayer5CheckBox;
	private JCheckBox fieldPlayer6CheckBox;
	private JCheckBox fieldPlayer7CheckBox;
	private JCheckBox fieldPlayer8CheckBox;
	private JCheckBox fieldPlayer9CheckBox;
	private JCheckBox fieldPlayer10CheckBox;

	private PpmComboBox<AttributeEvaluator<FootballAttributes>> goalkeeperComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer1ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer2ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer3ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer4ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer5ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer6ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer7ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer8ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer9ComboBox;
	private PpmComboBox<AttributeEvaluator<FootballAttributes>> fieldPlayer10ComboBox;

	private PpmComboBox<Side> fieldPlayer1SideComboBox;
	private PpmComboBox<Side> fieldPlayer2SideComboBox;
	private PpmComboBox<Side> fieldPlayer3SideComboBox;
	private PpmComboBox<Side> fieldPlayer4SideComboBox;
	private PpmComboBox<Side> fieldPlayer5SideComboBox;
	private PpmComboBox<Side> fieldPlayer6SideComboBox;
	private PpmComboBox<Side> fieldPlayer7SideComboBox;
	private PpmComboBox<Side> fieldPlayer8SideComboBox;
	private PpmComboBox<Side> fieldPlayer9SideComboBox;
	private PpmComboBox<Side> fieldPlayer10SideComboBox;

	protected FootballFormationTemplatePanel(
			PlayerEvaluator<FootballAttributes> playerEvaluator)
	{
		super(defaultTemplates);

		goalkeeperCheckBox = new JCheckBox("Goalkeeper", true);
		fieldPlayer1CheckBox = new JCheckBox("Field Player 1", true);
		fieldPlayer2CheckBox = new JCheckBox("Field Player 2", true);
		fieldPlayer3CheckBox = new JCheckBox("Field Player 3", true);
		fieldPlayer4CheckBox = new JCheckBox("Field Player 4", true);
		fieldPlayer5CheckBox = new JCheckBox("Field Player 5", true);
		fieldPlayer6CheckBox = new JCheckBox("Field Player 6", true);
		fieldPlayer7CheckBox = new JCheckBox("Field Player 7", true);
		fieldPlayer8CheckBox = new JCheckBox("Field Player 8", true);
		fieldPlayer9CheckBox = new JCheckBox("Field Player 9", true);
		fieldPlayer10CheckBox = new JCheckBox("Field Player 10", true);

		goalkeeperComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer1ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer2ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer3ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer4ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer5ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer6ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer7ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer8ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer9ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));
		fieldPlayer10ComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(false));

		List<Side> sides = Arrays.asList(Side.LEFT, Side.RIGHT, Side.UNIVERSAL);

		fieldPlayer1SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer2SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer3SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer4SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer5SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer6SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer7SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer8SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer9SideComboBox = new PpmComboBox<>(sides);
		fieldPlayer10SideComboBox = new PpmComboBox<>(sides);

		addRow(goalkeeperCheckBox, goalkeeperComboBox);
		addRow(fieldPlayer1CheckBox, fieldPlayer1ComboBox, fieldPlayer1SideComboBox);
		addRow(fieldPlayer2CheckBox, fieldPlayer2ComboBox, fieldPlayer2SideComboBox);
		addRow(fieldPlayer3CheckBox, fieldPlayer3ComboBox, fieldPlayer3SideComboBox);
		addRow(fieldPlayer4CheckBox, fieldPlayer4ComboBox, fieldPlayer4SideComboBox);
		addRow(fieldPlayer5CheckBox, fieldPlayer5ComboBox, fieldPlayer5SideComboBox);
		addRow(fieldPlayer6CheckBox, fieldPlayer6ComboBox, fieldPlayer6SideComboBox);
		addRow(fieldPlayer7CheckBox, fieldPlayer7ComboBox, fieldPlayer7SideComboBox);
		addRow(fieldPlayer8CheckBox, fieldPlayer8ComboBox, fieldPlayer8SideComboBox);
		addRow(fieldPlayer9CheckBox, fieldPlayer9ComboBox, fieldPlayer9SideComboBox);
		addRow(fieldPlayer10CheckBox, fieldPlayer10ComboBox, fieldPlayer10SideComboBox);
	}

	@Override
	public FormationTemplate<FootballAttributes> getFormationTemplate()
	{
		List<PositionTemplate<FootballAttributes>> positionTemplates = Arrays.asList(
			new PositionTemplate<>(
					"Goalkeeper",
					goalkeeperComboBox.getSelection(),
					Side.UNIVERSAL,
					!goalkeeperCheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer1SideComboBox.getSelection(),
				fieldPlayer1ComboBox.getSelection(),
				!fieldPlayer1CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer2SideComboBox.getSelection(),
				fieldPlayer2ComboBox.getSelection(),
				!fieldPlayer2CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer3SideComboBox.getSelection(),
				fieldPlayer3ComboBox.getSelection(),
				!fieldPlayer3CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer4SideComboBox.getSelection(),
				fieldPlayer4ComboBox.getSelection(),
				!fieldPlayer4CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer5SideComboBox.getSelection(),
				fieldPlayer5ComboBox.getSelection(),
				!fieldPlayer5CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer6SideComboBox.getSelection(),
				fieldPlayer6ComboBox.getSelection(),
				!fieldPlayer6CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer7SideComboBox.getSelection(),
				fieldPlayer7ComboBox.getSelection(),
				!fieldPlayer7CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer8SideComboBox.getSelection(),
				fieldPlayer8ComboBox.getSelection(),
				!fieldPlayer8CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer9SideComboBox.getSelection(),
				fieldPlayer9ComboBox.getSelection(),
				!fieldPlayer9CheckBox.isSelected()),
			getFieldPlayerPositionTemplate(
				fieldPlayer10SideComboBox.getSelection(),
				fieldPlayer10ComboBox.getSelection(),
				!fieldPlayer10CheckBox.isSelected()));

		return new FormationTemplate<>(nameComboBox.getText(), positionTemplates);
	}

	@Override
	public void setFormationTemplate(FormationTemplate<FootballAttributes> template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());

			goalkeeperCheckBox.setSelected(!getIsIgnored(template, 0));
			fieldPlayer1CheckBox.setSelected(!getIsIgnored(template, 1));
			fieldPlayer2CheckBox.setSelected(!getIsIgnored(template, 2));
			fieldPlayer3CheckBox.setSelected(!getIsIgnored(template, 3));
			fieldPlayer4CheckBox.setSelected(!getIsIgnored(template, 4));
			fieldPlayer5CheckBox.setSelected(!getIsIgnored(template, 5));
			fieldPlayer6CheckBox.setSelected(!getIsIgnored(template, 6));
			fieldPlayer7CheckBox.setSelected(!getIsIgnored(template, 7));
			fieldPlayer8CheckBox.setSelected(!getIsIgnored(template, 8));
			fieldPlayer9CheckBox.setSelected(!getIsIgnored(template, 9));
			fieldPlayer10CheckBox.setSelected(!getIsIgnored(template, 10));

			goalkeeperComboBox.setSelectedItem(getAttributeEvaluator(template, 0));
			fieldPlayer1ComboBox.setSelectedItem(getAttributeEvaluator(template, 1));
			fieldPlayer2ComboBox.setSelectedItem(getAttributeEvaluator(template, 2));
			fieldPlayer3ComboBox.setSelectedItem(getAttributeEvaluator(template, 3));
			fieldPlayer4ComboBox.setSelectedItem(getAttributeEvaluator(template, 4));
			fieldPlayer5ComboBox.setSelectedItem(getAttributeEvaluator(template, 5));
			fieldPlayer6ComboBox.setSelectedItem(getAttributeEvaluator(template, 6));
			fieldPlayer7ComboBox.setSelectedItem(getAttributeEvaluator(template, 7));
			fieldPlayer8ComboBox.setSelectedItem(getAttributeEvaluator(template, 8));
			fieldPlayer9ComboBox.setSelectedItem(getAttributeEvaluator(template, 9));
			fieldPlayer10ComboBox.setSelectedItem(getAttributeEvaluator(template, 10));

			fieldPlayer1SideComboBox.setSelectedItem(getSide(template, 1));
			fieldPlayer2SideComboBox.setSelectedItem(getSide(template, 2));
			fieldPlayer3SideComboBox.setSelectedItem(getSide(template, 3));
			fieldPlayer4SideComboBox.setSelectedItem(getSide(template, 4));
			fieldPlayer5SideComboBox.setSelectedItem(getSide(template, 5));
			fieldPlayer6SideComboBox.setSelectedItem(getSide(template, 6));
			fieldPlayer7SideComboBox.setSelectedItem(getSide(template, 7));
			fieldPlayer8SideComboBox.setSelectedItem(getSide(template, 8));
			fieldPlayer9SideComboBox.setSelectedItem(getSide(template, 9));
			fieldPlayer10SideComboBox.setSelectedItem(getSide(template, 10));
		}
		else
		{
			nameComboBox.setText("");

			goalkeeperCheckBox.setSelected(true);
			fieldPlayer1CheckBox.setSelected(true);
			fieldPlayer2CheckBox.setSelected(true);
			fieldPlayer3CheckBox.setSelected(true);
			fieldPlayer4CheckBox.setSelected(true);
			fieldPlayer5CheckBox.setSelected(true);
			fieldPlayer6CheckBox.setSelected(true);
			fieldPlayer7CheckBox.setSelected(true);
			fieldPlayer8CheckBox.setSelected(true);
			fieldPlayer9CheckBox.setSelected(true);
			fieldPlayer10CheckBox.setSelected(true);

			goalkeeperComboBox.setSelectedIndex(0);
			fieldPlayer1ComboBox.setSelectedIndex(0);
			fieldPlayer2ComboBox.setSelectedIndex(0);
			fieldPlayer3ComboBox.setSelectedIndex(0);
			fieldPlayer4ComboBox.setSelectedIndex(0);
			fieldPlayer5ComboBox.setSelectedIndex(0);
			fieldPlayer6ComboBox.setSelectedIndex(0);
			fieldPlayer7ComboBox.setSelectedIndex(0);
			fieldPlayer8ComboBox.setSelectedIndex(0);
			fieldPlayer9ComboBox.setSelectedIndex(0);
			fieldPlayer10ComboBox.setSelectedIndex(0);

			fieldPlayer1SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer2SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer3SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer4SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer5SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer6SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer7SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer8SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer9SideComboBox.setSelectedItem(Side.UNIVERSAL);
			fieldPlayer10SideComboBox.setSelectedItem(Side.UNIVERSAL);
		}
	}

	private PositionTemplate<FootballAttributes> getFieldPlayerPositionTemplate(
			Side side,
			AttributeEvaluator<FootballAttributes> attributeEvaluator,
			boolean isIgnored)
	{
		return new PositionTemplate<>(
				getPositionName(side, attributeEvaluator),
				attributeEvaluator,
				side,
				isIgnored);
	}

	private String getPositionName(
			Side side,
			AttributeEvaluator<FootballAttributes> attributeEvaluator)
	{
		if (side.equals(Side.UNIVERSAL))
		{
			return attributeEvaluator.getName();
		}
		else
		{
			return side + " " + attributeEvaluator.getName();
		}
	}
}
