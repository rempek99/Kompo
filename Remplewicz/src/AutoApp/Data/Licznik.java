package AutoApp.Data;

import AutoApp.Model.Resetowalny;
import AutoApp.Model.UjemnaWartosc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

/**
 * Klasa odpowiadaj¹ca za liczniki dystansów w samochodzie
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Licznik implements Resetowalny {
    /**
     * Dystans, który przechowuje licznik, wyra¿ony w metrach
     */
    private double dystans;
    /**
     * Wartoœæ logiczna, okreœlaj¹ca czy licznik mo¿e zostaæ zresetowany
     */
    private boolean staly;
    /**
     * Przechowujê datê pocz¹tku odczytu licznika
     */
    private Date start;
    /**
     * Obiekt umo¿liwiaj¹cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
     * @see PropertyChangeSupport
     * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs;

    /**
     * Domyœlny konstruktor, inicjalizuje sta³y licznik o wartoœci dystansu równej 0
     */
    public Licznik()
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = 0;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor pozwalaj¹cy okreœliæ typ Licznika
     * @param czy_staly wartoœæ logiczna okreœlaj¹ca typ licznika
     */
    public Licznik(boolean czy_staly)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = 0;
        this.start = new Date();
        this.staly = czy_staly;
    }

    /**
     * Kostruktor pozwalaj¹cy okreœliæ wartoœæ dystansu (licznik sta³y)
     * @param dystans wartoœæ dystansu w momencie inicjalizacji obiektu
     */
    public Licznik(float dystans)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = dystans;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor pozwalaj¹cy okreœliæ wartoœæ dystansu oraz typ licznika
     * @param dystans wartoœæ dystansu w momencie inicjalizacji obiektu
     * @param czy_staly wartoœæ logiczna okreœlaj¹ca typ licznika
     */
    public Licznik(float dystans, boolean czy_staly)
    {
        pcs = new PropertyChangeSupport(this);
        this.dystans = dystans;
        this.staly = czy_staly;
        this.start = new Date();
    }

    /**
     * Metoda zeruj¹ca wartoœæ dystansu oraz aktualizuj¹ca datê pocz¹tku odczytu
     * @throws Exception rzucany w przypadku, kiedy nastêpuje próba zresetowania licznika ju¿ zresetowanego
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
     * Dodaje wartoœæ dystansu do licznika
     * @param dystans wartoœæ, która zostanie dodana
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nastêpuje próba dodania wartoœci ujemnej
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
     * Umo¿liwia ustawienie Listener na obiekt klasy, który powiadamia Listener, kiedy jego wartoœæ dystansu ulega zmianie
     * @param Listener obiekt klasy PropertyChangeListener umo¿liwiaj¹cy odczyt komunikatów o zmianie parametru
     * @see PropertyChangeListener
     */
    public void addLicznikListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
