package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class ParsePanel<A extends Attributes> extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener<A> playersParsedListener;

	private JTextArea textArea;
	private JComboBox<PlayersParser<A>> parsersComboBox;
	private JButton parseButton;

	public ParsePanel(List<PlayersParser<A>> parsers)
	{
		textArea = new JTextArea(2, 25);

		parsersComboBox = new JComboBox<PlayersParser<A>>();
		parsersComboBox.setPreferredSize(new Dimension(200, 25));

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

		parsers.forEach(p -> parsersComboBox.addItem(p));

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(parsersComboBox);
		add(parseButton);
	}

	public void setPlayersParseListener(PlayersParsedListener<A> listener)
	{
		playersParsedListener = listener;
	}
}
