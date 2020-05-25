package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Cyklicznie, co 500 milisekund dodaje przejechany przez ten czas dystans do przebiegu Samochodu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class AktualizatorLicznikow implements ActionListener {
    private Timer timer;
    private Chwilowy_odczyt_predkosci temp;
    private Samochod samochod;

    /**
     * Konstruktor Klasy
     * @param temp chwilowe odczyty prędkości rejestrowane przez pojazd
     * @param samochod referencja na pojazd, który obsługuje
     */
    public AktualizatorLicznikow( Chwilowy_odczyt_predkosci temp, Samochod samochod){
        this.temp = temp;
        this.samochod=samochod;
        timer = new Timer(500,this);
        timer.start();
    }

    /**
     * Metoda wywoływana okresowo, zgodnie z parametrem Timera, obsługuje zdarzenie, aktualizuje liczniki
     * @param e zarejestrowane zdarzenie
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        double tmp=temp.przejechane(samochod.getPredkosciomierz().getPredkosc());
        try {
            samochod.getLicznikPodrozy().dodaj(tmp);
            samochod.getLicznikGlowny().dodaj(tmp);
            samochod.getLicznikUzytkownika().dodaj(tmp);
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
    }
}
