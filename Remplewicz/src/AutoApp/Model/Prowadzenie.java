package AutoApp.Model;

/**
 * Interfejs umo¿liwiaj¹cy implementacjê metod odpowiadaj¹cych za prowadzenie pojazdu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public interface Prowadzenie {
    void gaz() ;
    void hamulec();
    void skret(boolean prawo);
    void uruchom_silnik();
    void zgas_silnik();
}
