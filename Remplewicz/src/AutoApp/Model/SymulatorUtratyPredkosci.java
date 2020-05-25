package AutoApp.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Symuluje utrat� pr�dko�ci przy toczeniu sie bez gazu i bez uruchomionego tempomatu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 * @see Timer
 * @see ActionListener
 */
class SymulatorUtratyPredkosci implements ActionListener
{
    /**
     * Timer odpowiedzialny za aktualizacj� stanu pr�dko�ci samochodu
     */
    private Timer timer;
    /**
     * Referencja do pojazdu, kt�ry obs�uguje
     */
    private Samochod samochod;

    /**
     * Konstruktor klasy
     * @param samochod Pojazd dla ktorego bedzie symulowana utrata predkosci
     */
    public SymulatorUtratyPredkosci(Samochod samochod) {
        timer=new Timer(100,this );
        timer.start();
        this.samochod=samochod;
    }
    /**
     * Cyklicznie wywo�ywana metoda kt�ra zmniejsza predko�� pojazdu gdy nie jest wci�niety gaz i gdy jest wy��czony tempomat
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!samochod.isGaz())
        {
            try {
                float tmp=samochod.getPredkosciomierz().getPredkosc();
                if (tmp < samochod.getPredkosciomierz().getMax_predkosc()*0.01)
                    samochod.getPredkosciomierz().zmniejsz_predkosc(20);
                else if((0.8+(tmp*0.0001))<=tmp) {
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
