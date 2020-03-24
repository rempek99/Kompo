import javax.swing.*;

public class main {
	
	static int silnia(int n)
	{
		if(n==0 || n==1)
		{
			return 1;
		}
		else
		{
			return silnia(n-1)*n;
		}
	}
	
	static int pomnoz(int k, int n)
	{
		if(n==k)
		{
			return k;
		}
		else
		{
			return n*pomnoz(k,n-1);
		}
	}
	
	static int newton(int n, int k)
	{
		if(n==k)
		{
			return 1;
		}
		else 
		{
			return pomnoz(k+1,n)/silnia(n-k);
		}
		
	}
	
	static int pascal(int n,int k)
	{
		if(k==0)
		{
			return 1;
		}
		else
		{
			
			return newton(n,k);
		}
	}

	public static void main(String[] args) {
		
//		System.out.println("Hello world !!!");
//		System.out.println("Inny napis");
//		
//		double a=4, b=5;
//		System.out.println(a+b);
//		System.out.println(4-1);
//		if(b!=0)
//		{
//			System.out.println(a/b);
//		}
//		else
//		{
//			System.out.println("Nie dzielimy przez zero!");
//		}
//		
//		String txt1;
//		txt1 = JOptionPane.showInputDialog("Wprowadz pierwsza liczbe");
//		
//		String txt2;
//		txt2 = JOptionPane.showInputDialog("Wprowadz druga liczbe");
//		
//		System.out.println(txt1+txt2);
//		//Stalo sie tak, bo zmienne txt sa typu string wiec "txt1+txt2" polaczylo lancuchy
//		// Zamiast dodac liczby
//		
//		double liczba1 = Double.parseDouble(txt1);
//		int liczba2 = Integer.parseInt(txt2);
//		
//		System.out.println(liczba1 + liczba2);
//		
		String txt1;
		txt1 = JOptionPane.showInputDialog("Wprowadz ilosc wierszy trojkata Pascala");
		int liczba = Integer.parseInt(txt1);
		int tablica[][] = new int [liczba][liczba];
		for(int i = 0;i<liczba;i++)
		{
			for(int k=0;k<liczba-i;k++)
			{
				System.out.print("   ");
			}
			for(int j=0;j<=i;j++)
			{
				tablica[i][j]=pascal(i,j);
				System.out.printf( "%6d",tablica[i][j]);
				
			}
			System.out.println();
		}
	}

}
