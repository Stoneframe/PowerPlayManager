package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.player.PlayersParsedEvent;
import gui.player.PlayersParsedListener;
import model.Attributes;
import model.Player;
import parsers.ParseException;
import parsers.players.PlayersParser;

public class ParsePanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener<A> playersParsedListener;

	private JTextArea textArea;
	private JComboBox<PlayersParser<A>> parsersComboBox;
	private JButton parseButton;

	public ParsePanel(List<PlayersParser<A>> parsers)
	{
		textArea = new JTextArea(30, 50);

		parsersComboBox = new JComboBox<PlayersParser<A>>();

		parseButton = new JButton("Parse");
		parseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					PlayersParser<A> playersParser = parsersComboBox
							.getItemAt(parsersComboBox.getSelectedIndex());

					List<Player<A>> players = playersParser
							.parsePlayers(textArea.getText());

					textArea.setText("");

					if (playersParsedListener != null)
					{
						playersParsedListener.playersParsed(
							this,
							new PlayersParsedEvent<A>(this, players));
					}
				}
				catch (ParseException ex)
				{
					System.out.println("Unable to parse input");
				}
			}
		});

		parsers.forEach(parsersComboBox::addItem);

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;

		add(new JScrollPane(textArea), gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 2;

		add(parsersComboBox, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;

		add(parseButton, gbc);
	}

	public void setPlayersParseListener(PlayersParsedListener<A> listener)
	{
		playersParsedListener = listener;
	}
}
