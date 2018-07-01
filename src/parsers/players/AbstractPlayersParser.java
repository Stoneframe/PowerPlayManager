package parsers.players;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import model.Attributes;
import model.Player;
import model.Side;
import parsers.ParseException;
import parsers.SideParser;

public abstract class AbstractPlayersParser<A extends Attributes>
	extends PlayersParser<A>
{
	private static String[] countries =
	{
			"Afghanistan",
			"Albanien",
			"Algeriet",
			"Amerikanska Samoa",
			"Andorra",
			"Angola",
			"Anguilla",
			"Antigua & Barbuda",
			"Argentina",
			"Armenien",
			"Aruba",
			"Australien",
			"Azerbajdzjan",
			"Bahamas",
			"Bahrain",
			"Bangladesh",
			"Barbados",
			"Belgien",
			"Belize",
			"Benin",
			"Bermuda",
			"Bhutan",
			"Bolivia",
			"Bosnien och Herzegovina",
			"Botswana",
			"Brasilien",
			"Brunei",
			"Bulgarien",
			"Burkina Faso",
			"Burma",
			"Burundi",
			"Cayman�arna",
			"Centralafrikanska republiken",
			"Colombia",
			"Cook�arna",
			"Costa Rica",
			"Cypern",
			"Danmark",
			"Demokratiska republiken Kongo",
			"Djibouti",
			"Dominica",
			"Dominikanska Republiken",
			"Ecuador",
			"Egypten",
			"Ekvatorialguinea",
			"Elfenbenskusten",
			"El Salvador",
			"Eritrea",
			"Estland",
			"Etiopien",
			"Falklands�arna",
			"F�r�arna",
			"Fiji",
			"Fillipinerna",
			"Finland",
			"F�renade Arabemirater",
			"Frankrike",
			"Franska Guyana",
			"Franska Polynesien",
			"Gabon",
			"Gambia",
			"Georgien",
			"Ghana",
			"Gibraltar",
			"Grekland",
			"Grenada",
			"Gr�nland",
			"Guadeloupe",
			"Guam",
			"Guatemala",
			"Guernsey",
			"Guinea",
			"Guinea-Bissau",
			"Guyana",
			"Haiti",
			"Honduras",
			"Chile",
			"Indien",
			"Indonesien",
			"Irak",
			"Iran",
			"Irland",
			"Island",
			"Isle of Man",
			"Israel",
			"Italien",
			"Jamaica",
			"Japan",
			"Jemen",
			"Jersey",
			"Jordanien",
			"Kambodja",
			"Kamerun",
			"Kanada",
			"Kap Verde",
			"Kazakhstan",
			"Kenya",
			"Kina",
			"Kiribati",
			"Kokos�arna",
			"Komorerna",
			"Kroatien",
			"Kuba",
			"Kuwait",
			"Kyrgyzstan",
			"Laos",
			"Lesotho",
			"Lettland",
			"Libanon",
			"Liberia",
			"Libyen",
			"Liechtenstein",
			"Litauen",
			"Luxemburg",
			"Madagaskar",
			"Makedonien",
			"Malawi",
			"Malaysia",
			"Maldiverna",
			"Mali",
			"Malta",
			"Marocko",
			"Marshall�arna",
			"Martinique",
			"Mauretanien",
			"Mauritius",
			"Mexico",
			"Mikronesien",
			"Mo�ambique",
			"Moldavien",
			"Monaco",
			"Mongoliet",
			"Montenegro",
			"Montserrat",
			"Namibia",
			"Nauru",
			"Nederl�nderna",
			"Nederl�ndska Antillerna",
			"Nepal",
			"Nicaragua",
			"Niger",
			"Nigeria",
			"Nordkorea",
			"Nordmarianerna",
			"Norge",
			"Nya Kaledonien",
			"Nya Zeland",
			"Oman",
			"�sterrike",
			"�sttimor",
			"Pakistan",
			"Palau",
			"Panama",
			"Papua Nya Guinea",
			"Paraguay",
			"Peru",
			"Pitcairn�arna",
			"Polen",
			"Portugal",
			"Puerto Rico",
			"Qatar",
			"Republiken Kongo",
			"Resten av v�rlden",
			"R�union",
			"Rum�nien",
			"Rwanda",
			"Ryssland",
			"Saint Kitts och Nevis",
			"Saint Lucia",
			"Saint-Pierre och Miquelon",
			"Saint Vincent och Grenadinerna",
			"Salomon�arna",
			"Samoa",
			"Sankta Helena",
			"San Marino",
			"S�o Tom� och Pr�ncipe",
			"Saudiarabien",
			"Senegal",
			"Serbien",
			"Seychellerna",
			"Schweiz",
			"Sierra Leone",
			"Singapor",
			"Slovakien",
			"Slovenien",
			"Somalia",
			"Spanien",
			"Sri Lanka",
			"Storbritannien",
			"Sudan",
			"Surinam",
			"Sverige",
			"Swaziland",
			"Sydafrika",
			"Sydkorea",
			"Syrien",
			"Taiwan",
			"Tajikistan",
			"Tanzania",
			"Thailand",
			"Tchad",
			"Tjeckien",
			"Togo",
			"Tonga",
			"Trinidad och Tobago",
			"Tunisien",
			"Turkiet",
			"Turkmenistan",
			"Tuvalu",
			"Tyskland",
			"Uganda",
			"Ukraina",
			"Ungern",
			"Uruguay",
			"USA",
			"Uzbekistan",
			"Vanuatu",
			"V�stsahara",
			"Vatikanstaten",
			"Venezuela",
			"Vietnam",
			"Vitryssland",
			"Zambia",
			"Zimbabwe"
	};

	private final Pattern regexPattern;

	private final boolean includeCL;
	private final boolean includeSide;
	private final boolean includeQualities;
	private final boolean includeExperience;
	private final boolean includeTraining;

	protected AbstractPlayersParser(
			Pattern regexPattern,
			boolean includeCL,
			boolean includeSide,
			boolean includeQualities,
			boolean includeExperience,
			boolean includeTraining)
	{
		this.regexPattern = regexPattern;

		this.includeCL = includeCL;
		this.includeSide = includeSide;
		this.includeQualities = includeQualities;
		this.includeExperience = includeExperience;
		this.includeTraining = includeTraining;
	}

	@Override
	public abstract String getName();

	@Override
	public List<Player<A>> parsePlayers(String textToParse)
			throws ParseException
	{
		List<Player<A>> players = new LinkedList<>();

		for (String line : toSinglePlayerPerLine(textToParse))
		{
			Matcher matcher = regexPattern.matcher(line);

			if (matcher.find())
			{
				Player<A> player = createPlayer(matcher);

				players.add(player);
			}
			else
			{
				throw new ParseException();
			}
		}

		return players;
	}

	protected abstract List<String> toSinglePlayerPerLine(String textToParse);

	protected Player<A> createPlayer(Matcher matcher)
	{
		A attributes = createAttributes(matcher, includeQualities);

		return new Player<A>(
				matcher.group("name"),
				Integer.parseInt(matcher.group("age")),
				includeCL ? Integer.parseInt(matcher.group("cl")) : 0,
				includeSide ? SideParser.parseSide(matcher.group("side")) : Side.UNKNOWN,
				attributes,
				includeExperience ? Integer.parseInt(matcher.group("experience")) : 0,
				0,
				0,
				includeTraining ? Double.parseDouble(matcher.group("training")) : 0);
	}

	protected abstract A createAttributes(Matcher matcher, boolean includeQuality);

	protected static Pattern createPattern(String... regexes)
	{
		return Pattern.compile("^" + String.join("\t", regexes));
	}

	protected static String ignore()
	{
		return "[^\\t]*";
	}

	protected static String name()
	{
		return "(?<country>("
				+ String.join("|", countries).replace("-", "\\-")
				+ ")+) (?<name>\\S+(((?! Dagar)( \\S+))+)).*";
	}

	protected static String age()
	{
		return "[ ]*(?<age>\\d+)[ ]*";
	}

	protected static String cl()
	{
		return "[ ]*(?<cl>\\d)/6[ ]*";
	}

	protected static String attributes(String... names)
	{
		return String.join(
			"\t",
			Arrays.stream(names).map(n -> attribute(n)).collect(Collectors.toList()));
	}

	protected static String attribute(String name)
	{
		return String.format("[ ]*((?<%s>\\d+))[ ]*", name, name);
	}

	protected static String attributesWithQualities(String... names)
	{
		return String.join(
			"\t",
			Arrays.stream(names).map(n -> attributeWithQuality(n)).collect(Collectors.toList()));
	}

	protected static String attributeWithQuality(String name)
	{
		return String.format("[ ]*((?<%s>\\d+)(?<q%s>(\\d{2})))[ ]*", name, name);
	}

	protected static String experience()
	{
		return "[ ]*(?<experience>\\d+)[ ]*";
	}

	protected static String side()
	{
		return "[ ]*(?<side>(U|L|R))[ ]*";
	}

	protected static String training()
	{
		return "(?<training>\\d+\\.\\d{2})";
	}
}
