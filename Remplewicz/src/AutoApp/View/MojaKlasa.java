package AutoApp.View;

import AutoApp.Model.Samochod;


public class MojaKlasa {


	public void main(String[] args) throws CloneNotSupportedException {

		Samochod auto = new Samochod(200,150,150);
		auto.uruchom_silnik();
		auto.gaz();
		auto.gaz();
		auto.gaz();
		Okienko wind = new Okienko(this);
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException ex)
		{
			System.out.println(ex);
		}
		auto.gaz();
		auto.hamulec();
		auto.zgas_silnik();
		System.out.print(auto.przejazdy);
	}


}
