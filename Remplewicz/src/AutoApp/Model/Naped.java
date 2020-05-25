package AutoApp.Model;

/**
 * Interfefs implementujący metody sterujące prędkością pojazdu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public interface Naped {
    int predkosc = 0;
    int max_predkosc = 0;
    void zwieksz_predkosc(float wartosc) throws UjemnaWartosc;
    void zmniejsz_predkosc(float wartosc) throws UjemnaWartosc;
}
