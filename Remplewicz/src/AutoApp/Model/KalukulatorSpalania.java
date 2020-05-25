package AutoApp.Model;

import AutoApp.Data.RaportSpalania;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.*;

/**
 * Magazynuje informacje o spalaniu z ostatnich 5min pracy silnika i na ich podstawie oblicza �rednie spalanie
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class KalukulatorSpalania implements ActionListener {
    /**
     * Przechowuje poszczeg�lne zapisy raportu spalania
     */
    private ArrayList<RaportSpalania> raport;
    /**
     * Przechowuje warto�� stanu licznika
     */
    private double stanLicznika;
    /**
     * Referencja na pojazd, kt�ry obs�uguje
     */
    private Samochod samochod;
    /**
     * Timer umo�liwiaj�cy cykliczne wykonywanie odczyt�w
     */
    private Timer timer;

    /**
     * Konstruktor klasy
     * @param samochod referencja na pojazd, kt�ry obs�uguje
     */
    public KalukulatorSpalania(Samochod samochod) {
        this.samochod=samochod;
        stanLicznika=samochod.getLicznikGlowny().getDystans();
        raport=new ArrayList<RaportSpalania>();
        timer =new Timer(500,this);
        timer.start();
    }

    /**
     * Zwraca �rednie spalanie na podstawie 5 ostatnich minut jako ilo�� litr�w na 100 kilometr�w.
     * @return Srednie spalanie w ostanich 5 min
     */
    public double  getSrednieSpalanie(){
        double dyst=0,spal=0,ret;
        for(RaportSpalania r :raport)
        {
            if((new Date().getTime()-r.getData().getTime())>300000){
                raport.remove(r);
            }else {
                dyst+=r.getDystans();
                spal+=r.getSpalono();
            }
        }
        spal+=samochod.getPredkosciomierz().getPredkosc()*0.02;
        ret=((samochod.getMoc_silnika()*0.04)+(log10((spal*2.5)/dyst)*2));
        if((spal==0)||(dyst==0)||(ret>60)) {
            return 0;
        }
        return ret/2;
    }

    /**
     * Metoda wykonywana cyklicznie, obs�uguje zdarzenie up�ywu jednostki czasu, rejestruje spalone paliwo, generuje raporty
     * @param e zdarzenie wywo�ane przez timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(samochod.isZaplon()) {
                double t1,t2,t3;
                t2=samochod.getPredkosciomierz().getPredkosc();
                t1=samochod.getLicznikGlowny().getDystans()-stanLicznika;
                t3 = 0.02;
                t3+=pow(t2,5)*0.008;
                if(samochod.isGaz()){

                    t3=t3+(double)(samochod.getMoc_silnika())/500;
                }
                raport.add(new RaportSpalania(t1,t3));
            }
        stanLicznika=samochod.getLicznikGlowny().getDystans();
    }
}
