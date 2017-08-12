package gui.handball;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import evaluators.PlayerEvaluator;
import gui.PlayerEvaluatorsParsedEvent;
import gui.PlayerEvaluatorsParsedListener;
import gui.PlayersParsedEvent;
import gui.PlayersParsedListener;
import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;
import parsers.playerevaluators.HandballPlayerEvaluatorsParser;
import parsers.players.PlayersParser;
import parsers.players.handball.HandballMarketPlayersParser;
import parsers.players.handball.HandballOverviewPlayersParser;
import parsers.players.handball.HandballPractisePlayersParser;
import parsers.players.handball.HandballPractiseProPlayersParser;

public class HandballParsePanel extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener<HandballAttributes> playersParsedListener;
	private PlayerEvaluatorsParsedListener<
			HandballAttributes> playerEvaluatorParsedListener;

	private JTextArea textArea;
	private JButton parsePractiseButton;
	private JButton parseProPractiseButton;
	private JButton parseOverviewButton;
	private JButton parseMarketButton;
	private JButton parseTrainingProgramsButton;

	public HandballParsePanel()
	{
		textArea = new JTextArea(2, 25);

		parsePractiseButton = new JButton("Practise Parse");
		parsePractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new HandballPractisePlayersParser());
			}
		});

		parseProPractiseButton = new JButton("Practise (Pro) Parse");
		parseProPractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new HandballPractiseProPlayersParser());
			}
		});

		parseOverviewButton = new JButton("Overview Parse");
		parseOverviewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new HandballOverviewPlayersParser());
			}
		});

		parseMarketButton = new JButton("Market Parse");
		parseMarketButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new HandballMarketPlayersParser());
			}
		});

		parseTrainingProgramsButton = new JButton("Training Programs Parse");
		parseTrainingProgramsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayerEvaluators();
			}
		});

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(parsePractiseButton);
		add(parseProPractiseButton);
		add(parseOverviewButton);
		add(parseMarketButton);
		add(parseTrainingProgramsButton);
	}

	public void setPlayersParseListener(
			PlayersParsedListener<HandballAttributes> listener)
	{
		playersParsedListener = listener;
	}

	public void setPlayerEvaluatorsParsedListener(
			PlayerEvaluatorsParsedListener<HandballAttributes> listener)
	{
		playerEvaluatorParsedListener = listener;
	}

	private void parsePlayers(PlayersParser<HandballAttributes> playersParser)
	{
		try
		{
			List<Player<HandballAttributes>> players = playersParser
					.parsePlayers(textArea.getText());

			textArea.setText("");

			if (playersParsedListener != null)
			{
				playersParsedListener.playersParsed(
					this,
					new PlayersParsedEvent<HandballAttributes>(this, players));
			}
		}
		catch (ParseException ex)
		{
			System.out.println("Unable to parse input");
		}
	}

	private void parsePlayerEvaluators()
	{
		try
		{
			List<PlayerEvaluator<HandballAttributes>> evaluators =
					new HandballPlayerEvaluatorsParser()
							.parsePlayerEvaluators(textArea.getText());

			textArea.setText("");

			if (playerEvaluatorParsedListener != null)
			{
				playerEvaluatorParsedListener.playerEvaluatorsParsed(
					this,
					new PlayerEvaluatorsParsedEvent<HandballAttributes>(
							this,
							evaluators));
			}
		}
		catch (ParseException e)
		{
			System.out.println("Unable to parse input");
		}
	}
}
