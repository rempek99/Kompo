import java.util.ArrayList;
import java.util.Date;

public class Samochod implements Prowadzenie{
    int moc_silnika;
    int moc_hamulcow;
    Chwilowy_odczyt_predkosci temp;
    Licznik licznik1;
    Predkosciomierz predkosciomierz1;
    ArrayList<Podroz> przejazdy;

    public Samochod(int moc_silnika,int moc_hamulcow, int max_predkosc)
    {
        this.moc_silnika = moc_silnika;
        this.moc_hamulcow = moc_hamulcow;
        licznik1 = new Licznik(false);
        predkosciomierz1 = new Predkosciomierz(max_predkosc);
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        przejazdy = new ArrayList<Podroz>();
    }

    @Override
    public void gaz() {
        try{licznik1.dodaj(temp.przejechane());}
        catch(UjemnaWartosc ex)
        {
            System.out.println(ex);
        }
        try {
            predkosciomierz1.zwieksz_predkosc((float) (moc_silnika * 0.1));
        }
        catch(UjemnaWartosc ex)
        {
            System.out.print(ex);
        }
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

    @Override
    public void hamulec() {
        try{licznik1.dodaj(temp.przejechane());}
        catch(UjemnaWartosc ex)
        {
            System.out.println(ex);
        }
        try {
            predkosciomierz1.zmniejsz_predkosc((float) (moc_hamulcow * 0.3));
        }
        catch(UjemnaWartosc ex)
        {
            System.out.print(ex);
        }
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

    @Override
    public void skret(boolean prawo) {
        if(prawo)
            System.out.println("Skrecam w prawo");
        else
            System.out.println("Skrecam w lewo");
    }

    @Override
    public void uruchom_silnik() {
        predkosciomierz1.reset();
        try {licznik1.reset();}
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

    @Override
    public void zgas_silnik() {
        try{licznik1.dodaj(temp.przejechane());}
        catch(UjemnaWartosc ex)
        {
            System.out.println(ex);
        }
        przejazdy.add(new Podroz(licznik1.getDystans(),licznik1.getStart(),new Date()));
        predkosciomierz1.reset();
        try {licznik1.reset();}
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
