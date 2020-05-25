package AutoApp.Model;

import java.util.Comparator;
import AutoApp.Data.Podroz;

/**
 * Pozwala na posortowanie kolekcji obiektow typu Podroz po r�nych polach (czas trwania, d�ugo�� podr�y ,data rozpocz�cia podr�y, �rednia pr�dko��)
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Porownaj
implements Comparator<Podroz>{
	/**
	 * Pozwala na ustalenie po jakim polu ma by� wykonywane sortowanie.
	 * Rozpoznaje warto�ci "czas", "dlugosc", "data" i adekwatnie do tych bierze pod uwage odpowiednie pole obiektu Podroz,
	 * dla kazdej innej warto�ci sortowanie nastepuje wed�ug �redniej pr�dko��i.
	 */
	private String klucz;

	/**
	 * Konstruktor klasy
	 * @param klucz klucz, wg kt�rego elementy zostan� posortowane (czas,dlugosc,data,sr_predkosc)
	 */
	Porownaj(String klucz)
	{
		this.klucz = klucz;
	}
	@Override
	public int compare(Podroz o1, Podroz o2) {
		// TODO Auto-generated method stub
		if(klucz == "czas")
		{
			if(o1.getCzas()==o2.getCzas())
				return 0;
			else if(o1.getCzas() > o2.getCzas())
				return 1;
			else
				return -1;
		}
		else if(klucz == "dlugosc")
		{
			if(o1.getDlugosc() == o2.getDlugosc())
				return 0;
			else if(o1.getDlugosc() > o2.getDlugosc())
				return 1;
			else
				return -1;
		}
		else if(klucz == "data")
		{
			if(o1.getData_rozpoczecia().compareTo(o2.getData_rozpoczecia())==0)
				return 0;
			else if(o1.getData_rozpoczecia().compareTo(o2.getData_rozpoczecia())>0)
				return 1;
			else
				return -1;
		}
		else
		{
			if(o1.getSrednia_predkosc() == o2.getSrednia_predkosc())
				return 0;
			else if(o1.getSrednia_predkosc() > o2.getSrednia_predkosc())
				return 1;
			else
				return -1;
		}
	}

}
