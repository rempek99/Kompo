import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;
import java.util.Date;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException {
		//metoda clone moze rzucic ww. wyjatkiem
		//////////////////// 		TYDZIEN 06 		////////////////////////////////////////////
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
		Samochod auto = new Samochod(200,150,150);
		auto.uruchom_silnik();
		auto.gaz();
		auto.gaz();
		auto.gaz();
		/*try {
			Thread.sleep(20000);
		}
		catch (InterruptedException ex)
		{
			System.out.println(ex);
		}*/
		auto.gaz();
		auto.hamulec();
		auto.zgas_silnik();
		System.out.print(auto.przejazdy);

	}
}
