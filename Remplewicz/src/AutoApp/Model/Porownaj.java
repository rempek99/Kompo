package AutoApp.Model;

import java.util.Comparator;
import AutoApp.Data.Podroz;

/**
 * Pozwala na posortowanie kolekcji obiektow typu Podroz po różnych polach (czas trwania, długość podróży ,data rozpoczęcia podróży, średnia prędkość)
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Porownaj
implements Comparator<Podroz>{
	/**
	 * Pozwala na ustalenie po jakim polu ma być wykonywane sortowanie.
	 * Rozpoznaje wartości "czas", "dlugosc", "data" i adekwatnie do tych bierze pod uwage odpowiednie pole obiektu Podroz,
	 * dla kazdej innej wartości sortowanie nastepuje według średniej prędkośći.
	 */
	String klucz;
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
