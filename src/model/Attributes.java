package model;

public class Attributes
{
	private int goa;
	private int fip;
	private int sho;
	private int blk;
	private int pas;
	private int tec;
	private int spe;
	private int agr;

	private int qGoa;
	private int qFip;
	private int qSho;
	private int qBlk;
	private int qPas;
	private int qTec;
	private int qSpe;
	private int qAgr;

	public int getGoa()
	{
		return goa;
	}

	public void setGoa(int goa)
	{
		this.goa = goa;
	}

	public int getFip()
	{
		return fip;
	}

	public void setFip(int fip)
	{
		this.fip = fip;
	}

	public int getSho()
	{
		return sho;
	}

	public void setSho(int sho)
	{
		this.sho = sho;
	}

	public int getBlk()
	{
		return blk;
	}

	public void setBlk(int blk)
	{
		this.blk = blk;
	}

	public int getPas()
	{
		return pas;
	}

	public void setPas(int pas)
	{
		this.pas = pas;
	}

	public int getTec()
	{
		return tec;
	}

	public void setTec(int tec)
	{
		this.tec = tec;
	}

	public int getSpe()
	{
		return spe;
	}

	public void setSpe(int spe)
	{
		this.spe = spe;
	}

	public int getAgr()
	{
		return agr;
	}

	public void setAgr(int agr)
	{
		this.agr = agr;
	}

	public int getTotal()
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
	}

	public int getQFip()
	{
		return qFip;
	}

	public void setQFip(int qFip)
	{
		this.qFip = qFip;
	}

	public int getQSho()
	{
		return qSho;
	}

	public void setQSho(int qSho)
	{
		this.qSho = qSho;
	}

	public int getQBlk()
	{
		return qBlk;
	}

	public void setQBlk(int qBlk)
	{
		this.qBlk = qBlk;
	}

	public int getQPas()
	{
		return qPas;
	}

	public void setQPas(int qPas)
	{
		this.qPas = qPas;
	}

	public int getQTec()
	{
		return qTec;
	}

	public void setQTec(int qTec)
	{
		this.qTec = qTec;
	}

	public int getQSpe()
	{
		return qSpe;
	}

	public void setQSpe(int qSpe)
	{
		this.qSpe = qSpe;
	}

	public int getQAgr()
	{
		return qAgr;
	}

	public void setQAgr(int qAgr)
	{
		this.qAgr = qAgr;
	}

	@Override
	public String toString()
	{
		return String
				.format(
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
}
