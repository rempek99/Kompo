package AutoApp.Model;

import AutoApp.Data.Licznik;
import AutoApp.Data.ObslugaBazy;
import AutoApp.Data.Podroz;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Klasa reprezentujaca Samochod. Posiada podstawowe funkcjonalnos�i jak przyspiesznaie i hamowanie, skrecanie oraz przechowuje liste zapoisanych podrowzy
 * @see Prowadzenie
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Samochod implements Prowadzenie{

    /**
     * Obiekt odpowiedzialny za przeliczanie �redniego spalania pojazdu
     * @see KalukulatorSpalania
     */
    private KalukulatorSpalania kalkulator;
    /**
     * Obiekt odpowiedzialny za od�wie�anie warto�ci wskazywanych przez liczniki
     * @see AktualizatorLicznikow
     */
    private AktualizatorLicznikow aktualizatorLicznikow;
    /**
     * Odlicza warto�ci ca�kowite, kt�re przypisuje odbytym podr�om jako identyfikator
     */
    private int counter;
    /**
     * Moc silnika pojazdu
     */
    private int moc_silnika;
    /**
     * Moc hamulc�w pojazdu
     */
    private  int moc_hamulcow;
    /**
     * Przechowuje o stanie silnika (uruchomiony/zgaszony)
     */
    private boolean zaplon;
    /**
     * Informacja dla SymulatorUtratyPredkosci czy pojazd jest napedzany by niwelowac utraty predkosci (i/lub przyspieszac) czy nie.
     */
    private boolean gaz;
    /**
     * Obiekt odpowiadaj�cy za dzia�anie tempomatu
     * @see Tempomat
     */
    private Tempomat tempomat;
    /**
     * Obiekty �wiate� zamontowanych w poje�dzie
     * @see Swiatlo
     */
    private Swiatlo mijania,drogowe,lewyKierunkowskaz,prawyKierunkowskaz,przeciwmgielne_przod,przeciwmgielne_tyl;
    /**
     * Chwilowy odczyt, rejestowany pomi�dzy zmianami pr�dko�ci pojazdu
     * @see Chwilowy_odczyt_predkosci
     */
    private  Chwilowy_odczyt_predkosci temp;
    /**
     * Zlicza dystans pokonany podczas aktualnej podrozy czyli mi�dzy zgaszeniem a zap�onem silnika
     */
    private  Licznik licznikPodrozy,licznikUzytkownika;
    /**
     * Przechowuje informacje o ca�kowitym przebiegu pojazdu
     */
    private Licznik licznikGlowny;
    /**
     *  Pr�dko�ciomierz przechowuj�cy informacj� o pr�dko�ci, z kt�r� porusza si� pojazd
     * @see Predkosciomierz
     */
    private  Predkosciomierz predkosciomierz1;
    /**
     * Zarejestrowane przejazdy
     */
    private  ArrayList<Podroz> przejazdy;
    /**
     * Obiekt odpowiadaj�cy za wytracanie pr�dko�ci pojazdu
     * @see SymulatorUtratyPredkosci
     */
    private SymulatorUtratyPredkosci symulator;

    public void setGaz(boolean gaz) {
        this.gaz = gaz;
    }

    /**
     * Konstruktor klasy
     * @param moc_silnika Moc silnika pojazdu
     * @param moc_hamulcow Moc hamulc�w pojazdu
     * @param max_predkosc Zakres pr�dko�ciomierza
     * @param licznikGlowny Warto�� przebiegu
     */
    public Samochod(int moc_silnika, int moc_hamulcow, int max_predkosc, double licznikGlowny)
    {
        counter =1;
        tempomat=new Tempomat(this);
        tempomat.wylacz();
        this.moc_silnika = moc_silnika;
        this.moc_hamulcow = moc_hamulcow;
        licznikPodrozy = new Licznik(false);
        this.licznikGlowny = new Licznik(true);
        try {
            this.licznikGlowny.dodaj(licznikGlowny);
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
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
    /**
     * Pozwala na zwiekszanie predkosci pojazdu
     * @throws Nieuruchomiony rzucany, kiedy silnik nie jest uruchomiony
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
     * Umo�liwia hamowanie pojazdem
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
    /**
     * Zapisuje do podanego jako parametr katalogu liste podr�y jako plik podroze.xml
     * @param fileDirectoryPath �cie�ka do katalogu w ktorym bedzie zapisany rejestr podr�y
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
     * Wczytuje z pliku typu xml liste podr�y i zast�puje ni� aktualna list�
     * @param filePath �cie�ka do pliku z kt�rego ma zosta� wczytana lista podr�y
     * @throws Exception rzucany w przypadku b��du wczytywania pliku
     */
    public void wczytajPodroze (String filePath) throws Exception
    {
        String dest=filePath;
        try {
            File out=new File(dest);
            if(!out.exists())
            {
                throw new Exception("B��d pliku");
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
     * Umo�liwia wczytanie zapisu podr�y z bazy danych
     * @param nazwa_bazy_danych Nazwa bazy danych
     * @throws SQLException rzucany w przypadku b��d�w zwi�zanych np. z dost�pem do bazy danych
     * @see SQLException
     */
    public void wczytajPodrozeZBazy(String nazwa_bazy_danych) throws SQLException {
        ObslugaBazy a = new ObslugaBazy(nazwa_bazy_danych);
        przejazdy=a.wczytajBaze();
    }
    /**
     * Uruchamia kierunkowskazy odpowiadajace kierunkowi dokonania skr�tu
     * @param prawo Informuje czy sk�t jest w prawo czy w lewo
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
     * Gasi silnik, dodaje do listy podrozy podr� rozpoczeta przy uruchomieniu
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
    /**
     * Zapisuje stan przebiegu pojazdu
     * @param nameOfFile nazwa pliku, do kt�rego zapisywane s� dane
     * @throws FileNotFoundException rzucany w przypadku b��du pliku
     */
    public void zapiszGlownyLicznik(String nameOfFile) throws FileNotFoundException {
        PrintWriter print=new PrintWriter(nameOfFile);
        print.print(licznikGlowny.getDystans());
        print.close();
    }

    @Override
    public String toString() {
        String ret = "";
        ret+="INFORMACJE O POJEZDZIE:" +
                "\n Aktualna predkosc: "+getPredkosciomierz().getPredkosc()+"km/h"+
                "\n Moc silnika:"+getMoc_silnika()
                +"kM\n Predkosc maksymalna: "+getPredkosciomierz().getMax_predkosc()+"km/h\nSkutecznosc hamulcy: "+getMoc_hamulcow()+" %";
        if(isZaplon()) {
            ret+="\nSilnik uruchomiony";
        }else{
            ret+="\nSilnik zgaszony";
        }
        ret+="\n    ---Stan licznikow---"
                +"\nPzebieg glowny: "+getLicznikGlowny().getDystans()
                +"km\nPrzebieg obecnej podrozy: "+getLicznikPodrozy().getDystans()
                +"km\nPrzebieg resetowalny: "+getLicznikUzytkownika().getDystans()
                +"km\nWlaczone swiatla: ";
        if(getMijania().isWlaczone()){
            ret+=",swiatla mijania";
        }
        if(getDrogowe().isWlaczone()){
            ret+=",swiatla drogowe";
        }
        if(getPrzeciwmgielne_przod().isWlaczone()){
            ret+=",swiatla przeciwmgielne przod";
        }
        if(getPrzeciwmgielne_tyl().isWlaczone()){
            ret+=",swiatla przeciwmgielne tyl";
        }
        ret+=("\nKierunkowskaz uruchomiony: ");
        if(getLewyKierunkowskaz().isWlaczone()){
            ret+=" lewy ";
        }
        if(getPrawyKierunkowskaz().isWlaczone()){
            ret+=" prawy ";
        }
        return ret;
    }

    public Swiatlo getPrzeciwmgielne_przod() {
        return przeciwmgielne_przod;
    }
    public Swiatlo getPrzeciwmgielne_tyl() {
        return przeciwmgielne_tyl;
    }
    public KalukulatorSpalania getKalkulator() {
        return kalkulator;
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
    public boolean isZaplon() {
        return zaplon;
    }
    public boolean isGaz() {
        return gaz;
    }
}
