package model.handball;

import model.Attributes;

public class HandballAttributes
	extends Attributes
{
	int goa;
	int fip;
	int sho;
	int blk;
	int pas;
	int tec;
	int spe;
	int agr;

	int qGoa;
	int qFip;
	int qSho;
	int qBlk;
	int qPas;
	int qTec;
	int qSpe;
	int qAgr;

	public int getGoa()
	{
		return goa;
	}

	public void setGoa(int goa)
	{
		this.goa = goa;
		firePropertyChanged("Goa", goa);
	}

	public int getFip()
	{
		return fip;
	}

	public void setFip(int fip)
	{
		this.fip = fip;
		firePropertyChanged("Fip", fip);
	}

	public int getSho()
	{
		return sho;
	}

	public void setSho(int sho)
	{
		this.sho = sho;
		firePropertyChanged("Sho", sho);
	}

	public int getBlk()
	{
		return blk;
	}

	public void setBlk(int blk)
	{
		this.blk = blk;
		firePropertyChanged("Blk", blk);
	}

	public int getPas()
	{
		return pas;
	}

	public void setPas(int pas)
	{
		this.pas = pas;
		firePropertyChanged("Pas", pas);
	}

	public int getTec()
	{
		return tec;
	}

	public void setTec(int tec)
	{
		this.tec = tec;
		firePropertyChanged("Tec", tec);
	}

	public int getSpe()
	{
		return spe;
	}

	public void setSpe(int spe)
	{
		this.spe = spe;
		firePropertyChanged("Spe", spe);
	}

	public int getAgr()
	{
		return agr;
	}

	public void setAgr(int agr)
	{
		this.agr = agr;
		firePropertyChanged("Agr", agr);
	}

	@Override
	public int getTotalRating()
	{
		return goa + fip + sho + blk + pas + tec + spe + agr;
	}

	public int getQGoa()
	{
		return qGoa;
	}

	public void setQGoa(int qGoa)
	{
		this.qGoa = qGoa;
		firePropertyChanged("QGoa", qGoa);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQFip()
	{
		return qFip;
	}

	public void setQFip(int qFip)
	{
		this.qFip = qFip;
		firePropertyChanged("QFip", qFip);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSho()
	{
		return qSho;
	}

	public void setQSho(int qSho)
	{
		this.qSho = qSho;
		firePropertyChanged("QSho", qSho);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQBlk()
	{
		return qBlk;
	}

	public void setQBlk(int qBlk)
	{
		this.qBlk = qBlk;
		firePropertyChanged("QBlk", qBlk);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQPas()
	{
		return qPas;
	}

	public void setQPas(int qPas)
	{
		this.qPas = qPas;
		firePropertyChanged("QPas", qPas);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQTec()
	{
		return qTec;
	}

	public void setQTec(int qTec)
	{
		this.qTec = qTec;
		firePropertyChanged("QTec", qTec);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSpe()
	{
		return qSpe;
	}

	public void setQSpe(int qSpe)
	{
		this.qSpe = qSpe;
		firePropertyChanged("QSpe", qSpe);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQAgr()
	{
		return qAgr;
	}

	public void setQAgr(int qAgr)
	{
		this.qAgr = qAgr;
		firePropertyChanged("QAgr", qAgr);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	@Override
	public double getAverageQuality()
	{
		return (qGoa + qFip + qSho + qBlk + qPas + qTec + qSpe + qAgr) / 8d;
	}

	@Override
	public void merge(Attributes attributes)
	{
		if (!(attributes instanceof HandballAttributes)) return;

		HandballAttributes other = (HandballAttributes)attributes;

		mergeAttribute(() -> this.getGoa(), () -> other.getGoa(), (v) -> this.setGoa(v));
		mergeAttribute(() -> this.getFip(), () -> other.getFip(), (v) -> this.setFip(v));
		mergeAttribute(() -> this.getSho(), () -> other.getSho(), (v) -> this.setSho(v));
		mergeAttribute(() -> this.getBlk(), () -> other.getBlk(), (v) -> this.setBlk(v));
		mergeAttribute(() -> this.getPas(), () -> other.getPas(), (v) -> this.setPas(v));
		mergeAttribute(() -> this.getTec(), () -> other.getTec(), (v) -> this.setTec(v));
		mergeAttribute(() -> this.getSpe(), () -> other.getSpe(), (v) -> this.setSpe(v));
		mergeAttribute(() -> this.getAgr(), () -> other.getAgr(), (v) -> this.setAgr(v));
		mergeAttribute(() -> this.getQGoa(), () -> other.getQGoa(), (v) -> this.setQGoa(v));
		mergeAttribute(() -> this.getQFip(), () -> other.getQFip(), (v) -> this.setQFip(v));
		mergeAttribute(() -> this.getQSho(), () -> other.getQSho(), (v) -> this.setQSho(v));
		mergeAttribute(() -> this.getQBlk(), () -> other.getQBlk(), (v) -> this.setQBlk(v));
		mergeAttribute(() -> this.getQPas(), () -> other.getQPas(), (v) -> this.setQPas(v));
		mergeAttribute(() -> this.getQTec(), () -> other.getQTec(), (v) -> this.setQTec(v));
		mergeAttribute(() -> this.getQSpe(), () -> other.getQSpe(), (v) -> this.setQSpe(v));
		mergeAttribute(() -> this.getQAgr(), () -> other.getQAgr(), (v) -> this.setQAgr(v));
	}

	@Override
	public String toString()
	{
		return String.format(
			"\tGoa: %d(%d)\tFip: %d(%d)\tSho: %d(%d)\tBlk: %d(%d)\tPas: %d(%d)\tTec: %d(%d)\tSpe: %d(%d)\tAgr: %d(%d)\n",
			goa,
			qGoa,
			fip,
			qFip,
			sho,
			qSho,
			blk,
			qBlk,
			pas,
			qPas,
			tec,
			qTec,
			spe,
			qSpe,
			agr,
			qAgr);
	}

	@Override
	public String toJson()
	{
		return "{"
				+
				"goa:"
				+ goa
				+
				",fip:"
				+ fip
				+
				",sho:"
				+ sho
				+
				",blk:"
				+ blk
				+
				",pas:"
				+ pas
				+
				",tec:"
				+ tec
				+
				",spe:"
				+ spe
				+
				",agr:"
				+ agr
				+
				",qGoa:"
				+ qGoa
				+
				",qFip:"
				+ qFip
				+
				",qSho:"
				+ qSho
				+
				",qBlk:"
				+ qBlk
				+
				",qPas:"
				+ qPas
				+
				",qTec:"
				+ qTec
				+
				",qSpe:"
				+ qSpe
				+
				",qAgr:"
				+ qAgr
				+
				"}";
	}
}
