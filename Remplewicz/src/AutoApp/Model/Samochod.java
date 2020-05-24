package AutoApp.Model;

import AutoApp.Data.Licznik;
import AutoApp.Data.ObslugaBazy;
import AutoApp.Data.Podroz;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Klasa reprezentujaca Samochod. Posiada podstawowe funkcjonalnosći jak przyspiesznaie i hamowanie, skrecanie oraz przechowuje liste zapoisanych podrowzy
 * @see Prowadzenie
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Samochod implements Prowadzenie{

    private KalukulatorSpalania kalkulator;
    private AktualizatorLicznikow aktualizatorLicznikow;
    private int counter = 1;
    private int moc_silnika;
    private  int moc_hamulcow;
    /**
     * Przechowuje o stanie silnika (uruchomiony/zgaszony)
     */
    private boolean zaplon;
    /**
     * Informacja dla SymulatorUtratyPredkosci czy pojazd jest napedzany by niwelowac utraty predkosci (i/lub przyspieszac) czy nie.
     */
    private boolean gaz;
    private Tempomat tempomat;
    private Swiatlo mijania,drogowe,lewyKierunkowskaz,prawyKierunkowskaz,przeciwmgielne_przod,przeciwmgielne_tyl;
    private  Chwilowy_odczyt_predkosci temp;
    /**
     * Zlicza dystans pokonany podczas aktualnej podrozy czyli między zgaszeniem a odpaleniem silnika
     */
    private  Licznik licznikPodrozy,licznikUzytkownika;
    /**
     * Przechowuje informacje o całkowitym przebiegu pojazdu
     */
    private Licznik licznikGlowny; //stały
    private  Predkosciomierz predkosciomierz1;
    private  ArrayList<Podroz> przejazdy;
    private SymulatorUtratyPredkosci symulator;

    public boolean isZaplon() {
        return zaplon;
    }
    public boolean isGaz() {
        return gaz;
    }

    public void setGaz(boolean gaz) {
        this.gaz = gaz;
    }




    public Samochod(int moc_silnika, int moc_hamulcow, int max_predkosc)
    {
        tempomat=new Tempomat(this);
        tempomat.wylacz();
        this.moc_silnika = moc_silnika;
        this.moc_hamulcow = moc_hamulcow;
        licznikPodrozy = new Licznik(false);
        licznikGlowny = new Licznik(true);
        licznikUzytkownika=new Licznik(false);
        predkosciomierz1 = new Predkosciomierz(max_predkosc);
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        przejazdy = new ArrayList<Podroz>();
        mijania = new Swiatlo(Color.yellow);
        drogowe = new Swiatlo(Color.yellow);
        przeciwmgielne_przod = new Swiatlo(Color.yellow);
        przeciwmgielne_tyl = new Swiatlo(Color.RED);
        lewyKierunkowskaz = new Swiatlo(Color.orange);
        prawyKierunkowskaz = new Swiatlo(Color.orange);
        gaz = false;
        zaplon = false;
        symulator=new SymulatorUtratyPredkosci(this);
        aktualizatorLicznikow=new AktualizatorLicznikow(temp,this);
        kalkulator=new KalukulatorSpalania(this);
    }

    public Tempomat getTempomat() {
        return tempomat;
    }

    public Licznik getLicznikPodrozy() {
        return licznikPodrozy;
    }

    public Predkosciomierz getPredkosciomierz() {
        return predkosciomierz1;
    }

    public void setMoc_hamulcow(int moc_hamulcow) {
        this.moc_hamulcow = moc_hamulcow;
    }

    public Licznik getLicznikGlowny() {
        return licznikGlowny;
    }
    public Licznik getLicznikUzytkownika() {
        return licznikUzytkownika;
    }

    public void setMoc_silnika(int moc_silnika) {
        this.moc_silnika = moc_silnika;
    }

    public int getMoc_silnika() {
        return moc_silnika;
    }

    public int getMoc_hamulcow() {
        return moc_hamulcow;
    }

    public ArrayList<Podroz> getPrzejazdy() {
        return przejazdy;
    }

    /**
     * Pozwala na zwiekszanie predkosci pojazdu
     * @throws Nieuruchomiony
     */
    @Override
    public void gaz() throws Nieuruchomiony{
        if(!isZaplon())
            throw new Nieuruchomiony();
        try {
            predkosciomierz1.zwieksz_predkosc((float) ((moc_silnika * 0.01)-(predkosciomierz1.getPredkosc()*0.007)));
        }
        catch(UjemnaWartosc ex)
        {
            System.out.print(ex);
        }
        Date act = new Date();
        if((act.getTime()-temp.getPoczatek_odczytu().getTime())>1000)
        {
            temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        }
    }

    /**
     * Umożliwia hamowanie pojazdem
     */
    @Override
    public void hamulec() {
        try {
            predkosciomierz1.zmniejsz_predkosc((float)(moc_hamulcow * 30/predkosciomierz1.getPredkosc()));
        }
        catch(UjemnaWartosc ex)
        {
            System.out.print(ex);
        }
        Date act = new Date();
        if((act.getTime()-temp.getPoczatek_odczytu().getTime())>1000)
        {
            temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        }

    }

    public Swiatlo getMijania() {
        return mijania;
    }

    public Swiatlo getDrogowe() {
        return drogowe;
    }

    public Swiatlo getLewyKierunkowskaz() {
        return lewyKierunkowskaz;
    }

    public Swiatlo getPrawyKierunkowskaz() {
        return prawyKierunkowskaz;
    }

    /**
     * Zapisuje do podanego jako parametr katalogu liste podróży jako plik podroze.xml
     * @param fileDirectoryPath ścieżka do katalogu w ktorym bedzie zapisany rejestr podróży
     */
    public void zapiszPodroze (String fileDirectoryPath)
    {
        String dest=fileDirectoryPath+"/podroze.xml";
        try {
            File out=new File(dest);
            if(!out.exists())
            {
                out.createNewFile();
            }
            FileOutputStream output=new FileOutputStream(out);
            XMLEncoder encoder=new XMLEncoder(output);
            encoder.writeObject(przejazdy);
            encoder.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wczytuje z pliku typu xml liste podróży i zastępuje nią aktualna listę
     * @param filePath ścieżka do pliku z którego ma zostać wczytana lista podróży
     * @throws Exception
     */
    public void wczytajPodroze (String filePath) throws Exception
    {
        String dest=filePath;
        try {
            File out=new File(dest);
            if(!out.exists())
            {
                throw new Exception("Błąd pliku");
            }
            FileInputStream input=new FileInputStream(out);
            XMLDecoder decoder=new XMLDecoder(input);
            przejazdy=(ArrayList<Podroz>)decoder.readObject();
            decoder.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Umożliwia wczytanie zapisu podróży z bazy danych
     * @param nazwa_bazy_danych Nazwa bazy danych
     * @throws SQLException
     */
    public void wczytajPodrozeZBazy(String nazwa_bazy_danych) throws SQLException {
        ObslugaBazy a = new ObslugaBazy(nazwa_bazy_danych);
        przejazdy=a.wczytajBaze();
    }

    public KalukulatorSpalania getKalkulator() {
        return kalkulator;
    }

    /**
     * Uruchamia kierunkowskazy odpowiadajace kierunkowi dokonania skrętu
     * @param prawo Informuje czy skęt jest w prawo czy w lewo
     */
    @Override
    public void skret(boolean prawo) {
        if(prawo)
        {
            prawyKierunkowskaz.migaj();
            prawyKierunkowskaz.wylacz();
        }

        else
        {
            lewyKierunkowskaz.migaj();
            lewyKierunkowskaz.wylacz();
        }

    }

    /**
     * Uruchamia silnik oraz resetuje licznik
     */
    @Override
    public void uruchom_silnik() {
        zaplon = true;
        try {
            licznikPodrozy.reset();}
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

    /**
     * Gasi silnik, dodaje do listy podrozy podróż rozpoczeta przy uruchomieniu
     * silnika i zakonczona przy wywolaniu tej funkcji
     */
    @Override
    public void zgas_silnik() {
        zaplon = false;
        try {
            double tmp=temp.przejechane(predkosciomierz1.getPredkosc());
            licznikPodrozy.dodaj(tmp);
            licznikGlowny.dodaj(tmp);
            licznikUzytkownika.dodaj(tmp);
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
        przejazdy.add(new Podroz(counter, licznikPodrozy.getDystans(), licznikPodrozy.getStart(),new Date()));
        counter ++;
        //predkosciomierz1.reset();
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

    public Swiatlo getPrzeciwmgielne_przod() {
        return przeciwmgielne_przod;
    }

    public Swiatlo getPrzeciwmgielne_tyl() {
        return przeciwmgielne_tyl;
    }
}
