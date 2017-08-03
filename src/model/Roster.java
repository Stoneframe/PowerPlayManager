package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Roster<A extends Attributes> extends AbstractModelCollection
		implements
		Iterable<Player<A>>
{
	public static <A extends Attributes> Roster<A> intersection(
			Roster<A> r1,
			Roster<A> r2)
	{
		Roster<A> intr = r1.copy();
		intr.players.retainAll(r2.players);
		return intr;
	}

	private List<Player<A>> players = new LinkedList<Player<A>>();

	public Roster()
	{
	}

	private Roster(List<Player<A>> players)
	{
		this.players.addAll(players);
	}

	public void add(Player<A> player)
	{
		for (Player<A> addedPlayer : players)
		{
			if (addedPlayer.equals(player))
			{
				addedPlayer.merge(player);

				return;
			}
		}

		players.add(player);

		fireCollectionChanged(
			CollectionChangedEvent.ADDED,
			players.indexOf(player),
			player);
	}

	public void addAll(List<Player<A>> players)
	{
		for (Player<A> player : players)
		{
			add(player);
		}
	}

	public void remove(Player<A> player)
	{
		int index = players.indexOf(player);

		players.remove(player);

		fireCollectionChanged(CollectionChangedEvent.REMOVED, index, player);
	}

	public Player<A> get(int index)
	{
		return players.get(index);
	}

	public int indexOf(Player<A> player)
	{
		return players.indexOf(player);
	}

	public int size()
	{
		return players.size();
	}

	public Roster<A> copy()
	{
		return new Roster<A>(players);
	}

	public void clear()
	{
		while (!players.isEmpty())
		{
			remove(players.get(0));
		}
	}

	public Stream<Player<A>> stream()
	{
		return players.stream();
	}

	public List<Player<A>> sort(Comparator<Player<A>> comparator)
	{
		List<Player<A>> copy = new LinkedList<Player<A>>(players);

		Collections.sort(copy, comparator);
		Collections.reverse(copy);

		return Collections.unmodifiableList(copy);
	}

	public Player<A>[] toArray()
	{
		return players.toArray(new Player[0]);
	}

	@Override
	public Iterator<Player<A>> iterator()
	{
		return players.iterator();
	}
}
