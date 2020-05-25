package AutoApp.Data;

/**
 * Wyj�tek informuj�cy o braku mo�liwo�ci zresetowania licznika typu sta�ego
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
