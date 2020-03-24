package zajecia2;

import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Date; 

public class main {

	public static void main(String[] args) {
		// drukowanie argumentow wywolania
//		System.out.println(args[0]); 
//		System.out.println(args[1]); 
//		System.out.println(args[2]); 
//		if (args.length <3)
//			System.err.print("Za malo argumentow");
//		else
//			System.out.println(" * " + args[0]+" ** "+args[1]+" *** "+args[2]); 
//		System.out.println("Argumenty wywolania:");
//		for(int i=0; i<args.length;i++)
//			System.out.print(args[i]+", ");
//		System.out.println();
//		String txt1,txt2,txt3;
//		txt1=JOptionPane.showInputDialog("Podaj pierwsza liczbe");
//		txt2=JOptionPane.showInputDialog("Podaj druga liczbe");
//		txt3=JOptionPane.showInputDialog("Podaj znak dzialania( / * + -)");
//		double liczba1=Double.parseDouble(txt1);
//		int liczba2=Integer.parseInt(txt2);
//		switch(txt3)
//		{
//			case "/":
//			{
//				System.out.println(liczba1/liczba2);
//				break;
//			}
//			case "*":
//			{
//				System.out.println(liczba1*liczba2);
//				break;
//			}
//			case "+":
//			{
//				System.out.println(liczba1+liczba2);
//				break;
//			}
//			case "-":
//			{
//				System.out.println(liczba1-liczba2);
//				break;
//			}
//			default:
//				System.out.println("Nie rozpoznano znaku dzialania");
//		}
		//tablica-deklaracja
		int tab[]=new int [10];
		for(int i=0;i<10;i++)
			tab[i]=4;
		// Drukowanie zawartosci tablicy
		for (int i=0; i<10; i++) 
			System.out.print(tab[i]+" | ");
		System.out.println();
		for(int i=0;i<10;i++)
			tab[i]=i+1;
		for (int i=0; i<10; i++) 
			System.out.print(tab[i]+" | ");
		System.out.println();
		//ltablica o podanym rozmiarze i losowych wartosciach
		String rozm;
		rozm=JOptionPane.showInputDialog("Podaj rozmiar tablicy dynamicznej");
		int tab2[]=new int[Integer.parseInt(rozm)],tab21[]=new int[Integer.parseInt(rozm)];
		for(int i=0;i<Integer.parseInt(rozm);i++)
		{
			tab2[i]=(int)(Math.random()*100);
			tab21[i]=tab2[i];
		}
		for (int i=0; i<Integer.parseInt(rozm); i++) 
			System.out.print(tab2[i]+" | ");
		//sortowanie
		boolean zm=true;
		int tmp,r=Integer.parseInt(rozm);
		Date b1=new Date();
		while(zm)
		{
			zm=false;
			for(int i=0;i<r-1;i++)
			{
				if(tab2[i]>tab2[i+1])
				{
					zm=true;
					tmp=tab2[i+1];
					tab2[i+1]=tab2[i];
					tab2[i]=tmp;
				}
			}
		}
		Date b2=new Date();
		System.out.println();
		System.out.println("posortowana babelkowo");
		for (int i=0; i<Integer.parseInt(rozm); i++) 
			System.out.print(tab2[i]+" | ");
		Date s1=new Date();
		Arrays.sort(tab21);
		Date s2=new Date();
		System.out.println();
		System.out.println("posortowana nrzedziem z biblioteki");
		for (int i=0; i<Integer.parseInt(rozm); i++) 
			System.out.print(tab21[i]+" | ");
		System.out.println();
		System.out.println("Czas sortowania bombelkowego: "+(b2.getTime()-b1.getTime())+"ms");
		System.out.println("Czas sortowania narzedzia z biblioteki: "+(s2.getTime()-s1.getTime())+"ms");

	}

}
