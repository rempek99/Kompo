package AutoApp.Model;

/**
 * Wyj¹tek informuj¹cy o próbie podania wartoœci ujemnej, co jest niedozwolone
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class UjemnaWartosc extends Exception{
    /**
     * Konstruktor klasy
     * @param wartosc wartoœæ, która zosta³a podana
     */
    UjemnaWartosc(int wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    /**
     * Konstruktor klasy
     * @param wartosc wartoœæ, która zosta³a podana
     */
    public UjemnaWartosc(double wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    /**
     * Konstruktor klasy
     */
    UjemnaWartosc()
    {
        super("Podales niepoprawna wartosc: ");
    }
}
