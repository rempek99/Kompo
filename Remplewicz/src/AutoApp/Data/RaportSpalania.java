package AutoApp.Data;

import java.util.Date;

/**
 * Obiekt reprezentujacy pojedynczy, chwilowy pomiar spalania auta
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class RaportSpalania {
    /**
     * Informacja o czasie zapisania rekordu
     */
    private Date data;
    /**
     * Wartości określające przejechany dystans w kilometrach i spalone litry paliwa
     */
    private double dystans,spalono;

    public Date getData() {
        return data;
    }

    public double getDystans() {
        return dystans;
    }

    public double getSpalono() {
        return spalono;
    }

    /**
     * Konstruktor klasy
     * @param dystans Przejechany dystans wyrażony w kilometrach
     * @param spalono Spalone litry paliwa
     */
    public RaportSpalania(double dystans, double spalono) {
        this.dystans = dystans;
        this.spalono = spalono;
        data=new Date();
    }
}
