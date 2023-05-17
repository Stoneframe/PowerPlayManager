package importer;

import java.util.List;

import org.jsoup.nodes.Document;

import model.Attributes;
import model.Player;

public interface Parser<A extends Attributes>
{
	String getAddress();

	List<Player<A>> parse(Document document);
}