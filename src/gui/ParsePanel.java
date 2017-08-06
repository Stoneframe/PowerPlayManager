package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import model.PlayerEvaluatorsParser;
import model.PlayersParser;
import parsers.ParseException;
import parsers.players.MarketPlayersParser;
import parsers.players.OverviewPlayersParser;
import parsers.players.PractisePlayersParser;
import parsers.players.PractiseProPlayersParser;

public class ParsePanel extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener playersParsedListener;
	private PlayerEvaluatorsParsedListener playerEvaluatorParsedListener;

	private JTextArea textArea;
	private JButton parsePractiseButton;
	private JButton parseProPractiseButton;
	private JButton parseOverviewButton;
	private JButton parseMarketButton;
	private JButton parseTrainingProgramsButton;

	public ParsePanel()
	{
		textArea = new JTextArea(2, 25);

		parsePractiseButton = new JButton("Practise Parse");
		parsePractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new PractisePlayersParser());
			}
		});

		parseProPractiseButton = new JButton("Practise (Pro) Parse");
		parseProPractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new PractiseProPlayersParser());
			}
		});

		parseOverviewButton = new JButton("Overview Parse");
		parseOverviewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new OverviewPlayersParser());
			}
		});

		parseMarketButton = new JButton("Market Parse");
		parseMarketButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new MarketPlayersParser());
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

	public void setPlayersParseListener(PlayersParsedListener listener)
	{
		playersParsedListener = listener;
	}

	public void setPlayerEvaluatorsParsedListener(
			PlayerEvaluatorsParsedListener listener)
	{
		playerEvaluatorParsedListener = listener;
	}

	private void parsePlayers(PlayersParser<A> playersParser)
	{
		try
		{
			List<Player<?>> players = playersParser
					.parsePlayers(textArea.getText());

			textArea.setText("");

			if (playersParsedListener != null)
			{
				playersParsedListener.playersParsed(
					this,
					new PlayersParsedEvent(this, players));
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
			List<PlayerEvaluator<Attributes>> evaluators = new PlayerEvaluatorsParser()
					.parsePlayerEvaluators(textArea.getText());

			textArea.setText("");

			if (playerEvaluatorParsedListener != null)
			{
				playerEvaluatorParsedListener.playerEvaluatorsParsed(
					this,
					new PlayerEvaluatorsParsedEvent(this, evaluators));
			}
		}
		catch (ParseException e)
		{
			System.out.println("Unable to parse input");
		}
	}
}
