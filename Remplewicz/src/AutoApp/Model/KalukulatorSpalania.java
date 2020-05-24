package AutoApp.Model;

import AutoApp.Data.RaportSpalania;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.*;

/**
 * Magazynuje informacje o spalaniu z ostatnich 5min pracy silnika i na ich podstawie oblicza średnie spalanie
 */
public class KalukulatorSpalania implements ActionListener {
    /**
     * Przechowuje poszczególne zapisy raportu spalania
     */
    private ArrayList<RaportSpalania> raport;
    private double stanLicznika;
    private Samochod samochod;
    private Timer timer;

    public KalukulatorSpalania(Samochod samochod) {
        this.samochod=samochod;
        stanLicznika=samochod.getLicznikGlowny().getDystans();
        raport=new ArrayList<RaportSpalania>();
        timer =new Timer(500,this);
        timer.start();
    }

    /**
     * Zwraca średnie spalanie w ostatnich 5 min jako ilość litrów na 100 kilometrów.
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
        return ret;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(samochod.isZaplon()) {
                double t1,t3;
                t1=samochod.getLicznikGlowny().getDystans()-stanLicznika;
                t3 = 0.02;
                if(samochod.isGaz()){
                    t3=t3+(double)(samochod.getMoc_silnika())/500;
                }
                raport.add(new RaportSpalania(t1,t3));
            }
        stanLicznika=samochod.getLicznikGlowny().getDystans();
    }
}
