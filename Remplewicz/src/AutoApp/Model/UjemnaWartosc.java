package AutoApp.Model;

/**
 * Wyj�tek informuj�cy o pr�bie podania warto�ci ujemnej, co jest niedozwolone
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class UjemnaWartosc extends Exception{
    /**
     * Konstruktor klasy
     * @param wartosc warto��, kt�ra zosta�a podana
     */
    UjemnaWartosc(int wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    /**
     * Konstruktor klasy
     * @param wartosc warto��, kt�ra zosta�a podana
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
