package gui.handball;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import gui.formation.FormationPanel;
import model.handball.HandballFormation;

public class HandballFormationPanel
	extends FormationPanel
{
	private static final long serialVersionUID = -7652823156719901773L;

	private JLabel leftWingLabel;
	private JLabel rightWingLabel;
	private JLabel pivotLabel;
	private JLabel leftBackLabel;
	private JLabel centerBackLabel;
	private JLabel rightBackLabel;

	private JTextField leftWingTextField;
	private JTextField rightWingTextField;
	private JTextField pivotTextField;
	private JTextField leftBackTextField;
	private JTextField centerBackTextField;
	private JTextField rightBackTextField;

	public HandballFormationPanel(HandballFormation formation)
	{
		leftWingLabel = new JLabel("Left Wing:");
		rightWingLabel = new JLabel("Right Wing:");
		pivotLabel = new JLabel("Pivot:");
		leftBackLabel = new JLabel("Left Back:");
		centerBackLabel = new JLabel("Center Back:");
		rightBackLabel = new JLabel("Right Back:");

		leftWingTextField = new JTextField(15);
		leftWingTextField.setEditable(false);
		leftWingTextField.setText(formation.getLeftWing().getName());

		rightWingTextField = new JTextField(15);
		rightWingTextField.setEditable(false);
		rightWingTextField.setText(formation.getRightWing().getName());

		pivotTextField = new JTextField(15);
		pivotTextField.setEditable(false);
		pivotTextField.setText(formation.getPivot().getName());

		leftBackTextField = new JTextField(15);
		leftBackTextField.setEditable(false);
		leftBackTextField.setText(formation.getLeftBack().getName());

		centerBackTextField = new JTextField(15);
		centerBackTextField.setEditable(false);
		centerBackTextField.setText(formation.getCenterBack().getName());

		rightBackTextField = new JTextField(15);
		rightBackTextField.setEditable(false);
		rightBackTextField.setText(formation.getRightBack().getName());

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder(formation.getName()),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(6, 2));

		add(leftWingLabel);
		add(leftWingTextField);

		add(rightWingLabel);
		add(rightWingTextField);

		add(pivotLabel);
		add(pivotTextField);

		add(leftBackLabel);
		add(leftBackTextField);

		add(centerBackLabel);
		add(centerBackTextField);

		add(rightBackLabel);
		add(rightBackTextField);
	}
}
