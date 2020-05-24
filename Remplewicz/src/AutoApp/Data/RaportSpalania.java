package AutoApp.Data;

import java.util.Date;

/**
 * Obiekt reprezentujacy pojedynczy, chwilowy pomiar spalania auta
 */
public class RaportSpalania {
    /**
     * Informacja o czasie zapisania rekordu
     */
    private Date data;
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

    public RaportSpalania(double dystans, double spalono) {
        this.dystans = dystans;
        this.spalono = spalono;
        data=new Date();
    }
}
