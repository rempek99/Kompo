package AutoApp.Model;

import AutoApp.Data.Licznik;
import AutoApp.Data.ObslugaBazy;
import AutoApp.Data.Podroz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Samochod implements Prowadzenie{

    int counter = 1;
    int moc_silnika;
    int moc_hamulcow;
    private boolean zaplon;
    private boolean gaz;
    Swiatlo mijania,drogowe,lewyKierunkowskaz,prawyKierunkowskaz;
    Chwilowy_odczyt_predkosci temp;
    Licznik licznik1;
    Licznik licznikGlowny; //stały
    Predkosciomierz predkosciomierz1;
    ArrayList<Podroz> przejazdy;
    SymulatorUtratyPredkosci symulator;

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
        this.moc_silnika = moc_silnika;
        this.moc_hamulcow = moc_hamulcow;
        licznik1 = new Licznik(false);
        licznikGlowny = new Licznik(true);
        predkosciomierz1 = new Predkosciomierz(max_predkosc);
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
        przejazdy = new ArrayList<Podroz>();
        mijania = new Swiatlo(Color.yellow);
        drogowe = new Swiatlo(Color.yellow);
        lewyKierunkowskaz = new Swiatlo(Color.orange);
        prawyKierunkowskaz = new Swiatlo(Color.orange);
        gaz = false;
        zaplon = false;
        symulator=new SymulatorUtratyPredkosci(this);
    }

    public Licznik getLicznik1() {
        return licznik1;
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

    @Override
    public void gaz() throws Nieuruchomiony{
        if(!isZaplon())
            throw new Nieuruchomiony();
        try {
            licznik1.dodaj(temp.przejechane());
            licznikGlowny.dodaj(temp.przejechane());
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
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

    @Override
    public void hamulec() {
        try {
            licznik1.dodaj(temp.przejechane());
            licznikGlowny.dodaj(temp.przejechane());
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
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
    public void wczytajPodrozeZBazy(String nazwa_bazy_danych) throws SQLException {
        ObslugaBazy a = new ObslugaBazy(nazwa_bazy_danych);
        przejazdy=a.wczytajBaze();
    }

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

    @Override
    public void uruchom_silnik() {
        zaplon = true;
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
        try {
            licznik1.dodaj(temp.przejechane());
            licznikGlowny.dodaj(temp.przejechane());
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
        przejazdy.add(new Podroz(counter,licznik1.getDystans(),licznik1.getStart(),new Date()));
        counter ++;
        predkosciomierz1.reset();
        temp = new Chwilowy_odczyt_predkosci(predkosciomierz1.getPredkosc());
    }

}
