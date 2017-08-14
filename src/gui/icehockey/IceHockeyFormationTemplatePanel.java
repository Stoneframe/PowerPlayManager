package gui.icehockey;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import builders.formation.icehockey.IceHockeyFormationTemplate;
import evaluators.PlayerEvaluator;
import evaluators.icehockey.IceHockeyPlayerEvaluator;
import gui.formation.FormationTemplatePanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
		extends FormationTemplatePanel<IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = 6358768840962999641L;

	private JLabel nameLabel;
	private JLabel leftWingLabel;
	private JLabel centerLabel;
	private JLabel rightWingLabel;
	private JLabel leftBackLabel;
	private JLabel rightBackLabel;

	private JTextField nameTextField;
	private JComboBox<PlayerEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private JComboBox<PlayerEvaluator<IceHockeyAttributes>> centerComboBox;
	private JComboBox<PlayerEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private JComboBox<PlayerEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private JComboBox<PlayerEvaluator<IceHockeyAttributes>> rightBackComboBox;

	public IceHockeyFormationTemplatePanel(List<PlayerEvaluator<IceHockeyAttributes>> evaluators)
	{
		nameLabel = new JLabel("Name:");
		leftWingLabel = new JLabel("Left Wing:");
		centerLabel = new JLabel("Center:");
		rightWingLabel = new JLabel("Right Wing:");
		leftBackLabel = new JLabel("Left Back:");
		rightBackLabel = new JLabel("Right Back:");

		nameTextField = new JTextField(15);

		leftWingComboBox = new JComboBox<PlayerEvaluator<IceHockeyAttributes>>();
		centerComboBox = new JComboBox<PlayerEvaluator<IceHockeyAttributes>>();
		rightWingComboBox = new JComboBox<PlayerEvaluator<IceHockeyAttributes>>();
		leftBackComboBox = new JComboBox<PlayerEvaluator<IceHockeyAttributes>>();
		rightBackComboBox = new JComboBox<PlayerEvaluator<IceHockeyAttributes>>();

		evaluators.forEach(e -> leftWingComboBox.addItem(e));
		evaluators.forEach(e -> centerComboBox.addItem(e));
		evaluators.forEach(e -> rightWingComboBox.addItem(e));
		evaluators.forEach(e -> leftBackComboBox.addItem(e));
		evaluators.forEach(e -> rightBackComboBox.addItem(e));

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Formation Template"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(7, 2));

		add(nameLabel);
		add(nameTextField);

		add(leftWingLabel);
		add(leftWingComboBox);

		add(centerLabel);
		add(centerComboBox);

		add(rightWingLabel);
		add(rightWingComboBox);

		add(leftBackLabel);
		add(leftBackComboBox);

		add(rightBackLabel);
		add(rightBackComboBox);
	}

	@Override
	public IceHockeyFormationTemplate getFormationTemplate()
	{
		return new IceHockeyFormationTemplate(
				nameTextField.getText(),
				(IceHockeyPlayerEvaluator) leftWingComboBox.getSelectedItem(),
				(IceHockeyPlayerEvaluator) centerComboBox.getSelectedItem(),
				(IceHockeyPlayerEvaluator) rightWingComboBox.getSelectedItem(),
				(IceHockeyPlayerEvaluator) leftBackComboBox.getSelectedItem(),
				(IceHockeyPlayerEvaluator) rightBackComboBox.getSelectedItem());
	}

	@Override
	public void setFormationTemplate(IceHockeyFormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			centerComboBox.setSelectedItem(template.getCenterEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameTextField.setText("");
			leftWingComboBox.setSelectedIndex(0);
			centerComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
