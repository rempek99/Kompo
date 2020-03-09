import javax.swing.*;

public class MojaKlasa {

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
		String wiersze;
		wiersze = JOptionPane.showInputDialog("Podaj liczbe wierszy");
		String output = "";
		int n = Integer.parseInt(wiersze);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i;j++)
			{
				output += "    ";
			}
			for(int j=0;j<i+1;j++)
			{
				output += pascal(i,j);
				output += "       ";
			}
			output += "\n";
		}
		System.out.print(output);
		
		
	}

}
