package AutoApp.Model;

/**
 * Wyj�tek informuj�cy o tym, �e silnik jest nieuruchomiony i nie jest mo�liwe wykonanie dzia�ania
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Nieuruchomiony extends RuntimeException {
    /**
     * Konstruktor klasy
     */
    Nieuruchomiony() {
        super("Silnik nie zostal uruchomiony");
    }
}
