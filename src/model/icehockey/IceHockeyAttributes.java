package model.icehockey;

import java.util.Arrays;
import java.util.stream.Collectors;

import model.Attribute;
import model.Attributes;

public class IceHockeyAttributes
	extends Attributes
{
	public IceHockeyAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa"),
				new Attribute("Def"),
				new Attribute("Off"),
				new Attribute("Sho"),
				new Attribute("Pas"),
				new Attribute("Tec"),
				new Attribute("Agr")));
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

	public int getDef()
	{
		return attributes.get(1).getRating();
	}

	public void setDef(int def)
	{
		attributes.get(1).setRating(def);
		firePropertyChanged("Def", def);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getOff()
	{
		return attributes.get(2).getRating();
	}

	public void setOff(int off)
	{
		attributes.get(2).setRating(off);
		firePropertyChanged("Off", off);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSho()
	{
		return attributes.get(3).getRating();
	}

	public void setSho(int sho)
	{
		attributes.get(3).setRating(sho);
		firePropertyChanged("Sho", sho);
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

	public int getAgr()
	{
		return attributes.get(6).getRating();
	}

	public void setAgr(int agr)
	{
		attributes.get(6).setRating(agr);
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

	public int getQDef()
	{
		return attributes.get(1).getQuality();
	}

	public void setQDef(int qDef)
	{
		attributes.get(1).setQuality(qDef);
		firePropertyChanged("QDef", qDef);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQOff()
	{
		return attributes.get(2).getQuality();
	}

	public void setQOff(int qOff)
	{
		attributes.get(2).setQuality(qOff);
		firePropertyChanged("QOff", qOff);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSho()
	{
		return attributes.get(3).getQuality();
	}

	public void setQSho(int qSho)
	{
		attributes.get(3).setQuality(qSho);
		firePropertyChanged("QSho", qSho);
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

	public int getQAgr()
	{
		return attributes.get(6).getQuality();
	}

	public void setQAgr(int qAgr)
	{
		attributes.get(6).setQuality(qAgr);
		firePropertyChanged("QAgr", qAgr);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public IceHockeyAttributes copy()
	{
		IceHockeyAttributes other = new IceHockeyAttributes();
		
		other.attributes = this.attributes
				.stream()
				.map(a -> a.clone())
				.collect(Collectors.toList());
		
		return other;
	}
}
