package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Roster implements Iterable<Player>
{
	public static Roster intersection(Roster r1, Roster r2)
	{
		Roster intr = r1.copy();
		intr.players.retainAll(r2.players);
		return intr;
	}

	private List<Player> players = new LinkedList<Player>();

	public Roster()
	{
	}

	public Roster(List<Player> players)
	{
		this.players.addAll(players);
	}

	public void add(Player player)
	{
		for (Player addedPlayer : players)
		{
			if (addedPlayer.equals(player))
			{
				addedPlayer.merge(player);

				return;
			}
		}

		players.add(player);
	}

	public void addAll(List<Player> players)
	{
		for (Player player : players)
		{
			add(player);
		}
	}

	public void remove(Player player)
	{
		players.remove(player);
	}

	public Player get(int index)
	{
		return players.get(index);
	}

	public int size()
	{
		return players.size();
	}

	public Roster copy()
	{
		return new Roster(players);
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

	public Player[] toArray()
	{
		return players.toArray(new Player[0]);
	}

	@Override
	public Iterator<Player> iterator()
	{
		return players.iterator();
	}
}
