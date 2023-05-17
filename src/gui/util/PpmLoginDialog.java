package gui.util;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class PpmLoginDialog
{
	public static Credientials show()
	{
		JPanel panel = new JPanel();

		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameField = new JTextField(10);

		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField(10);

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);

		usernameField.addAncestorListener(new RequestFocusListener());

		String[] options = new String[]
		{
			"OK",
			"Cancel"
		};

		int option = JOptionPane.showOptionDialog(
			null,
			panel,
			"The title",
			JOptionPane.NO_OPTION,
			JOptionPane.PLAIN_MESSAGE,
			null,
			options,
			options[0]);

		if (option == 0)
		{
			return new Credientials(
				usernameField.getText(),
				new String(passwordField.getPassword()));
		}
		else
		{
			return null;
		}
	}

	private static class RequestFocusListener
		implements
			AncestorListener
	{
		@Override
		public void ancestorAdded(final AncestorEvent e)
		{
			final AncestorListener al = this;

			SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run()
				{
					final JComponent component = e.getComponent();
					component.requestFocusInWindow();
					component.removeAncestorListener(al);
				}
			});
		}

		@Override
		public void ancestorMoved(final AncestorEvent e)
		{
		}

		@Override
		public void ancestorRemoved(final AncestorEvent e)
		{
		}
	}

	public static class Credientials
	{
		private final String username;
		private final String password;

		public Credientials(String username, String password)
		{
			this.username = username;
			this.password = password;
		}

		public String getUsername()
		{
			return username;
		}

		public String getPassword()
		{
			return password;
		}
	}
}
