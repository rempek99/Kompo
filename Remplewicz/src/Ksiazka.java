import java.util.Comparator;

public class Ksiazka 
implements Comparable<Ksiazka>{
	int strony;
	String nazwa;
	String autor;
	
	public int getStrony()
	{
		return this.strony;
	}
	public String getNazwa()
	{
		return this.nazwa;
	}
	public String getAutor()
	{
		return this.autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public void setStrony(int strony) {
		this.strony = strony;
	}
	
	public Ksiazka() {
		// TODO Auto-generated constructor stub
		this.strony = 0;
		this.autor = "Nieznany";
		this.nazwa = "Bez tytu³u";
	}
	public Ksiazka(String nazwa, String autor,int strony)
	{
		this.strony = strony;
		this.autor = autor;
		this.nazwa = nazwa;
	}
	
	public String toString()
	{
		String output = "";
		output += "\""+ getNazwa() + "\"" + ", " + getAutor() + ", (" + getStrony() + ")\n";
		return output;
	}

	public int compareTo(Ksiazka ks)
	{
		if(this.nazwa == ks.getNazwa())
		{
			if(this.autor.compareTo(ks.getAutor())>0)
				return 1;
			if(this.autor == ks.getAutor())
				if(this.strony==ks.getStrony())
					return 1;
				else if(this.strony > ks.getStrony())
					return 1;
				else
					return -1;
			else
				return -1;
		}
		else if(this.nazwa.compareTo(ks.getNazwa())>0)
			return 1;
		else
			return -1;
	}

}
