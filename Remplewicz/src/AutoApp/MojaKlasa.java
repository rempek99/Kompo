package AutoApp;

		import AutoApp.Model.Samochod;
		import AutoApp.View.Controller;
		import AutoApp.View.Okienko;
		import AutoApp.Data.ObslugaBazy;

		import java.io.*;
		import java.sql.SQLException;
		import java.util.Scanner;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException, SQLException {
		File file = new File("licznikGlowny.txt");
		double value = 0;
		try {
			Scanner in = new Scanner(file);
			value = Double.parseDouble(in.nextLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Okienko okno1 = new Okienko();
		Samochod auto1 = new Samochod(150, 12, 210, value);
		Controller cont1 = new Controller(auto1, okno1);
		okno1.setVisible(true);


	}
}
