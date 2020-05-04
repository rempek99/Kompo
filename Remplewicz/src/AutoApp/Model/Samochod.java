package AutoApp.Model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

public class Samochod implements Prowadzenie{
    int moc_silnika;
    int moc_hamulcow;

    public boolean isZaplon() {
        return zaplon;
    }

    private boolean zaplon = false;
    Swiatlo mijania,drogowe,lewyKierunkowskaz,prawyKierunkowskaz;
    Chwilowy_odczyt_predkosci temp;
    Licznik licznik1;
    public Predkosciomierz getPredkosciomierz() {
        return predkosciomierz1;
    }

    public int getMoc_silnika() {
        return moc_silnika;
    }

    public int getMoc_hamulcow() {
        return moc_hamulcow;
    }

    Predkosciomierz predkosciomierz1;
    ArrayList<Podroz> przejazdy;

    public ArrayList<Podroz> getPrzejazdy() {
        return przejazdy;
    }

    public Samochod(int moc_silnika, int moc_hamulcow, int max_predkosc)
    {
        this.moc_silnika = moc_silnika;
        this.moc_hamulcow = moc_hamulcow;
        licznik1 = new Licznik(false);
        predkosciomierz1 = new Predkosciomierz(max_predkosc);
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        przejazdy = new ArrayList<Podroz>();
    }

    @Override
    public void gaz() throws Nieuruchomiony{
        if(!isZaplon())
            throw new Nieuruchomiony();
        try{licznik1.dodaj(temp.przejechane());}
        catch(UjemnaWartosc ex)
        {
            System.out.println(ex);
        }
        try {
            predkosciomierz1.zwieksz_predkosc((float) ((moc_silnika * 0.01)-(predkosciomierz1.getPredkosc()*0.007)));
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
            predkosciomierz1.zmniejsz_predkosc((float)(moc_hamulcow * 60/predkosciomierz1.getPredkosc()));
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
        zaplon = true;
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
        zaplon = false;
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
