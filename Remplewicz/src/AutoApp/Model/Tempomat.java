package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

/**
 * Klasa odpowiedzialna za funkcjonalno�� utrzymywania ustalonej pr�dko�ci pojazdu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Tempomat implements ActionListener {
    /**
     * Warto�� logiczna okre�laj�ca stan tempomatu
     */
    private boolean wlaczony;
    /**
     * Timer odpowiedzialny za cykliczne kontolowanie pr�dko�ci pojazdu
     */
    private Timer timer;
    /**
     * Tak� pr�dko�c bedzi� utrzymywa� tempomat
     */
    private double predkosc;
    /**
     * Referencja do pojazdu, kt�ry obs�uguje tempomat
     */
    private Samochod samochod;

    /**
     * Konstruktor klasy
     * @param samochod referencja do pojazdu, kt�ry obs�uguje tempomat
     */
    public Tempomat(Samochod samochod) {
        this.samochod = samochod;
        wlaczony=false;
        timer=new Timer(10,this);
    }
    /**
     * S�u�y do uruchomienia tempomatu
     * @param predkosc Taka pr�dko�c b�dzie si� stara� utrzyma� tempomat
     */
    public void wlacz(double predkosc) {
        this.wlaczony = true;
        timer.start();
        this.predkosc=predkosc;
    }
    /**
     * S�u�y do wy��czenia tempomatu
     */
    public void wylacz()
    {
        timer.stop();
        wlaczony=false;
    }
    /**
     * Zatrzymuje pojazd i wy��cza tempomat (np gdy pojawi sie jakas przeszkoda na drodze).
     */
    public void stop()
    {
        wlaczony=false;
        timer.stop();
        while(samochod.getPredkosciomierz().getPredkosc()>0) {
            samochod.hamulec();
        }
    }

    /**
     * Aktualizowany z ka�d� jednost� czasu ustalon� przez timer, odpowiada za utrzymanie zadeklarowanej pr�dko�ci
     * @param e obs�ugiwane zdarzenie, wywo�ane przez timer
     */
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

    public boolean isWlaczony() {return wlaczony;}
    }

