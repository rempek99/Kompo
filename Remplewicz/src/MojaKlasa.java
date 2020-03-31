import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException {
		//metoda clone moze rzucic ww. wyjatkiem
		//////////////////// 		TYDZIEN 04 		////////////////////////////////////////////

		//Przyk³ad z wykorzystaniem stosu

		Stack<Podroz> stosik = new Stack<Podroz>();
		System.out.println("Czy stos jest pusty?: " + stosik.empty());
		stosik.push(new Podroz(20,3000));
		stosik.push(new Podroz(20,6420));
		stosik.push(new Podroz(120,5231));
		System.out.println("Czy stos jest pusty?: " + stosik.empty());
		System.out.println(stosik);
		Podroz pobrana = stosik.pop();
		System.out.println(stosik);
		System.out.println("Pobrany przejazd: " + pobrana);
		//Instrukcja pop() pobra³a ze stosu ostatni element (lezacy na wierzcho³ku stosu)

		//// Dalsza czêœæ bazuje na wykorzystaniu Listy
		System.out.println("---------------------------\n---------------------------\n---------------------------\n");
		ArrayList<Podroz> przejazdy = new ArrayList<Podroz>();
		przejazdy.add(new Podroz(100,7200));
		przejazdy.add(new Podroz(50,3100));
		przejazdy.add(new Podroz(42,5210));
		przejazdy.add(new Podroz(74,4182));
		przejazdy.add(new Podroz(15,4127));
		przejazdy.add(new Podroz(81,1002));
		przejazdy.add(new Podroz(2,114));
		przejazdy.add(new Podroz(210,10032));
		przejazdy.add(new Podroz(30,1802));
		przejazdy.add(new Podroz(40,2004));
		System.out.println("---------------------------");
		System.out.println("Tablica przed sortowaniem");
		System.out.println("---------------------------");
		System.out.print(przejazdy);
		//sortowanie z wykorzystaniem interfejsu Comparable
		Collections.sort(przejazdy);
		System.out.println("---------------------------");
		System.out.println("Tablica po sortowaniu domyœlnym");
		System.out.println("---------------------------");
		System.out.print(przejazdy);
		System.out.println("---------------------------");
		System.out.println("Tablica po sortowaniu wg sredniej predkosci");
		System.out.println("---------------------------");
		Porownaj comp = new Porownaj("srednia_predkosc");
		//sortowanie z wykorzystaniem interfejsu Comparator
		Collections.sort(przejazdy, comp);
		System.out.print(przejazdy);
	}
}