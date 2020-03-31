import java.util.Comparator;
public class Porownaj 
implements Comparator<Podroz>{
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
