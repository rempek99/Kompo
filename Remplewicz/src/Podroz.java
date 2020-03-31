public class Podroz
implements Comparable<Podroz>, Cloneable{
	float dlugosc; //km
	float srednia_predkosc; //km/h
	int czas; //sekundy
	
	public float getDlugosc()
	{
		return this.dlugosc;
	}
	public float getSrednia_predkosc()
	{
		return this.srednia_predkosc;
	}
	public int getCzas()
	{
		return this.czas;
	}

	public void setDlugosc(float dlugosc) {
		this.dlugosc = dlugosc;
	}

	public void setSrednia_predkosc(float srednia_predkosc) {
		this.srednia_predkosc = srednia_predkosc;
	}

	public void setCzas(int czas) {
		this.czas = czas;
	}

	public Podroz() {
		// TODO Auto-generated constructor stub
		this.dlugosc = 0;
		this.srednia_predkosc = 0;
		this.czas = 0;
	}
	public Podroz(float dlugosc, int czas)
	{
		this.dlugosc = dlugosc;
		this.czas = czas;
		this.srednia_predkosc = dlugosc / czas * 3600 ;
	}
	
	public String toString()
	{
		String output = "";
		output += "Dystans: "+ getDlugosc() + "km, Srednia Predkosc: " + String.format("%.2f", getSrednia_predkosc()) + "km/h, Czas: " + String.format("%02d",czas/3600) + ":" + String.format("%02d",czas/60%60) + ":" + String.format("%02d",czas%60)+"\n";
		return output;
	}

	public int compareTo(Podroz ks)
	{
		if(this.dlugosc == ks.getDlugosc())
		{
			if(this.srednia_predkosc >  ks.getSrednia_predkosc())
				return 1;
			if(this.srednia_predkosc == ks.getSrednia_predkosc())
				if(this.czas==ks.getCzas())
					return 1;
				else if(this.czas > ks.getCzas())
					return 1;
				else
					return -1;
			else
				return -1;
		}
		else if(this.dlugosc > ks.getDlugosc())
			return 1;
		else
			return -1;
	}
	public Object clone()
	throws CloneNotSupportedException
	{
		Podroz tmp =  (Podroz)super.clone();
		return tmp;
	}
	

}
