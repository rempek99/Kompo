import java.util.Arrays;


public class MojaKlasa {
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
	//metofa clone moze rzucic ww. wyjatkiem
	//////////////////// 		TYDZIEN 03 		////////////////////////////////////////////
		
		KolekcjaKsiazek regal = new KolekcjaKsiazek(10);
		regal.getKsiazki()[0] = new Ksiazka("Dziady","Mickiewicz Adam",333);
		regal.getKsiazki()[1] = new Ksiazka("Sonety Krymskie","Mickiewicz Adam",150);
		regal.getKsiazki()[2] = new Ksiazka("Chlopi","Reymont Wladyslaw Stanislaw",550);
		regal.getKsiazki()[3] = new Ksiazka("Potop","Sienkiewicz Henryk",620);
		regal.getKsiazki()[4] = new Ksiazka("XDziady","Nickiewicz Adam",112);
		regal.getKsiazki()[5] = new Ksiazka("Zonety Krymskie","Wiewicz Adam",100);
		regal.getKsiazki()[6] = new Ksiazka("Hlopi","Reymon Stanislaw",5780);
		regal.getKsiazki()[7] = new Ksiazka("Potop","Sewicz Henryk",62);
		regal.getKsiazki()[8] = new Ksiazka("Ksiazka 1","Nowak Adam",10);
		regal.getKsiazki()[9] = new Ksiazka("Kronika","Gal Anonim",12);
		System.out.println("---------------------------");
		System.out.println("Tablica przed sortowaniem");
		System.out.println("---------------------------");
		System.out.print(regal);
		//sortowanie z wykorzystaniem interfejsu Comparable
		Arrays.sort(regal.getKsiazki());
		System.out.println("---------------------------");
		System.out.println("Tablica po sortowaniu domyœlnym");
		System.out.println("---------------------------");
		System.out.print(regal);
		System.out.println("---------------------------");
		System.out.println("Tablica po sortowaniu wg stron");
		System.out.println("---------------------------");
		Porownaj comp = new Porownaj("strony");
		//sortowanie z wykorzystaniem interfejsu Comparator
		Arrays.sort(regal.getKsiazki(), comp);
		System.out.print(regal);
		
		Object proba2 = regal.clone();
		//Sprawdzenie nowy obiekt (jego tablica) nie wskazuje na ten sam adres
		System.out.println(((KolekcjaKsiazek) proba2).getKsiazki()!=regal.getKsiazki());
		System.out.println(proba2!=regal);
	}

}
