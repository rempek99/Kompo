package AutoApp.Data;

import AutoApp.Model.Resetowalny;
import AutoApp.Model.UjemnaWartosc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

/**
 * Klasa odpowiadajaca za liczniki dystansow w samochodzie
 */
public class Licznik implements Resetowalny {
    double dystans; //metry
    boolean staly;
    Date start;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Konstruktor domy�lny klasy (licznik sta�y)
     */
    public Licznik()
    {
        this.dystans = 0;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor klasy okre�laj�cy typ licznika
     * @param czy_staly definiuje, czy powsta�y licznik b�dzie mo�liwy do zresetowania
     */
    public Licznik(boolean czy_staly)
    {
        this.start = new Date();
        this.staly = czy_staly;
    }

    /**
     * Konstruktor klasy okre�laj�cy pocz�tkowo zapisany dystans
     * @param dystans warto�� przechowywana w liczniku w momencie tworzenia obiektu
     */
    public Licznik(float dystans)
    {
        this.dystans = dystans;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor klasy umorzliwiaj�cy okre�lenie jego typu oraz startowego dystansu
     * @param dystans warto�� przechowywana w liczniku w momencie tworzenia obiektu
     * @param czy_staly definiuje, czy powsta�y licznik b�dzie mo�liwy do zresetowania
     */
    public Licznik(float dystans, boolean czy_staly)
    {
        this.dystans = dystans;
        this.staly = czy_staly;
        this.start = new Date();
    }

    /**
     * Zeruje ilo�� przejechanych kilometr�w oraz ustala now� dat� pocz�tku odczytu
     * @throws Exception wyj�tek powoduje pr�ba zresetowania licznika sta�ego lub ju� zresetowanego
     */
    public void reset() throws Exception
    {
        if(this.dystans == 0 && start.equals(new Date()))
            throw new Exception("Samochod.model.Licznik zostal juz zresetowany");
        if(this.staly)
            throw new LicznikStaly();
        this.start = new Date();
        this.dystans = 0;
    }

    public Date getStart() {
        return start;
    }

    /**
     * Dodaje warto�� do licznika
     * @param dystans warto��, kt�ra zostanie dodana
     * @throws UjemnaWartosc powoduje pr�ba dodania warto�ci ujemnej
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
    public void addLicznikListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
