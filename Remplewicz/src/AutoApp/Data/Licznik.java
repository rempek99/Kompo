package AutoApp.Data;

import AutoApp.Model.Resetowalny;
import AutoApp.Model.UjemnaWartosc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

/**
 * Klasa odpowiadaj�ca za liczniki dystans�w w samochodzie
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Licznik implements Resetowalny {
    /**
     * Dystans, kt�ry przechowuje licznik, wyra�ony w metrach
     */
    private double dystans;
    /**
     * Warto�� logiczna, okre�laj�ca czy licznik mo�e zosta� zresetowany
     */
    private boolean staly;
    /**
     * Przechowuj� dat� pocz�tku odczytu licznika
     */
    private Date start;
    /**
     * Obiekt umo�liwiaj�cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
     * @see PropertyChangeSupport
     * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs;

    /**
     * Domy�lny konstruktor, inicjalizuje sta�y licznik o warto�ci dystansu r�wnej 0
     */
    public Licznik()
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = 0;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor pozwalaj�cy okre�li� typ Licznika
     * @param czy_staly warto�� logiczna okre�laj�ca typ licznika
     */
    public Licznik(boolean czy_staly)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = 0;
        this.start = new Date();
        this.staly = czy_staly;
    }

    /**
     * Kostruktor pozwalaj�cy okre�li� warto�� dystansu (licznik sta�y)
     * @param dystans warto�� dystansu w momencie inicjalizacji obiektu
     */
    public Licznik(float dystans)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = dystans;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor pozwalaj�cy okre�li� warto�� dystansu oraz typ licznika
     * @param dystans warto�� dystansu w momencie inicjalizacji obiektu
     * @param czy_staly warto�� logiczna okre�laj�ca typ licznika
     */
    public Licznik(float dystans, boolean czy_staly)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = dystans;
        this.staly = czy_staly;
        this.start = new Date();
    }

    /**
     * Metoda zeruj�ca warto�� dystansu oraz aktualizuj�ca dat� pocz�tku odczytu
     * @throws Exception rzucany w przypadku, kiedy nast�puje pr�ba zresetowania licznika ju� zresetowanego
     */
    public void reset() throws Exception
    {
        this.start = new Date();
        if(this.dystans == 0)
            throw new Exception("Samochod.model.Licznik zostal juz zresetowany");
        if(this.staly)
            throw new LicznikStaly();
        this.dystans = 0;
    }

    public Date getStart() {
        return start;
    }

    /**
     * Dodaje warto�� dystansu do licznika
     * @param dystans warto��, kt�ra zostanie dodana
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nast�puje pr�ba dodania warto�ci ujemnej
     */
    public void dodaj(double dystans) throws UjemnaWartosc
    {
        if(dystans<0)
            throw new UjemnaWartosc(dystans);
        String oldValue = Double.toString(getDystans());
        this.dystans += dystans;
        String newValue = Double.toString(getDystans());
        pcs.firePropertyChange("dystans",oldValue,newValue);
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

    /**
     * Umo�liwia ustawienie Listener na obiekt klasy, kt�ry powiadamia Listener, kiedy jego warto�� dystansu ulega zmianie
     * @param Listener obiekt klasy PropertyChangeListener umo�liwiaj�cy odczyt komunikat�w o zmianie parametru
     * @see PropertyChangeListener
     */
    public void addLicznikListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
