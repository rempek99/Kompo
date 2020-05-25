package AutoApp.Data;

/**
 * Wyjątek informujący o braku możliwości zresetowania licznika typu stałego
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class LicznikStaly extends Exception {
    LicznikStaly()
    {
        super("Nie mozna zresetowac stalego licznika");
    }
}
