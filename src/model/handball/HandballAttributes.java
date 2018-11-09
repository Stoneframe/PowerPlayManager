package model.handball;

import java.util.Arrays;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class HandballAttributes
	extends Attributes
{
	public HandballAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa", "Goalkeeping"),
				new Attribute("Fip", "Field play"),
				new Attribute("Sho", "Shooting"),
				new Attribute("Blk", "Blocking"),
				new Attribute("Pas", "Passing"),
				new Attribute("Tec", "Technique"),
				new Attribute("Spe", "Speed"),
				new Attribute("Agr", "Aggressiveness")));
	}

	public int getGoa()
	{
		return attributes.get(0).getRating();
	}

	public void setGoa(int goa)
	{
		attributes.get(0).setRating(goa);
		firePropertyChanged("Goa", goa);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getFip()
	{
		return attributes.get(1).getRating();
	}

	public void setFip(int fip)
	{
		attributes.get(1).setRating(fip);
		firePropertyChanged("Fip", fip);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSho()
	{
		return attributes.get(2).getRating();
	}

	public void setSho(int sho)
	{
		attributes.get(2).setRating(sho);
		firePropertyChanged("Sho", sho);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getBlk()
	{
		return attributes.get(3).getRating();
	}

	public void setBlk(int blk)
	{
		attributes.get(3).setRating(blk);
		firePropertyChanged("Blk", blk);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getPas()
	{
		return attributes.get(4).getRating();
	}

	public void setPas(int pas)
	{
		attributes.get(4).setRating(pas);
		firePropertyChanged("Pas", pas);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getTec()
	{
		return attributes.get(5).getRating();
	}

	public void setTec(int tec)
	{
		attributes.get(5).setRating(tec);
		firePropertyChanged("Tec", tec);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSpe()
	{
		return attributes.get(6).getRating();
	}

	public void setSpe(int spe)
	{
		attributes.get(6).setRating(spe);
		firePropertyChanged("Spe", spe);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getAgr()
	{
		return attributes.get(7).getRating();
	}

	public void setAgr(int agr)
	{
		attributes.get(7).setRating(agr);
		firePropertyChanged("Agr", agr);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getQGoa()
	{
		return attributes.get(0).getQuality();
	}

	public void setQGoa(int qGoa)
	{
		attributes.get(0).setQuality(qGoa);
		firePropertyChanged("QGoa", qGoa);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQFip()
	{
		return attributes.get(1).getQuality();
	}

	public void setQFip(int qFip)
	{
		attributes.get(1).setQuality(qFip);
		firePropertyChanged("QFip", qFip);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSho()
	{
		return attributes.get(2).getQuality();
	}

	public void setQSho(int qSho)
	{
		attributes.get(2).setQuality(qSho);
		firePropertyChanged("QSho", qSho);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQBlk()
	{
		return attributes.get(3).getQuality();
	}

	public void setQBlk(int qBlk)
	{
		attributes.get(3).setQuality(qBlk);
		firePropertyChanged("QBlk", qBlk);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQPas()
	{
		return attributes.get(4).getQuality();
	}

	public void setQPas(int qPas)
	{
		attributes.get(4).setQuality(qPas);
		firePropertyChanged("QPas", qPas);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQTec()
	{
		return attributes.get(5).getQuality();
	}

	public void setQTec(int qTec)
	{
		attributes.get(5).setQuality(qTec);
		firePropertyChanged("QTec", qTec);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSpe()
	{
		return attributes.get(6).getQuality();
	}

	public void setQSpe(int qSpe)
	{
		attributes.get(6).setQuality(qSpe);
		firePropertyChanged("QSpe", qSpe);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQAgr()
	{
		return attributes.get(7).getQuality();
	}

	public void setQAgr(int qAgr)
	{
		attributes.get(7).setQuality(qAgr);
		firePropertyChanged("QAgr", qAgr);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public HandballAttributes copy()
	{
		HandballAttributes other = new HandballAttributes();

		other.attributes = this.attributes
			.stream()
			.map(a -> a.clone())
			.collect(Collectors.toList());

		return other;
	}
}
