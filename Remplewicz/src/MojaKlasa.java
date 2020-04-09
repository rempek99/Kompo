import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException {
		//metoda clone moze rzucic ww. wyjatkiem
		//////////////////// 		TYDZIEN 06 		////////////////////////////////////////////

		ArrayList<Podroz> przejazdy = new ArrayList<Podroz>();
		przejazdy.add(new Podroz(100, LocalDateTime.of(2020,01,01,22,10,10), LocalDateTime.of(2020,01,01,22,20,10)));
		przejazdy.add(new Podroz(50,LocalDateTime.of(2020,01,04,21,20,10), LocalDateTime.of(2020,01,04,22,20,10)));
		przejazdy.add(new Podroz(42,LocalDateTime.of(2020,02,01,20,20,10), LocalDateTime.of(2020,02,01,22,20,10)));
		przejazdy.add(new Podroz(74, LocalDateTime.of(2019,11,01,22,20,10), LocalDateTime.of(2019,11,01,23,42,10)));
		przejazdy.add(new Podroz(15,LocalDateTime.of(2019,11,01,22,20,10), LocalDateTime.of(2019,11,02,11,42,10)));
		przejazdy.add(new Podroz(81,LocalDateTime.of(2016,02,22,10,20,10), LocalDateTime.of(2016,02,22,14,42,10)));
		przejazdy.add(new Podroz(2, LocalDateTime.of(2019,11,01,12,20,10), LocalDateTime.of(2019,11,01,12,25,10)));
		przejazdy.add(new Podroz(210, LocalDateTime.of(2019,01,01,22,20,10), LocalDateTime.of(2019,1,3,1,42,10)));
		przejazdy.add(new Podroz(30,LocalDateTime.of(2020,02,11,10,20,10), LocalDateTime.of(2020,02,11,13,42,10)));
		przejazdy.add(new Podroz(40,LocalDateTime.of(2019,12,01,21,20,10), LocalDateTime.of(2019,12,01,23,42,10)));
		Licznik licznik1 = new Licznik(false);
		try {
			licznik1.reset(); //Wywołanie wyjątku bibliotecznego
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally {
			System.out.println("Licznik 1: " + licznik1);
		}
		Licznik licznik2 = new Licznik();
		try{
			licznik2.dodaj(-100.3f); //Wywołanie wyjątku UjemnaWartosc
		}
		catch (UjemnaWartosc e)
		{
			System.out.println(e);
		}
		try{
			licznik2.dodaj(30.3f);
		}
		catch (UjemnaWartosc e)
		{
			System.out.println(e);
		}
		try {
			licznik2.reset(); //Wywołanie wyjątku LicznikStaly
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally {
			System.out.println("Licznik 2: " + licznik2);
		}
	}
}
