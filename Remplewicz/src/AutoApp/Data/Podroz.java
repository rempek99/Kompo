package AutoApp.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa przechowywuj�ca dane dotycz�ce pojedynczej odbytej podr�y
 */
public class Podroz
		implements Comparable<Podroz>, Cloneable, Serializable {
	int id;
	double dlugosc; //km
	double srednia_predkosc; //km/h
	long czas; //sekundy
	String data_rozpoczecia;
	transient DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

	String data_zakonczenia;
	private static DecimalFormat df = new DecimalFormat("0.00");

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

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	/**
	 * Konstruktor domy�lny klasy Podroz
	 */
	public Podroz() {
		// TODO Auto-generated constructor stub
		this.dlugosc = 0;
		this.srednia_predkosc = 0;
		this.czas = 0;
	}

	/**
	 * Konstruktor klasy Podroz (�rednia pr�dko�� liczona jest automatycznie)
	 * @param id identyfikator rekordu
	 * @param dlugosc dlugo�� podr�y wyra�ona w kilometrach
	 * @param data_rozpoczecia data i czas rozpocz�cia podr�y
	 * @param data_zakonczenia data i czas zako�czenia podr�y
	 */
	public Podroz(int id, double dlugosc, Date data_rozpoczecia, Date data_zakonczenia)
	{
		this.id = id;
		this.dlugosc = dlugosc;
		this.czas = (data_zakonczenia.getTime()-data_rozpoczecia.getTime())/1000;
		this.srednia_predkosc = dlugosc / czas * 3600 ;
		this.data_rozpoczecia = dateFormat.format(data_rozpoczecia);
		this.data_zakonczenia = dateFormat.format(data_zakonczenia);
	}

	/**
	 * Konstruktor klasy Podroz
	 * @param id identyfikator rekordu
	 * @param dlugosc dlugo�� podr�y wyra�ona w kilometrach
	 * @param data_rozpoczecia data i czas rozpocz�cia podr�y
	 * @param data_zakonczenia data i czas zako�czenia podr�y
	 * @param srednia_predkosc �rednia pr�dko��, z jak� porusza� si� pomys�
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
	 * Warto�� poszczeg�lnych warto�ci w formie String
	 * @param i numer porz�dkowy odpowiadaj�cy warto�ci: <br>0-data_rozpoczecia, <br>1-data_zakonczenia, <br>2-dlugosc, <br>3-srednia_predkosc, <br>4-czas
	 * @return warto�� elementu zapisanego na i pozycji
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