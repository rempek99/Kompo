package AutoApp.Data;

/**
 * Wyj¹tek informuj¹cy o braku mo¿liwoœci zresetowania licznika typu sta³ego
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class LicznikStaly extends Exception {
    /**
     * Konstruktor klasy
     */
    LicznikStaly()
    {
        super("Nie mozna zresetowac stalego licznika");
    }
}
