package AutoApp.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * Przechowuje podstawowe informacje o predkościomierzu i udostępnia możliwość interakcji z nim
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Predkosciomierz implements Naped, Resetowalny{
    /**
     * informacja o maksymalnej możliwej prędkości  w km/h
     */
    private int max_predkosc;
    /**
     * Aktualna prędkośc w km/h
     */
    private float predkosc;//  km/h
    /**
           * Obiekt umożliwiający kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
           * @see PropertyChangeSupport
           * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs;

    public int getMax_predkosc() {
        return max_predkosc;
    }

    /**
     * Konstruktor klasy
     * @param max_predkosc zakres prędkościomierza
     */
    public Predkosciomierz(int max_predkosc)
    {
        this.pcs = new PropertyChangeSupport(this);
        this.max_predkosc = max_predkosc;
        this.predkosc = 0;
    }

    public void setMax_predkosc(int max_predkosc) {
        this.max_predkosc = max_predkosc;
    }

    public float getPredkosc() {
        return predkosc;
    }

    @Override
    public String toString()
    {
        String output="";
        output = "Predkosc: "+predkosc+"km/h";
        return output;
    }

    /**
     * Umożliwia zwiększenie prędkości wskazywanej przez prędkościomierz
     * @param wartosc O tę wartość zostanie zwiększona aktualna prędkość
     * @throws UjemnaWartosc rzucany w przypadku, kiedy następuje próba dodania ujemnej wartości do prędkościomierza
     */
    @Override
    public void zwieksz_predkosc(float wartosc) throws UjemnaWartosc {
        String oldValue = Float.toString(getPredkosc());
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        if(wartosc+predkosc>max_predkosc)
            this.predkosc = max_predkosc;
        else
            this.predkosc += wartosc;

        String newValue = Float.toString(getPredkosc());
        pcs.firePropertyChange("predkosc",oldValue,newValue);
    }

    /**
     * Umożliwia zmniejszenie predkości wskazywanej przez predkosciomierz
     * @param wartosc O tę wartośc zostanie zmniejszona aktualna prędkość
     * @throws UjemnaWartosc rzucany w przypadku, kiedy następuje próba dodania ujemnej wartości do prędkościomierza
     */
    @Override
    public void zmniejsz_predkosc(float wartosc) throws UjemnaWartosc
    {
        String oldValue = Float.toString(getPredkosc());
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        if(predkosc-wartosc<0)
            this.predkosc = 0;
        else
            this.predkosc -= wartosc;
        String newValue = Float.toString(getPredkosc());
        pcs.firePropertyChange("predkosc",oldValue,newValue);
    }

    /**
     * Pozwala na zresetowanie predkościomierza - ustawia wskazywaną przez niego prędkość na 0
     */
    @Override
    public void reset() {
        String oldValue = Float.toString(getPredkosc());
        predkosc = 0;
        String newValue = Float.toString(getPredkosc());
        pcs.firePropertyChange("predkosc",oldValue,newValue);
    }

    /**
     * Umożliwia nasłuchiwanie zmiany stanu prędkościomierza
     * @param Listener listener, który odbiera zdarzenia
     */
    public void addPredkoscListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
