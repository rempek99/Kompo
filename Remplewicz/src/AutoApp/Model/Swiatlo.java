package AutoApp.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Swiatlo implements Obsluga_Swiatla{
    boolean wlaczone;
    Color barwa;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
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
    public void addSwiatloListener(PropertyChangeListener Listener)
    {
        pcs.addPropertyChangeListener(Listener);
    }
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
