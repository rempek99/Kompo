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
//////// TYDZIEN 1. ////////////////////////////////////////////////	
		System.out.println("Hello world!!!");
		System.out.println("Inny napis");
		
		double a=4;
		double b=5;
		System.out.println(a+b);
		
		if(b!=0)
			System.out.println(a/b);
		else
			System.out.println("Nie dzielimy przez zero!");
	
		System.out.println(25-7);
		System.out.println(2*6);
		
		String txt1, txt2;
		txt1 = JOptionPane.showInputDialog("Wprowadz pierwsza liczbe");
		txt2 = JOptionPane.showInputDialog("Wprowadz druga liczbe");
	
		System.out.println("Przed konwersja");
		System.out.println(txt1 + txt2);
		double liczba1 = Double.parseDouble(txt1);
		int liczba2 = Integer.parseInt(txt2);
		System.out.println("Po konwersji1");
		System.out.println(liczba1 + liczba2);
		String wiersze;
		wiersze = JOptionPane.showInputDialog("Podaj liczbe wierszy");
		String output = "";
		String tmp="";
		int n = Integer.parseInt(wiersze);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i;j++)
			{
				output += "      ";
			}
			for(int j=0;j<i+1;j++)
			{
				tmp = String.valueOf(pascal(i,j));
				output  += tmp;
				output += "         ";
				//usuwanie spacji po liczbach d³u¿szych ni¿ 1, aby trójk¹t siê nie "rozje¿d¿a³"
				output = output.substring(0, output.length() - (tmp.length()-1)); 
			}
			output += "\n";
		}
		System.out.print(output);

	}

}
