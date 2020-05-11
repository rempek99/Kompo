package AutoApp.Data;

import AutoApp.Model.Resetowalny;
import AutoApp.Model.UjemnaWartosc;

import java.util.Date;

//Klasa odpowiadajaca za liczniki dystansow w samochodzie
public class Licznik implements Resetowalny {
    double dystans; //metry
    boolean staly;
    Date start;

    public Licznik()
    {
        this.dystans = 0;
        this.staly = true;
        this.start = new Date();
    }
    public Licznik(boolean czy_staly)
    {
        this.start = new Date();
        this.staly = czy_staly;
    }
    public Licznik(float dystans)
    {
        this.dystans = dystans;
        this.staly = true;
        this.start = new Date();
    }
    public Licznik(float dystans, boolean czy_staly)
    {
        this.dystans = dystans;
        this.staly = czy_staly;
        this.start = new Date();
    }
    public void reset() throws Exception
    {
        this.start = new Date();
        if(this.dystans == 0)
            throw new Exception("Samochod.model.Licznik zostal juz zresetowany");
        if(this.staly == true)
            throw new LicznikStaly();
        this.dystans = 0;
    }

    public Date getStart() {
        return start;
    }

    public void dodaj(double dystans) throws UjemnaWartosc
    {
        if(dystans<0)
            throw new UjemnaWartosc(dystans);
        this.dystans += dystans;
    }
    public double getDystans() {
        return dystans;
    }
    public String toString()
    {
        String output = "";
        output +="Pomiar od: "+ start + " Dystans: " + dystans +"m";
        return output;
    }
}
