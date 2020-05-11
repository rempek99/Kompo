package AutoApp.Model;

import java.util.Date;

public class Chwilowy_odczyt_predkosci {
    Date poczatek_odczytu;
    double predkosc;

    public Date getPoczatek_odczytu() {
        return poczatek_odczytu;
    }

    Chwilowy_odczyt_predkosci(double predkosc)
    {
        this.predkosc = predkosc;
        this.poczatek_odczytu = new Date();
    }

    public void setPredkosc(double predkosc) {
        this.predkosc = predkosc;
    }

    double przejechane()
    {
        Date now = new Date();
        double end = ((now.getTime()-poczatek_odczytu.getTime())/1000)*predkosc/3600;
        return end;
    }

}
