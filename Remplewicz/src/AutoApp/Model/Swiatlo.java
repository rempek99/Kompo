package AutoApp.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Reprezentuje instancje swiateł, umożliwia 3 akcje na nim- włącz,wyłącz i migaj
 * @see Obsluga_Swiatla
 * @author Dawid Jakubik
 * @Author Arkadiusz Remplewicz
 */
public class Swiatlo implements Obsluga_Swiatla{
    boolean wlaczone;
    Color barwa;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    /**
     * Używany do symulowania migania świateł
     */
    Timer timer = new Timer(500,new TimerListener());
    int tempBlinks = 0;

    public Swiatlo(Color barwa)
    {
        this.wlaczone = false;
        this.barwa = barwa;
    }


    public Color getBarwa() {
        return barwa;
    }

    public void setBarwa(Color barwa) {
        this.barwa = barwa;
    }
    @Override
    public void wylacz() {
        String oldValue = "true";
        this.wlaczone = false;
        String newValue = "false";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }

    @Override
    public void wlacz() {
        String oldValue = "false";
        this.wlaczone = true;
        String newValue = "true";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }

    public boolean isWlaczone() {
        return wlaczone;
    }

    @Override
    public void migaj() {
        tempBlinks = 0;
        timer.start();
    }

    /**
     * Umożwliwia dodanie obiektu który nasłuchuje zmiany stanu świateł
     * @param Listener Obiekt nasłuchujacy zdarzenia
     */
    public void addSwiatloListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }

    /**
     * Używany do symulacji migania świateł
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
}
