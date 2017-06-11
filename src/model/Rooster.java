package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import model.comparators.RatingComparator;

public class Rooster implements Iterable<Player>
{
	private List<Player> players = new LinkedList<Player>();

	public Rooster()
	{
	}

	private Rooster(List<Player> players)
	{
		players.addAll(players);
	}

	public void add(Player player)
	{
		players.add(player);
	}

	public void remove(Player player)
	{
		players.remove(player);
	}

	public Rooster copy()
	{
		return new Rooster(players);
	}

	public Stream<Player> stream()
	{
		return players.stream();
	}

	public List<Player> sort(Comparator<Player> comparator)
	{
		List<Player> copy = new LinkedList<Player>(players);

		Collections.sort(copy, comparator);
		Collections.reverse(copy);

		return Collections.unmodifiableList(copy);
	}

	public Player select(
			PlayerEvaluator evaluator,
			Side side)
	{
		Player player = players
				.stream()
				.filter(p -> p.getSide().equals(side))
				.max(new RatingComparator(evaluator))
				.get();

		players.remove(player);

		return player;
	}

	@Override
	public Iterator<Player> iterator()
	{
		return players.iterator();
	}
}
