package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Symuluje utratę prędkości przy toczeniu sie bez gazu i bez uruchomionego tempomatu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 * @see Timer
 * @see ActionListener
 */
class SymulatorUtratyPredkosci implements ActionListener
{
    private Timer timer;
    private Samochod samochod;

    /**
     * @param samochod Pojazd dla ktorego bedzie symulowana utrata predkosci
     */
    public SymulatorUtratyPredkosci(Samochod samochod) {
        timer=new Timer(100,this );
        timer.start();
        this.samochod=samochod;
    }

    /**
     * Cyklicznie wywoływana metoda która zmniejsza predkość pojazdu gdy nie jest wciśniety gaz i gdy jest wyłączony tempomat

     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!samochod.isGaz())
        {
            try {
                float tmp=samochod.getPredkosciomierz().getPredkosc();
                if((0.8+(tmp*0.0001))<=tmp) {
                    samochod.getPredkosciomierz().zmniejsz_predkosc((float) (0.8+(tmp*0.0001)));
                }
                else{
                    samochod.getPredkosciomierz().zmniejsz_predkosc(tmp);
                }
            } catch (UjemnaWartosc ujemnaWartosc) {
                ujemnaWartosc.printStackTrace();
            }
        }
    }
}
