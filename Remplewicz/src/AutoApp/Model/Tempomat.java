package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

public class Tempomat implements ActionListener {
    private boolean wlaczony;
    private Timer timer;

    /**
     * Taką prędkośc bedzię utrzymywał tempomat
     */
    private double predkosc;
    private Samochod samochod;

    public Tempomat(Samochod samochod) {
        this.samochod = samochod;
        wlaczony=false;
        timer=new Timer(10,this);

    }

    public boolean isWlaczony() {
        return wlaczony;
    }

    /**
     * Służy do uruchomienia tempomatu
     * @param predkosc Taka prędkośc będzie się starał utrzymać tempomat
     */
    public void wlacz(double predkosc) {
        this.wlaczony = true;
        timer.start();
        this.predkosc=predkosc;
    }

    /**
     * Służy do wyłączenia tempomatu
     */
    public void wylacz()
    {
        timer.stop();
        wlaczony=false;
    }

    /**
     * Zatrzymuje pojazd i wyłącza tempomat (np gdy pojawi sie jakas przeszkoda na drodze).
     */
    public void stop()
    {
        wlaczony=false;
        timer.stop();
        while(samochod.getPredkosciomierz().getPredkosc()>0) {
            samochod.hamulec();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(abs(predkosc-samochod.getPredkosciomierz().getPredkosc())>(samochod.getMoc_silnika() * 0.01)-(samochod.getPredkosciomierz().getPredkosc()*0.007)) {
                if (samochod.getPredkosciomierz().getPredkosc() < predkosc) {
                    samochod.gaz();
                } else if (samochod.getPredkosciomierz().getPredkosc() > predkosc) {
                    samochod.hamulec();
                }
            }
        }
    }

