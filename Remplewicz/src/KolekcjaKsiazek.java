import java.util.Arrays;
import java.util.Comparator;

public class KolekcjaKsiazek{
	int rozmiar;
	Ksiazka ksiazki [];
	int miejsce;
	
	public int getMiejsce() {
		return miejsce;
	}
	public int getRozmiar() {
		return rozmiar;
	}
	public Ksiazka[] getKsiazki() {
		return ksiazki;
	}
	public void setMiejsce(int miejsce) {
		this.miejsce = miejsce;
	}
	public void setRozmiar(int rozmiar) {
		this.rozmiar = rozmiar;
	}
	public void setKsiazki(Ksiazka[] ksiazki) {
		this.ksiazki = ksiazki;
	}
	
	
	public KolekcjaKsiazek() 
	{
		miejsce = 0;
		this.rozmiar = 10;
		ksiazki = new Ksiazka[rozmiar];
	}
	public KolekcjaKsiazek(int rozmiar)
	{
		miejsce = 0;
		this.rozmiar = rozmiar;
		ksiazki = new Ksiazka[rozmiar];
	}
	public void dodaj(Ksiazka ksiazka)
	{
		if(miejsce == rozmiar)
		{
			System.out.println("Brak miejsca w kolekcji");
			return;
		}
		this.ksiazki[miejsce] = ksiazka;
		miejsce ++;
	}
	public String toString()
	{
		String output = "";
		for(int i = 0; i < miejsce; i++)
		{
			output += this.ksiazki[i].toString();
		}
		return output;
	}
	int compare()
	{
		
		return 0;
	}
	public void sortuj()
	{
		Arrays.sort(ksiazki);
	}
}
