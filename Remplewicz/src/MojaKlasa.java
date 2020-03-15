import javax.swing.*;
import java.util.Arrays;
import java.util.Date; 

public class MojaKlasa {
	
	static void babelkowe(int tab[], int rozmiar)
	{
		int tmp = 0;
		for (int i = 0; i < rozmiar; i++)
		{
			for (int j = 1; j < rozmiar - i; j++)
			{
				if(tab[j-1]>tab[j])
				{
					tmp = tab[j];
					tab[j] = tab[j-1];
					tab[j-1] = tmp;
				}
			}
		}
	}
	static int[] kopiuj_tab(int tab[], int rozmiar)
	{
		int skopiowana[] = new int[rozmiar];
		for (int x = 0; x < rozmiar; x++)
			skopiowana[x] = tab[x];
		
		return skopiowana;
	}
	
	public static void main(String[] args) {
		
	/////// 		TYDZIEN 02 		////////////////////////////////////////////
			System.out.println(args[0]);
			System.out.println(args[1]+" | "+args[2]);
			if (args.length < 3)
				System.err.print("Za malo argumentow");
			else
				System.out.println(" * " + args[0]+" ** " + args[1] + " *** " + args[2]);
			System.out.println("___________________________________________________________");
			
			
	///////////  	ARGUMENTY WYWOLANIA PROGRAMU	////////////////////////	
			System.out.println("Wczytano agrumenty: ");
			for (int i = 0; i<args.length;i++)
			{
				System.out.print(args[i]+" ");
			}
			System.out.print("\n");
			boolean good=true;
			double num1 = Double.parseDouble(args[0]);
			double num2 = Double.parseDouble(args[1]);
			double wynik = 0;
			switch(args[2])
			{
			case "+":
			{
				wynik = num1+num2;
				break;
			}
			case "-":
			{
				wynik = num1-num2;
				break;
			}
			case "*":
			{
				wynik = num1*num2;
				break;
			}
			case "/":
			{
				if(num2==0)
				{
					System.err.print("Proba dzielenia przez 0");
					good = false;
				}
				wynik = num1/num2;
				break;
			}
			}
			if(good)
			System.out.println("Wynik: "+ wynik);
			System.out.println("___________________________________________________________");
			
	///////////// TABLICE: INDEKSOWANIE, WYPELNIANIE, PRZESZUKIWANIE /////////////////
			
			int tab[] = new int[10];
			for (int i = 1; i<10; i=i+1)
			{
				tab[i]=5*i+3;
			}
			for (int i = 1; i < 10; i=i+1)
				System.out.print(tab[i]+" ");
			
			System.out.println("");
			System.out.println("___________________________________________________________");
//			
	///////////////////////////////////////////////////////////////////////////////
			int i = 0;
			
//			for (i = 0; i < rozmiar; i++)
//			{
//				tablica_inna[i] = 20-2*i;
//			}
			
			String txt1;
			txt1 = JOptionPane.showInputDialog("Ilosc liczb do wylowoania");
			i = Integer.parseInt(txt1);
			int rozmiar = i;
			int tablica_inna[] = new int[rozmiar];
			
			//wypelnianie tablicy losowymi liczbami
			int zakres = 100;
			for (i = 0; i < rozmiar; i++)
				tablica_inna[i] = (int)(Math.random()*zakres);
			System.out.println("Wylosowana tablica");
			for (i = 0; i< rozmiar; i++)
				System.out.print(tablica_inna[i]+" ");
			System.out.println("");
			
			//kopiuje wylosowana tablice do tab1, aby potem ja posortowac
			int tab1[] = kopiuj_tab(tablica_inna,rozmiar);
			
			//stoper do sortowania babelkowego
			Date przed_babelkowym = new Date();
			babelkowe(tab1, rozmiar);
			Date po_babelkowym = new Date();
			
			System.out.println("Po sortowaniu babelkowym");
			for (i = 0; i< rozmiar; i++)
				System.out.print(tab1[i]+" ");
			System.out.println("");
			
			int tab2[] = kopiuj_tab(tablica_inna,rozmiar);
			
			//stoper do sortowania Arrays
			Date przed_arrays = new Date();
			Arrays.sort(tab2);
			Date po_arrays = new Date();
			
			System.out.println("Po sortowaniu Arrays");
			for (i = 0; i< rozmiar; i++)
				System.out.print(tab2[i]+" ");
			System.out.println("");
			
			System.out.println("Sortowanie babelkowe zajelo: "+ (po_babelkowym.getTime()-przed_babelkowym.getTime())+"ms");
			System.out.println("Sortowanie arrays zajelo: "+ (po_arrays.getTime()-przed_arrays.getTime())+"ms");

	}

}
