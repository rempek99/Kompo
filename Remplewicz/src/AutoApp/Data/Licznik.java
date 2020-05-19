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
     * Konstruktor domyœlny klasy (licznik sta³y)
     */
    public Licznik()
    {
        this.dystans = 0;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor klasy okreœlaj¹cy typ licznika
     * @param czy_staly definiuje, czy powsta³y licznik bêdzie mo¿liwy do zresetowania
     */
    public Licznik(boolean czy_staly)
    {
        this.start = new Date();
        this.staly = czy_staly;
    }

    /**
     * Konstruktor klasy okreœlaj¹cy pocz¹tkowo zapisany dystans
     * @param dystans wartoœæ przechowywana w liczniku w momencie tworzenia obiektu
     */
    public Licznik(float dystans)
    {
        this.dystans = dystans;
        this.staly = true;
        this.start = new Date();
    }

    /**
     * Konstruktor klasy umorzliwiaj¹cy okreœlenie jego typu oraz startowego dystansu
     * @param dystans wartoœæ przechowywana w liczniku w momencie tworzenia obiektu
     * @param czy_staly definiuje, czy powsta³y licznik bêdzie mo¿liwy do zresetowania
     */
    public Licznik(float dystans, boolean czy_staly)
    {
        this.dystans = dystans;
        this.staly = czy_staly;
        this.start = new Date();
    }

    /**
     * Zeruje iloœæ przejechanych kilometrów oraz ustala now¹ datê pocz¹tku odczytu
     * @throws Exception wyj¹tek powoduje próba zresetowania licznika sta³ego lub ju¿ zresetowanego
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
     * Dodaje wartoœæ do licznika
     * @param dystans wartoœæ, która zostanie dodana
     * @throws UjemnaWartosc powoduje próba dodania wartoœci ujemnej
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
