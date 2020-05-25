package AutoApp.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa przechowuj¹ca informacje o pojedynczej odbytej podró¿y
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Podroz
		implements Comparable<Podroz>, Cloneable, Serializable {
	/**
	 * unikalny identyfikator
	 */
	private int id;
	/**
	 * d³ugoœæ odbytej podró¿y wyra¿ona w kilometrach
	 */
	private double dlugosc;
	/**
	 * œrednia prêdkoœæ podczas podró¿y wyra¿ona w kilometrach na godzinê
	 */
	private double srednia_predkosc;
	/**
	 * czas trwania podró¿y wyra¿ona w sekundach
	 */
	private long czas;
	/**
	 * data rozpoczêcia podró¿y
	 */
	private String data_rozpoczecia;
	/**
	 * obiekt definiuj¹cy format reprezentuj¹cy datê
	 */
	private transient DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * data zakoñczenia podró¿y
	 */
	private String data_zakonczenia;
	/**
	 * obiekt definiuj¹cy format reprezentuj¹cy liczbê rzeczywist¹ z precyzj¹ do dwóch miejsc dziesiêtnych
	 */
	private static DecimalFormat df = new DecimalFormat("0.00");
	public void setCzas(long czas) {
		this.czas = czas;
	}
	public void setData_rozpoczecia(String data_rozpoczecia) {
		this.data_rozpoczecia = data_rozpoczecia;
	}
	public void setData_zakonczenia(String data_zakonczenia) {
		this.data_zakonczenia = data_zakonczenia;
	}
	public String getData_zakonczenia() {
		return data_zakonczenia;
	}
	public String getData_rozpoczecia() {
		return data_rozpoczecia;
	}
	public double getDlugosc()
	{
		return this.dlugosc;
	}
	public double getSrednia_predkosc()
	{
		return this.srednia_predkosc;
	}
	public long getCzas()
	{
		return this.czas;
	}
	public void setDlugosc(double dlugosc) {
		this.dlugosc = dlugosc;
	}
	public void setSrednia_predkosc(double srednia_predkosc) {
		this.srednia_predkosc = srednia_predkosc;
	}
	public void setCzas(int czas) {
		this.czas = czas;
	}
	public int getId() {
		return id;
	}

	/**
	 * Konstruktor domyœlny, ustawiaj¹cy d³ugoœæ, czas oraz œredni¹ prêdkoœæ podró¿y na wartoœci 0
	 */
	public Podroz() {
		// TODO Auto-generated constructor stub
		this.dlugosc = 0;
		this.srednia_predkosc = 0;
		this.czas = 0;
	}

	/**
	 * Konstruktor klasy
	 * @param id unikalny identyfikator
	 * @param dlugosc d³ugoœæ odbytej podró¿y wyra¿ona w kilometrach
	 * @param data_rozpoczecia data rozpoczêcia podró¿y
	 * @param data_zakonczenia data zakoñczenia podró¿y
	 */
	public Podroz(int id, double dlugosc, Date data_rozpoczecia, Date data_zakonczenia)
	{
		this.id = id;
		this.dlugosc = dlugosc;
		this.czas = (data_zakonczenia.getTime()-data_rozpoczecia.getTime())/1000;
		if(czas==0)
			this.srednia_predkosc = 0;
		else
			this.srednia_predkosc = dlugosc / czas * 3600 ;
		this.data_rozpoczecia = dateFormat.format(data_rozpoczecia);
		this.data_zakonczenia = dateFormat.format(data_zakonczenia);
	}

	/**
	 *
	 * @param id unikalny identyfikator
	 * @param dlugosc d³ugoœæ odbytej podró¿y wyra¿ona w kilometrach
	 * @param data_rozpoczecia data rozpoczêcia podró¿y
	 * @param data_zakonczenia data zakoñczenia podró¿y
	 * @param srednia_predkosc œrednia prêdkoœæ podczas podró¿y wyra¿ona w kilometrach na godzinê
	 */
	public Podroz(int id,double dlugosc, Date data_rozpoczecia, Date data_zakonczenia, double srednia_predkosc)
	{
		this.id = id;
		this.dlugosc = dlugosc;
		this.czas = (data_zakonczenia.getTime()-data_rozpoczecia.getTime())/1000;
		this.srednia_predkosc = srednia_predkosc ;
		this.data_rozpoczecia = dateFormat.format(data_rozpoczecia);
		this.data_zakonczenia = dateFormat.format(data_zakonczenia);
	}

	public String toString()
	{
		String output = "";
		output +="START: "+data_rozpoczecia +" STOP: "+ data_zakonczenia + " Dystans: "+ df.format(getDlugosc()) + "km, Srednia Predkosc: " + String.format("%.2f", getSrednia_predkosc()) + "km/h, Czas: " + String.format("%02d",czas/3600) + ":" + String.format("%02d",czas/60%60) + ":" + String.format("%02d",czas%60)+"\n";
		return output;
	}

	/**
	 * Wartoœæ pola w postaci String
	 * @param i numer pocz¹dkowy pola: 1) data rozpoczêcia<br>2) data zakoñczenia<br>3) dystans<br>4) œrednia prêdkoœæ<br>5) czas trwania
	 * @return Zwraca ci¹g znaków reprezentuj¹cy konkretn¹ wartoœæ pola obiektu
	 */
	public String stringData(int i)
	{
		String [] output = {data_rozpoczecia,data_zakonczenia, df.format(getDlugosc())+"km",String.format("%.2f", getSrednia_predkosc())+ "km/h",String.format("%02d",czas/3600) + ":" + String.format("%02d",czas/60%60) + ":" + String.format("%02d",czas%60) };

		return output[i];
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
}