import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Podroz
implements Comparable<Podroz>, Cloneable, Serializable {
	float dlugosc; //km
	float srednia_predkosc; //km/h
	long czas; //sekundy
	LocalDateTime data_rozpoczecia, data_zakonczenia;

	public LocalDateTime getData_rozpoczecia() {
		return data_rozpoczecia;
	}

	public float getDlugosc()
	{
		return this.dlugosc;
	}
	public float getSrednia_predkosc()
	{
		return this.srednia_predkosc;
	}
	public long getCzas()import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Podroz
implements Comparable<Podroz>, Cloneable, Serializable {
	float dlugosc; //km
	float srednia_predkosc; //km/h
	long czas; //sekundy
	String data_rozpoczecia;
	String data_zakonczenia;

	public String getData_rozpoczecia() {
		return data_rozpoczecia;
	}

	public float getDlugosc()
	{
		return this.dlugosc;
	}
	public float getSrednia_predkosc()
	{
		return this.srednia_predkosc;
	}
	public long getCzas()
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


	public Podroz(float dlugosc, LocalDateTime data_rozpoczecia, LocalDateTime data_zakonczenia)
	{
		this.dlugosc = dlugosc;
		Duration duration = Duration.between(data_rozpoczecia,data_zakonczenia);
		this.czas = Math.abs(duration.toSeconds());
		this.srednia_predkosc = dlugosc / czas * 3600 ;
		this.data_rozpoczecia = data_rozpoczecia.toString();
		this.data_zakonczenia = data_zakonczenia.toString();
	}
	
	public String toString()
	{
		String output = "";
		output +=data_rozpoczecia +" "+ data_zakonczenia + " Dystans: "+ getDlugosc() + "km, Srednia Predkosc: " + String.format("%.2f", getSrednia_predkosc()) + "km/h, Czas: " + String.format("%02d",czas/3600) + ":" + String.format("%02d",czas/60%60) + ":" + String.format("%02d",czas%60)+"\n";
		return output;
	}

	public int compareTo(Podroz ks)
	{
		if(this.data_rozpoczecia.compareTo(ks.getData_rozpoczecia())==0) {
			if(this.dlugosc > ks.getDlugosc())
				return 1;
			else if(this.dlugosc < ks.getDlugosc())
				return -1;
			else {
				if (this.srednia_predkosc > ks.getSrednia_predkosc())
					return 1;
				if (this.srednia_predkosc == ks.getSrednia_predkosc())
					if (this.czas == ks.getCzas())
						return 1;
					else if (this.czas > ks.getCzas())
						return 1;
					else
						return -1;
				else
					return -1;
			}
		}
		else if(this.data_rozpoczecia.compareTo(ks.getData_rozpoczecia())>0)
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
//	public String podroz2Xml(Podroz podroz)
//	{
//		XStream mapping=new XStream(new DomDriver());
//		String xml=mapping.toXML(podroz);
//		return xml;
//	}
//	public String podroz2Xml()
//	{
//		XStream mapping=new XStream(new DomDriver());
//		String xml=mapping.toXML((Podroz)this);
//		return xml;
//	}
//	public Podroz xml2Podroz(String xml) {
//		XStream mapping = new XStream(new DomDriver());
//		return (Podroz) mapping.fromXML(xml);
//	}
}