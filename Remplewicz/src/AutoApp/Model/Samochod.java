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
 * Klasa reprezentujaca Samochod. Posiada podstawowe funkcjonalnosæi jak przyspiesznaie i hamowanie, skrecanie oraz przechowuje liste zapoisanych podrowzy
 * @see Prowadzenie
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Samochod implements Prowadzenie{

    /**
     * Obiekt odpowiedzialny za przeliczanie œredniego spalania pojazdu
     * @see KalukulatorSpalania
     */
    private KalukulatorSpalania kalkulator;
    /**
     * Obiekt odpowiedzialny za odœwie¿anie wartoœci wskazywanych przez liczniki
     * @see AktualizatorLicznikow
     */
    private AktualizatorLicznikow aktualizatorLicznikow;
    /**
     * Odlicza wartoœci ca³kowite, które przypisuje odbytym podró¿om jako identyfikator
     */
    private int counter;
    /**
     * Moc silnika pojazdu
     */
    private int moc_silnika;
    /**
     * Moc hamulców pojazdu
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
     * Obiekt odpowiadaj¹cy za dzia³anie tempomatu
     * @see Tempomat
     */
    private Tempomat tempomat;
    /**
     * Obiekty œwiate³ zamontowanych w pojeŸdzie
     * @see Swiatlo
     */
    private Swiatlo mijania,drogowe,lewyKierunkowskaz,prawyKierunkowskaz,przeciwmgielne_przod,przeciwmgielne_tyl;
    /**
     * Chwilowy odczyt, rejestowany pomiêdzy zmianami prêdkoœci pojazdu
     * @see Chwilowy_odczyt_predkosci
     */
    private  Chwilowy_odczyt_predkosci temp;
    /**
     * Zlicza dystans pokonany podczas aktualnej podrozy czyli miêdzy zgaszeniem a zap³onem silnika
     */
    private  Licznik licznikPodrozy,licznikUzytkownika;
    /**
     * Przechowuje informacje o ca³kowitym przebiegu pojazdu
     */
    private Licznik licznikGlowny;
    /**
     *  Prêdkoœciomierz przechowuj¹cy informacjê o prêdkoœci, z któr¹ porusza siê pojazd
     * @see Predkosciomierz
     */
    private  Predkosciomierz predkosciomierz1;
    /**
     * Zarejestrowane przejazdy
     */
    private  ArrayList<Podroz> przejazdy;
    /**
     * Obiekt odpowiadaj¹cy za wytracanie prêdkoœci pojazdu
     * @see SymulatorUtratyPredkosci
     */
    private SymulatorUtratyPredkosci symulator;

    public void setGaz(boolean gaz) {
        this.gaz = gaz;
    }

    /**
     * Konstruktor klasy
     * @param moc_silnika Moc silnika pojazdu
     * @param moc_hamulcow Moc hamulców pojazdu
     * @param max_predkosc Zakres prêdkoœciomierza
     * @param licznikGlowny Wartoœæ przebiegu
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
     * Umo¿liwia hamowanie pojazdem
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
     * Zapisuje do podanego jako parametr katalogu liste podró¿y jako plik podroze.xml
     * @param fileDirectoryPath œcie¿ka do katalogu w ktorym bedzie zapisany rejestr podró¿y
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
     * Wczytuje z pliku typu xml liste podró¿y i zastêpuje ni¹ aktualna listê
     * @param filePath œcie¿ka do pliku z którego ma zostaæ wczytana lista podró¿y
     * @throws Exception rzucany w przypadku b³êdu wczytywania pliku
     */
    public void wczytajPodroze (String filePath) throws Exception
    {
        String dest=filePath;
        try {
            File out=new File(dest);
            if(!out.exists())
            {
                throw new Exception("B³¹d pliku");
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
     * Umo¿liwia wczytanie zapisu podró¿y z bazy danych
     * @param nazwa_bazy_danych Nazwa bazy danych
     * @throws SQLException rzucany w przypadku b³êdów zwi¹zanych np. z dostêpem do bazy danych
     * @see SQLException
     */
    public void wczytajPodrozeZBazy(String nazwa_bazy_danych) throws SQLException {
        ObslugaBazy a = new ObslugaBazy(nazwa_bazy_danych);
        przejazdy=a.wczytajBaze();
    }
    /**
     * Uruchamia kierunkowskazy odpowiadajace kierunkowi dokonania skrêtu
     * @param prawo Informuje czy skêt jest w prawo czy w lewo
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
     * Gasi silnik, dodaje do listy podrozy podró¿ rozpoczeta przy uruchomieniu
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
     * @param nameOfFile nazwa pliku, do którego zapisywane s¹ dane
     * @throws FileNotFoundException rzucany w przypadku b³êdu pliku
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
