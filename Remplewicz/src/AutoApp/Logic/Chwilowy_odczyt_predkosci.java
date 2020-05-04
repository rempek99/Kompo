package AutoApp.Logic;

import java.util.Date;
//klasa pozwalajaca na
public class Chwilowy_odczyt_predkosci {
    Date poczatek_odczytu;
    float predkosc;

    public Date getPoczatek_odczytu() {
        return poczatek_odczytu;
    }

    Chwilowy_odczyt_predkosci(float predkosc)
    {
        this.predkosc = predkosc;
        this.poczatek_odczytu = new Date();
    }
    float przejechane()
    {
        Date now = new Date();
        float end = ((now.getTime()-poczatek_odczytu.getTime())/1000)*predkosc/3600;
        return end;
    }

}
