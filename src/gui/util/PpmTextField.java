package gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class PpmTextField
	extends JTextField
{
	private static final long serialVersionUID = -806015638000221963L;

	public PpmTextField()
	{
	}

	public PpmTextField(String text)
	{
		super(text);
	}

	public PpmTextField(int columns)
	{
		super(columns);
	}

	public PpmTextField(String text, int columns)
	{
		super(text, columns);
	}

	public PpmTextField(Document doc, String text, int columns)
	{
		super(doc, text, columns);
	}

	@Override
	protected Document createDefaultModel()
	{
		Document document = super.createDefaultModel();

		document.addDocumentListener(new SimpleDocumentListener()
		{
			public void textChanged(DocumentEvent e)
			{
				onTextChanged(e);
			}
		});

		return document;
	}

	private void onTextChanged(DocumentEvent e)
	{
		for (ActionListener listener : getActionListeners())
		{
			listener.actionPerformed(
				new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "TextChanged"));
		}
	}

	private abstract class SimpleDocumentListener
		implements
			DocumentListener
	{

		@Override
		public void changedUpdate(DocumentEvent e)
		{
			textChanged(e);
		}

		@Override
		public void insertUpdate(DocumentEvent e)
		{
			textChanged(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e)
		{
			textChanged(e);
		}

		public abstract void textChanged(DocumentEvent e);
	}
}
