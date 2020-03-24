import java.util.Comparator;
public class Porownaj 
implements Comparator<Ksiazka>{
	String klucz;
	Porownaj(String klucz)
	{
		this.klucz = klucz;
	}
	@Override
	public int compare(Ksiazka o1, Ksiazka o2) {
		// TODO Auto-generated method stub
		if(klucz == "strony")
		{
			if(o1.getStrony()==o2.getStrony())
				return 0;
			else if(o1.getStrony() > o2.getStrony())
				return 1;
			else
				return -1;
		}
		else if(klucz == "autor")
		{
			if(o1.getAutor().compareTo(o2.getAutor())==0)
				return 0;
			else if(o1.getAutor().compareTo(o2.getAutor())>0)
				return 1;
			else
				return -1;
		}
		else
		{
			if(o1.getNazwa().compareTo(o2.getNazwa())==0)
				return 0;
			else if(o1.getNazwa().compareTo(o2.getNazwa())>0)
				return 1;
			else
				return -1;
		}
	}

}
