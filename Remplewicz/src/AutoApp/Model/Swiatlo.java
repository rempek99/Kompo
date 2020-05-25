package AutoApp.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Reprezentuje instancje swiate³, umo¿liwia 3 akcje na nim- w³¹cz,wy³¹cz i migaj
 * @see Obsluga_Swiatla
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Swiatlo implements Obsluga_Swiatla{
    /**
     * Wartoœæ logiczna okreœlaj¹ca stan œwia³a
     */
    boolean wlaczone;
    /**
     * Wartoœæ okreœlaj¹ca barwê œwiat³a
     * @see Color
     */
    Color barwa;
    /**
     * Obiekt umo¿liwiaj¹cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
     * @see PropertyChangeSupport
     * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    /**
     * U¿ywany do symulowania migania œwiate³
     */
    Timer timer = new Timer(500,new TimerListener());
    /**
     * Wartoœæ tymczasowa, odpowiadaj¹ca za iloœæ migniêæ swiat³a
     */
    int tempBlinks;

    /**
     * Konstruktor klasy
     * @param barwa okreœla barwê œwiat³a
     */
    public Swiatlo(Color barwa)
    {
        this.wlaczone = false;
        this.barwa = barwa;
    }

    /**
     * Zmienia stan œwiat³a na wy³¹czone
     */
    @Override
    public void wylacz() {
        String oldValue = "true";
        this.wlaczone = false;
        String newValue = "false";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }
    /**
     * Zmienia stan œwiat³a na w³¹czone
     */
    @Override
    public void wlacz() {
        String oldValue = "false";
        this.wlaczone = true;
        String newValue = "true";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }

    /**
     * Steruje stanem œwiat³a, inicjuje jego miganie
     */
    @Override
    public void migaj() {
        tempBlinks = 0;
        timer.start();
    }
    /**
     * Umo¿wliwia dodanie obiektu który nas³uchuje zmiany stanu œwiate³
     * @param Listener Obiekt nas³uchujacy zdarzenia
     */
    public void addSwiatloListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
    /**
     * U¿ywany do symulacji migania œwiate³
     */
    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                if(timer.getDelay()>100)
                {
                    if(isWlaczone())
                        wylacz();
                    else
                        wlacz();
                    tempBlinks ++;
                    timer.restart();
                    if(tempBlinks>5)
                        timer.stop();
                }
        }
    }

    public Color getBarwa() {return barwa;}
    public void setBarwa(Color barwa) {this.barwa = barwa;}
    public boolean isWlaczone() {return wlaczone;}
}
