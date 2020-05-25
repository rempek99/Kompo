package AutoApp.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * Przechowuje podstawowe informacje o predkoœciomierzu i udostêpnia mo¿liwoœæ interakcji z nim
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Predkosciomierz implements Naped, Resetowalny{
    /**
     * informacja o maksymalnej mo¿liwej prêdkoœci  w km/h
     */
    private int max_predkosc;
    /**
     * Aktualna prêdkoœc w km/h
     */
    private float predkosc;//  km/h
    /**
           * Obiekt umo¿liwiaj¹cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
           * @see PropertyChangeSupport
           * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs;

    public int getMax_predkosc() {
        return max_predkosc;
    }

    /**
     * Konstruktor klasy
     * @param max_predkosc zakres prêdkoœciomierza
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
     * Umo¿liwia zwiêkszenie prêdkoœci wskazywanej przez prêdkoœciomierz
     * @param wartosc O tê wartoœæ zostanie zwiêkszona aktualna prêdkoœæ
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nastêpuje próba dodania ujemnej wartoœci do prêdkoœciomierza
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
     * Umo¿liwia zmniejszenie predkoœci wskazywanej przez predkosciomierz
     * @param wartosc O tê wartoœc zostanie zmniejszona aktualna prêdkoœæ
     * @throws UjemnaWartosc rzucany w przypadku, kiedy nastêpuje próba dodania ujemnej wartoœci do prêdkoœciomierza
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
     * Pozwala na zresetowanie predkoœciomierza - ustawia wskazywan¹ przez niego prêdkoœæ na 0
     */
    @Override
    public void reset() {
        String oldValue = Float.toString(getPredkosc());
        predkosc = 0;
        String newValue = Float.toString(getPredkosc());
        pcs.firePropertyChange("predkosc",oldValue,newValue);
    }

    /**
     * Umo¿liwia nas³uchiwanie zmiany stanu prêdkoœciomierza
     * @param Listener listener, który odbiera zdarzenia
     */
    public void addPredkoscListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
}
