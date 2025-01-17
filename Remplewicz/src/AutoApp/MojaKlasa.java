package AutoApp;

		import AutoApp.Model.Samochod;
		import AutoApp.View.Consola;
		import AutoApp.View.Controller;
		import AutoApp.View.Okienko;
		import java.io.*;
		import java.sql.SQLException;
		import java.util.Scanner;

/**
 * Klasa main projektu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class MojaKlasa {
	public static void main(String[] args) throws CloneNotSupportedException, SQLException {
		File file = new File("licznikGlowny.txt");
		double value = 0;
		try {
			Scanner in = new Scanner(file);
			value = Double.parseDouble(in.nextLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Samochod auto1 = new Samochod(150, 12, 210, value);
		if(args.length>0)
		{
			if(args[0].equals("console"))
			{
				Consola cons1 = new Consola(auto1);
				cons1.start();
			}
		}
		else
		{
			Okienko okno1 = new Okienko();
			Controller cont1 = new Controller(auto1, okno1);
			okno1.setVisible(true);
		}
	}
}