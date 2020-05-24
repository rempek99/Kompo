package AutoApp.Model;

import java.util.Date;

/**
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Chwilowy_odczyt_predkosci {
    Date poczatek_odczytu;
    double predkoscPoczatek, predkoscKoniec;

    public Date getPoczatek_odczytu() {
        return poczatek_odczytu;
    }

    Chwilowy_odczyt_predkosci(double predkosc)
    {
        this.predkoscPoczatek = predkosc;
        this.poczatek_odczytu = new Date();
    }


    /**
     * Pozwala na odczyt przejechanego dystansu od ostatniego wywolania tej funkcji. Dystans jest liczony
     * dla ruchu jednostajnie przyspieszonego z poczatkowa predkoscia zapamiatana z ostaniego wywolania funkcji
     * oraz predkoscia koncowa podawana jako parametr.
     * @param aktualnaPredkosc Aktualna predkość pojazdu
     * @return Dystans pokonany od ostaniego wywolania tej funkcji
     */
    double przejechane(double aktualnaPredkosc)
    {
        predkoscKoniec=aktualnaPredkosc;
        Date now = new Date();
        double end = ((now.getTime()-poczatek_odczytu.getTime())/1000)*((predkoscPoczatek/7200)+(predkoscKoniec/7200));
        end/=100;
        predkoscPoczatek=predkoscKoniec;
        return end;
    }

}
