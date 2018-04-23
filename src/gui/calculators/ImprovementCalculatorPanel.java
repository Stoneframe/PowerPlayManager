package gui.calculators;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import calculators.ImprovementCalculator;

public class ImprovementCalculatorPanel
	extends JPanel
{
	private static final long serialVersionUID = -4980631306468019235L;

	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	private JButton calculateButton;
	private JButton clearButton;

	private ImprovementCalculator calculator;

	public ImprovementCalculatorPanel()
	{
		calculator = new ImprovementCalculator();

		inputTextArea = new JTextArea();
		inputTextArea.setBorder(BorderFactory.createEtchedBorder());

		outputTextArea = new JTextArea();
		outputTextArea.setBorder(BorderFactory.createEtchedBorder());

		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(e -> onCalculate());

		clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> onClear());

		placeComponents();

		setPreferredSize(new Dimension(400, 600));
	}

	private void placeComponents()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.weightx = 1;

		gbc.gridy = 0;
		gbc.weighty = 1000;
		add(new JScrollPane(inputTextArea), gbc);

		gbc.gridy = 1;
		gbc.weighty = 1;
		add(calculateButton, gbc);

		gbc.gridy = 2;
		gbc.weighty = 500;
		add(new JScrollPane(outputTextArea), gbc);

		gbc.gridy = 3;
		gbc.weighty = 1;
		add(clearButton, gbc);
	}

	private void onCalculate()
	{
		outputTextArea.setText(calculator.calculate(inputTextArea.getText()));

		inputTextArea.setText("");
	}

	private void onClear()
	{
		inputTextArea.setText("");
		outputTextArea.setText("");
	}
}
