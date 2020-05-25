package AutoApp.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Reprezentuje instancje swiate�, umo�liwia 3 akcje na nim- w��cz,wy��cz i migaj
 * @see Obsluga_Swiatla
 * @author Dawid Jakubik
 * @author Arkadiusz Remplewicz
 */
public class Swiatlo implements Obsluga_Swiatla{
    /**
     * Warto�� logiczna okre�laj�ca stan �wia�a
     */
    boolean wlaczone;
    /**
     * Warto�� okre�laj�ca barw� �wiat�a
     * @see Color
     */
    Color barwa;
    /**
     * Obiekt umo�liwiaj�cy kontrolowanie stanu licznika i powiadamiane o zmianu jego stanu
     * @see PropertyChangeSupport
     * @see PropertyChangeListener
     */
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    /**
     * U�ywany do symulowania migania �wiate�
     */
    Timer timer = new Timer(500,new TimerListener());
    /**
     * Warto�� tymczasowa, odpowiadaj�ca za ilo�� migni�� swiat�a
     */
    int tempBlinks;

    /**
     * Konstruktor klasy
     * @param barwa okre�la barw� �wiat�a
     */
    public Swiatlo(Color barwa)
    {
        this.wlaczone = false;
        this.barwa = barwa;
    }

    /**
     * Zmienia stan �wiat�a na wy��czone
     */
    @Override
    public void wylacz() {
        String oldValue = "true";
        this.wlaczone = false;
        String newValue = "false";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }
    /**
     * Zmienia stan �wiat�a na w��czone
     */
    @Override
    public void wlacz() {
        String oldValue = "false";
        this.wlaczone = true;
        String newValue = "true";
        pcs.firePropertyChange("wlaczone",oldValue,newValue);
    }

    /**
     * Steruje stanem �wiat�a, inicjuje jego miganie
     */
    @Override
    public void migaj() {
        tempBlinks = 0;
        timer.start();
    }
    /**
     * Umo�wliwia dodanie obiektu kt�ry nas�uchuje zmiany stanu �wiate�
     * @param Listener Obiekt nas�uchujacy zdarzenia
     */
    public void addSwiatloListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
    /**
     * U�ywany do symulacji migania �wiate�
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
