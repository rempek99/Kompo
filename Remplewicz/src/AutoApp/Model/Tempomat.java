package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

/**
 * Klasa odpowiedzialna za funkcjonalnoœæ utrzymywania ustalonej prêdkoœci pojazdu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Tempomat implements ActionListener {
    /**
     * Wartoœæ logiczna okreœlaj¹ca stan tempomatu
     */
    private boolean wlaczony;
    /**
     * Timer odpowiedzialny za cykliczne kontolowanie prêdkoœci pojazdu
     */
    private Timer timer;
    /**
     * Tak¹ prêdkoœc bedziê utrzymywa³ tempomat
     */
    private double predkosc;
    /**
     * Referencja do pojazdu, który obs³uguje tempomat
     */
    private Samochod samochod;

    /**
     * Konstruktor klasy
     * @param samochod referencja do pojazdu, który obs³uguje tempomat
     */
    public Tempomat(Samochod samochod) {
        this.samochod = samochod;
        wlaczony=false;
        timer=new Timer(10,this);
    }
    /**
     * S³u¿y do uruchomienia tempomatu
     * @param predkosc Taka prêdkoœc bêdzie siê stara³ utrzymaæ tempomat
     */
    public void wlacz(double predkosc) {
        this.wlaczony = true;
        timer.start();
        this.predkosc=predkosc;
    }
    /**
     * S³u¿y do wy³¹czenia tempomatu
     */
    public void wylacz()
    {
        timer.stop();
        wlaczony=false;
    }
    /**
     * Zatrzymuje pojazd i wy³¹cza tempomat (np gdy pojawi sie jakas przeszkoda na drodze).
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
     * Aktualizowany z ka¿d¹ jednost¹ czasu ustalon¹ przez timer, odpowiada za utrzymanie zadeklarowanej prêdkoœci
     * @param e obs³ugiwane zdarzenie, wywo³ane przez timer
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

