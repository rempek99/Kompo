package AutoApp.Model;

import java.util.Date;

/**
 * Klasa s³u¿¹ca do obliczenia przejechanego dystansu w jednostce czasu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Chwilowy_odczyt_predkosci {
    /**
     * Data wskazuj¹ca na pocz¹tek zarejestrowanego odczytu
     */
    private Date poczatek_odczytu;
    /**
     * Wartoœci prêdkoœci zarejestrowane na pocz¹tku oraz na koñcu odczytu
     */
    private double predkoscPoczatek, predkoscKoniec;

    public Date getPoczatek_odczytu() {
        return poczatek_odczytu;
    }

    /**
     * Konstruktor klasy
     * @param predkosc prêdkoœæ, z jak¹ porusza³ siê pojazd w momencie rozpoczêcia odczytu
     */
    Chwilowy_odczyt_predkosci(double predkosc)
    {
        this.predkoscPoczatek = predkosc;
        this.poczatek_odczytu = new Date();
    }


    /**
     * Pozwala na odczyt przejechanego dystansu od ostatniego wywolania tej funkcji. Dystans jest liczony
     * dla ruchu jednostajnie przyspieszonego z poczatkowa predkoscia zapamiatana z ostaniego wywolania funkcji
     * oraz predkoscia koncowa podawana jako parametr.
     * @param aktualnaPredkosc Aktualna predkoœæ pojazdu
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
