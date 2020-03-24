
public class KolekcjaKsiazek
implements Cloneable{
	int rozmiar;
	Ksiazka ksiazki [];
	
	public int getRozmiar() {
		return rozmiar;
	}
	public Ksiazka[] getKsiazki() {
		return ksiazki;
	}
	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}
	public void setKsiazki(Ksiazka[] ksiazki) {
		this.ksiazki = ksiazki;
	}
	
	
	public KolekcjaKsiazek() 
	{
		this.rozmiar = 10;
		ksiazki = new Ksiazka[rozmiar];
	}
	public KolekcjaKsiazek(int rozmiar)
	{
		this.rozmiar = rozmiar;
		ksiazki = new Ksiazka[rozmiar];
	}
	public String toString()
	{
		String output = "";
		for(int i = 0; i < rozmiar; i++)
		{
			output += this.ksiazki[i].toString();
		}
		return output;
	}
	public Object clone() throws CloneNotSupportedException
	{
		KolekcjaKsiazek klon = (KolekcjaKsiazek)super.clone();
		Ksiazka tmp[] = klon.getKsiazki();
		klon.ksiazki = new Ksiazka[klon.getRozmiar()];
		for(int i = 0; i < klon.getRozmiar(); i++)
		{
			klon.getKsiazki()[i] = (Ksiazka) tmp[i].clone();
		}
		
		return klon;
	}
}
