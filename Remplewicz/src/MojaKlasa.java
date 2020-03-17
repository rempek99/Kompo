import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

public class MojaKlasa {
	
	
	public static void main(String[] args) {
		
	//////////////////// 		TYDZIEN 03 		////////////////////////////////////////////
		
		KolekcjaKsiazek regal = new KolekcjaKsiazek(10);
		regal.dodaj(new Ksiazka("Dziady","Mickiewicz Adam",333));
		regal.dodaj(new Ksiazka("Sonety Krymskie","Mickiewicz Adam",150));
		regal.dodaj(new Ksiazka("Chlopi","Reymont Wladyslaw Stanislaw",550));
		regal.dodaj(new Ksiazka("Potop","Sienkiewicz Henryk",620));
		regal.dodaj(new Ksiazka("XDziady","Nickiewicz Adam",112));
		regal.dodaj(new Ksiazka("Zonety Krymskie","Wiewicz Adam",100));
		regal.dodaj(new Ksiazka("Hlopi","Reymon Stanislaw",5780));
		regal.dodaj(new Ksiazka("Potop","Sewicz Henryk",62));
		regal.dodaj(new Ksiazka("Ksiazka 1","Nowak Adam",10));
		regal.dodaj(new Ksiazka("Kronika","Gal Anonim",12));
		System.out.print(regal);
		regal.sortuj();
		System.out.println("---------------------------");
		System.out.print(regal);
		
			
		
	}

}
