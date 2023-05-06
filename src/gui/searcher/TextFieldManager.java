package gui.searcher;

import javax.swing.JTextField;

public class TextFieldManager
{
	private final JTextField textField;

	public TextFieldManager(JTextField textField)
	{
		this.textField = textField;
	}

	public String get()
	{
		return textField.getText();
	}

	public void set(String value)
	{
		textField.setText(value);
	}
}
