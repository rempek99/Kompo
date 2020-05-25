package AutoApp.Model;

/**
 * Wyj¹tek informuj¹cy o tym, ¿e silnik jest nieuruchomiony i nie jest mo¿liwe wykonanie dzia³ania
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
