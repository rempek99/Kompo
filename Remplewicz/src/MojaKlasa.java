import javax.swing.*;
import java.util.Arrays;
import java.util.Date; 

public class MojaKlasa {
	
	static int [] babelkowe(int tab[], int rozmiar)
	{
		int posortowana[] = new int[rozmiar];
		for (int i = 0; i < rozmiar; i++)
			posortowana[i] = tab[i];
		int tmp = 0;
		for (int i = 0; i < rozmiar; i++)
		{
			for (int j = 1; j < rozmiar - i; j++)
			{
				if(posortowana[j-1]>posortowana[j])
				{
					tmp = posortowana[j];
					posortowana[j] = posortowana[j-1];
					posortowana[j-1] = tmp;
				}
			}
		}
		return posortowana;
	}

	static long strong(int n)
	{
		if(n==0 || n==1)
			return 1;
		else
			return n*strong(n-1);
	}
	
	static long newton(int n, int k)
	{
		return strong(n)/(strong(k)*strong(n-k));
	}
	
	static long pascal(int n, int k)
	{
		if(k==0)
		{
			return 1;
		}
		return newton(n,k);
	}
	
	public static void main(String[] args) {
////////// TYDZIEN 1. ////////////////////////////////////////////////	
//		System.out.println("Hello world!!!");
//		System.out.println("Inny napis");
//		
//		double a=4;
//		double b=5;
//		System.out.println(a+b);
//		
//		if(b!=0)
//			System.out.println(a/b);
//		else
//			System.out.println("Nie dzielimy przez zero!");
//	
//		System.out.println(25-7);
//		System.out.println(2*6);
//		
//		String txt1, txt2;
//		txt1 = JOptionPane.showInputDialog("Wprowadz pierwsza liczbe");
//		txt2 = JOptionPane.showInputDialog("Wprowadz druga liczbe");
//	
//		System.out.println("Przed konwersja");
//		System.out.println(txt1 + txt2);
//		double liczba1 = Double.parseDouble(txt1);
//		int liczba2 = Integer.parseInt(txt2);
//		System.out.println("Po konwersji1");
//		System.out.println(liczba1 + liczba2);
//		String wiersze;
//		wiersze = JOptionPane.showInputDialog("Podaj liczbe wierszy");
//		String output = "";
//		int n = Integer.parseInt(wiersze);
//		for(int i=0;i<n;i++)
//		{
//			for(int j=0;j<n-i;j++)
//			{
//				output += "    ";
//			}
//			for(int j=0;j<i+1;j++)
//			{
//				output += pascal(i,j);
//				output += "       ";
//			}
//			output += "\n";
//		}
//		System.out.print(output);
//
/////// 		TYDZIEN 02 		////////////////////////////////////////////
//		System.out.println(args[0]);
//		System.out.println(args[1]+" | "+args[2]);
		
//		if (args.length < 3)
//			System.err.print("Za malo argumentow");
//		else
//			System.out.println(" * " + args[0]+" ** " + args[1] + " *** " + args[2]);
//		
		
///////////  	ARGUMENTY WYWOLANIA PROGRAMU	////////////////////////	
//		int i;
//		System.out.println("Wczytano agrumenty: ");
//		for (i = 0; i<args.length;i++)
//		{
//			System.out.print(args[i]+" ");
//		}
//		System.out.print("\n");
//		boolean good=true;
//		double num1 = Double.parseDouble(args[0]);
//		double num2 = Double.parseDouble(args[1]);
//		double wynik = 0;
//		switch(args[2])
//		{
//		case "+":
//		{
//			wynik = num1+num2;
//			break;
//		}
//		case "-":
//		{
//			wynik = num1-num2;
//			break;
//		}
//		case "*":
//		{
//			wynik = num1*num2;
//			break;
//		}
//		case "/":
//		{
//			if(num2==0)
//			{
//				System.err.print("Proba dzielenia przez 0");
//				good = false;
//			}
//			wynik = num1/num2;
//			break;
//		}
//		}
//		if(good)
//		System.out.println("Wynik: "+ wynik);
		
///////////// TABLICE: INDEKSOWANIE, WYPELNIANIE, PRZESZUKIWANIE /////////////////
//		int i;
//		
//		int tab[] = new int[10];
//		for (i = 1; i<10; i=i+1)
//		{
//			tab[i]=5*i+3;
//		}
//		for (i = 1; i < 10; i=i+1)
//			System.out.print(tab[i]+" ");
//		
//		System.out.println("");
//		
///////////////////////////////////////////////////////////////////////////////
		int i = 0;
		String txt1;
		txt1 = JOptionPane.showInputDialog("Ilosc liczb do wylowoania");
		i = Integer.parseInt(txt1);
		int rozmiar = i;
		int tablica_inna[] = new int[rozmiar];
		
		for (i = 0; i < rozmiar; i++)
		{
			tablica_inna[i] = 20-2*i;
			//System.out.print(tablica_inna[i]+" ");
		}
		//System.out.println("");
		
		int zakres = 100;
		for (i = 0; i < rozmiar; i++)
			tablica_inna[i] = (int)(Math.random()*zakres);
		System.out.println("Wylosowana tablica");
		for (i = 0; i< rozmiar; i++)
			System.out.print(tablica_inna[i]+" ");
		System.out.println("");
		
		Date przed_babelkowym = new Date();
		int posortowana [] = babelkowe(tablica_inna, rozmiar);
		Date po_babelkowym = new Date();
		
		System.out.println("Po sortowaniu babelkowym");
		for (i = 0; i< rozmiar; i++)
			System.out.print(posortowana[i]+" ");
		System.out.println("");
		
		Date przed_arrays = new Date();
		Arrays.sort(tablica_inna);
		Date po_arrays = new Date();
		
		System.out.println("Po sortowaniu Arrays");
		for (i = 0; i< rozmiar; i++)
			System.out.print(tablica_inna[i]+" ");
		System.out.println("");
		
		System.out.println("Sortowanie babelkowe zajelo: "+ (po_babelkowym.getTime()-przed_babelkowym.getTime())+"ms");
		System.out.println("Sortowanie arrays zajelo: "+ (po_arrays.getTime()-przed_arrays.getTime())+"ms");
	}

}
