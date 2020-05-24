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
    public AktualizatorLicznikow( Chwilowy_odczyt_predkosci temp, Samochod samochod){
        this.temp = temp;
        this.samochod=samochod;
        timer = new Timer(500,this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        double tmp=temp.przejechane(samochod.predkosciomierz1.getPredkosc());
        try {
            samochod.licznik1.dodaj(tmp);
            samochod.licznikGlowny.dodaj(tmp);
        } catch (UjemnaWartosc ujemnaWartosc) {
            ujemnaWartosc.printStackTrace();
        }
    }
}
