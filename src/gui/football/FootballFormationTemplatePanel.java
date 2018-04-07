package gui.football;

import java.util.Arrays;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import formation.FormationTemplate;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFormationTemplate(FormationTemplate<FootballAttributes> template)
	{
		// TODO Auto-generated method stub

	}
}
