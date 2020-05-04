package AutoApp;

import AutoApp.Logic.Samochod;
import AutoApp.View.Controller;
import AutoApp.View.Okienko;


public class MojaKlasa {


	public static void main(String[] args) throws CloneNotSupportedException {

		Okienko okno1 = new Okienko();
		Samochod auto1 = new Samochod(150,12,210);
		Controller cont1 = new Controller(auto1,okno1);
		okno1.setVisible(true);
//		ArrayList<Podroz> tmp=new ArrayList<Podroz>(),tmp2=new ArrayList<Podroz>();
//		tmp.add(new Podroz(15,new Date(),new Date()));
//		tmp.add(new Podroz(25,new Date(),new Date()));
//		tmp.add(new Podroz(35,new Date(),new Date()));
//		tmp.add(new Podroz(45,new Date(),new Date()));
//		System.out.println(tmp);
//		FileOutputStream out;
//		FileInputStream in;
//		try {
//			in=new FileInputStream(new File("./podroze.xml"));
//			out=new FileOutputStream(new File("./podroze.xml"));
//			XMLDecoder dec=new XMLDecoder(in);
//			XMLEncoder enc=new XMLEncoder(out);
//			enc.writeObject(tmp);
//			enc.close();
//			out.close();
//			tmp2=(ArrayList<Podroz>) dec.readObject();
//			System.out.println("/////////////////////////////////////////////");
//			System.out.println(tmp2);
//			dec.close();
//			in.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


	}


}
