package AutoApp.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * Przechowuje podstawowe informacje o predko�ciomierzu i udost�pnia mo�liwo�� interakcji z nim
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Predkosciomierz implements Naped, Resetowalny{
    /**
     * informacja o maksymalnej mo�liwej pr�dko�ci  w km/h
     */
    private int max_predkosc;
    /**
     * Aktualna pr�dko�c w km/h
     */
    private float predkosc;//  km/h
    /**
           * Obiekt umo�liwiaj�cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
           * @see PropertyChangeSupport
           * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs;

    public int getMax_predkosc() {
        return max_predkosc;
    }

    /**
     * Konstruktor klasy
     * @param max_predkosc zakres pr�dko�ciomierza
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
     * Umo�liwia zwi�kszenie pr�dko�ci wskazywanej przez pr�dko�ciomierz
     * @param wartosc O t� warto�� zostanie zwi�kszona aktualna pr�dko��
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nast�puje pr�ba dodania ujemnej warto�ci do pr�dko�ciomierza
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
     * Umo�liwia zmniejszenie predko�ci wskazywanej przez predkosciomierz
     * @param wartosc O t� warto�c zostanie zmniejszona aktualna pr�dko��
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nast�puje pr�ba dodania ujemnej warto�ci do pr�dko�ciomierza
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
     * Pozwala na zresetowanie predko�ciomierza - ustawia wskazywan� przez niego pr�dko�� na 0
     */
    @Override
    public void reset() {
        String oldValue = Float.toString(getPredkosc());
        predkosc = 0;
        String newValue = Float.toString(getPredkosc());
        pcs.firePropertyChange("predkosc",oldValue,newValue);
    }

    /**
     * Umo�liwia nas�uchiwanie zmiany stanu pr�dko�ciomierza
     * @param Listener listener, kt�ry odbiera zdarzenia
     */
    public void addPredkoscListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
