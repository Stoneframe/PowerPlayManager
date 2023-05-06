package gui.searcher;

import javax.swing.JTextField;

import gui.util.IntegerUtil;

public class NumFieldManager
{
	private final JTextField textField;

	private final int defaultValue;

	public NumFieldManager(JTextField textField, int defaultValue)
	{
		this.textField = textField;
		this.defaultValue = defaultValue;
	}

	public int get()
	{
		return IntegerUtil.parseInt(textField.getText(), defaultValue);
	}

	public void set(int value)
	{
		if (value == defaultValue)
		{
			textField.setText("");
		}
		else
		{
			textField.setText(Integer.toString(value));
		}
	}
}
