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
		//////////////////// 		TYDZIEN 05 		////////////////////////////////////////////

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
		Collections.sort(przejazdy);
		System.out.print(przejazdy);
		//ZAPIS TEKSTU DO PLIKU
		File plik = new File("./plik.txt");
		String tekstDoZapisu = new String("Przykladowy tekst");
		try{
			plik.createNewFile(); //utworzenie nowego pliku
			FileWriter strumienZapisu = new FileWriter(plik);
			strumienZapisu.write(tekstDoZapisu, 0,tekstDoZapisu.length());
			strumienZapisu.close();
		}
		catch(IOException io)
		{
			System.out.println(io.getMessage());
		}
		catch (Exception se)
		{
			System.err.println("blad sec");
		}
		char bufor[] = new char[20];
		try{
			FileReader strumienOdczytu = new FileReader(plik);
			strumienOdczytu.read(bufor,0,20);
			strumienOdczytu.close();
		}
		catch (FileNotFoundException io)
		{
			System.out.println(io.getMessage());
		}
		catch (IOException io)
		{
			System.out.println(io.getMessage());
		}
		String tekstOdczytany = new String(bufor);
		System.out.println("Odczytane z pliku: " + tekstOdczytany);
		int rozmiar = 10;
		int tablica[] = new int[rozmiar];
		int zakres = 100;
		for(int i=0;i<rozmiar;i++)
			tablica[i] = (int)(Math.random()*zakres);
		System.out.println("Wylosowana tablica: ");
		for(int i=0;i<rozmiar;i++)
			System.out.print(tablica[i]+"  ");
		//Zapis tablicy do pliku
		try{
			DataOutputStream strumienTablicy = new DataOutputStream(new FileOutputStream("./tablica.bin"));
			for(int i =0;i<rozmiar;i++)
				strumienTablicy.writeInt(tablica[i]);
			strumienTablicy.close();
		}
		catch(IOException io)
		{
			System.out.println(io.getMessage());
		}
		catch (Exception se)
		{
			System.err.println("blad sec");
		}
		//Odczyt tablicy z pliku
		int tablicaOdczytana[] = new int[rozmiar];
		try{
			DataInputStream strumienTablicaZPliku = new DataInputStream(new FileInputStream("./tablica.bin"));
			for(int i=0;i<rozmiar;i++)
			{
				tablicaOdczytana[i] = strumienTablicaZPliku.readInt();
			}
			strumienTablicaZPliku.close();
		}
		catch(FileNotFoundException io)
		{
			System.out.println(io.getMessage());
		}
		catch(IOException io)
		{
			System.out.println(io.getMessage());
		}
		System.out.println("\nOdczytano tablice:");
		for(int i=0; i< rozmiar; i++)
			System.out.print(tablicaOdczytana[i]+"  ");
		Arrays.sort(tablicaOdczytana);
		System.out.println("\nPosortowana tablica: ");
		for(int i=0;i<10;i++)
			System.out.print(tablicaOdczytana[i]+"  ");
		//Zapis obiektow do pliku
		try {
			FileOutputStream out = new FileOutputStream("./przejazdy.o");
			ObjectOutputStream p = new ObjectOutputStream(out);
			p.writeObject(przejazdy);
			out.close();
		}
		catch(IOException io)
		{
			System.out.println(io.getMessage());
		}
		//Odczyt obiektow do pliku
		ArrayList<Podroz> przejazdyOdczytane = new ArrayList<Podroz>();
		try {
			FileInputStream in = new FileInputStream("./przejazdy.o");
			ObjectInputStream q = new ObjectInputStream(in);
			przejazdyOdczytane = (ArrayList<Podroz>) q.readObject();
			in.close();
		}
		catch(IOException | ClassNotFoundException io)
		{
			System.out.println(io.getMessage());
		}
		System.out.print("\nPrzejazdy odczytane z pliku:\n"+przejazdyOdczytane);
		try {
			XStream mapping=new XStream(new DomDriver());
			String out=mapping.toXML(przejazdy);
			FileOutputStream save=new FileOutputStream("./przejazdy.xml");
			save.write(out.getBytes());
			System.out.println("zapisano w formacie xml: ");
			System.out.println(out);
			save.close();
			DataInputStream in=new DataInputStream(new FileInputStream("./przejazdy.xml"));
			System.out.println("odczytana lista z pliku xml:");
			ArrayList<Podroz> podroze_z_xml=(ArrayList<Podroz>) mapping.fromXML(in);
			System.out.println(podroze_z_xml);
		}
		catch( Exception e)
		{
			System.out.println(e);
		}


	}
}
