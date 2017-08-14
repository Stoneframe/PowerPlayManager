package gui.icehockey;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import gui.formation.FormationPanel;
import model.icehockey.IceHockeyFormation;

public class IceHockeyFormationPanel extends FormationPanel
{
	private static final long serialVersionUID = 6877705226792737170L;

	private JLabel leftWingLabel;
	private JLabel centerLabel;
	private JLabel rightWingLabel;
	private JLabel leftBackLabel;
	private JLabel rightBackLabel;

	private JTextField leftWingTextField;
	private JTextField centerTextField;
	private JTextField rightWingTextField;
	private JTextField leftBackTextField;
	private JTextField rightBackTextField;

	public IceHockeyFormationPanel(IceHockeyFormation formation)
	{
		leftWingLabel = new JLabel("Left Wing:");
		centerLabel = new JLabel("Center:");
		rightWingLabel = new JLabel("Right Wing:");
		leftBackLabel = new JLabel("Left Back:");
		rightBackLabel = new JLabel("Right Back:");

		leftWingTextField = new JTextField(15);
		leftWingTextField.setEditable(false);
		leftWingTextField.setText(formation.getLeftWing().getName());

		centerTextField = new JTextField(15);
		centerTextField.setEditable(false);
		centerTextField.setText(formation.getCenter().getName());

		rightWingTextField = new JTextField(15);
		rightWingTextField.setEditable(false);
		rightWingTextField.setText(formation.getRightWing().getName());

		leftBackTextField = new JTextField(15);
		leftBackTextField.setEditable(false);
		leftBackTextField.setText(formation.getLeftBack().getName());

		rightBackTextField = new JTextField(15);
		rightBackTextField.setEditable(false);
		rightBackTextField.setText(formation.getRightBack().getName());

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder(formation.getName()),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(5, 2));

		add(leftWingLabel);
		add(leftWingTextField);
		
		add(centerLabel);
		add(centerTextField);

		add(rightWingLabel);
		add(rightWingTextField);

		add(leftBackLabel);
		add(leftBackTextField);

		add(rightBackLabel);
		add(rightBackTextField);
	}
}
