package AutoApp;

import AutoApp.Model.Samochod;
import AutoApp.View.Controller;
import AutoApp.View.Okienko;
import AutoApp.Data.ObslugaBazy;

import java.sql.SQLException;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException, SQLException {

		Okienko okno1 = new Okienko();
		Samochod auto1 = new Samochod(150,12,210);
		Controller cont1 = new Controller(auto1,okno1);
		okno1.setVisible(true);
	}


}
