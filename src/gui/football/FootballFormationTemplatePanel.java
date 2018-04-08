package gui.football;

import java.util.Arrays;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
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

	private static List<FormationTemplate<FootballAttributes>> defaultTemplates = Arrays.asList();

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

		addRow("Goalkeeper", goalkeeperComboBox);
		addRow("Field player 1", fieldPlayer1ComboBox, fieldPlayer1SideComboBox);
		addRow("Field player 2", fieldPlayer2ComboBox, fieldPlayer2SideComboBox);
		addRow("Field player 3", fieldPlayer3ComboBox, fieldPlayer3SideComboBox);
		addRow("Field player 4", fieldPlayer4ComboBox, fieldPlayer4SideComboBox);
		addRow("Field player 5", fieldPlayer5ComboBox, fieldPlayer5SideComboBox);
		addRow("Field player 6", fieldPlayer6ComboBox, fieldPlayer6SideComboBox);
		addRow("Field player 7", fieldPlayer7ComboBox, fieldPlayer7SideComboBox);
		addRow("Field player 8", fieldPlayer8ComboBox, fieldPlayer8SideComboBox);
		addRow("Field player 9", fieldPlayer9ComboBox, fieldPlayer9SideComboBox);
		addRow("Field player 10", fieldPlayer10ComboBox, fieldPlayer10SideComboBox);
	}

	@Override
	public FormationTemplate<FootballAttributes> getFormationTemplate()
	{
		List<PositionTemplate<FootballAttributes>> positionTemplates = Arrays.asList(
			new PositionTemplate<>(
					"Goalkeeper",
					goalkeeperComboBox.getSelection(),
					Side.UNIVERSAL),
			getFieldPlayerPositionTemplate(
				fieldPlayer1SideComboBox.getSelection(),
				fieldPlayer1ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer2SideComboBox.getSelection(),
				fieldPlayer2ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer3SideComboBox.getSelection(),
				fieldPlayer3ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer4SideComboBox.getSelection(),
				fieldPlayer4ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer5SideComboBox.getSelection(),
				fieldPlayer5ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer6SideComboBox.getSelection(),
				fieldPlayer6ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer7SideComboBox.getSelection(),
				fieldPlayer7ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer8SideComboBox.getSelection(),
				fieldPlayer8ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer9SideComboBox.getSelection(),
				fieldPlayer9ComboBox.getSelection()),
			getFieldPlayerPositionTemplate(
				fieldPlayer10SideComboBox.getSelection(),
				fieldPlayer10ComboBox.getSelection()));

		return new FormationTemplate<>(nameComboBox.getText(), positionTemplates);
	}

	@Override
	public void setFormationTemplate(FormationTemplate<FootballAttributes> template)
	{
		if (template != null)
		{
			nameComboBox.setText(template.getName());

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
			AttributeEvaluator<FootballAttributes> attributeEvaluator)
	{
		return new PositionTemplate<>(
				getPositionName(side, attributeEvaluator),
				attributeEvaluator,
				side);
	}

	private String getPositionName(
			Side side,
			AttributeEvaluator<FootballAttributes> attributeEvaluator)
	{
		String sideString = side.equals(Side.UNIVERSAL) ? "Center" : side.toString();

		return String.format("%s %s", sideString, attributeEvaluator.getName());
	}
}
