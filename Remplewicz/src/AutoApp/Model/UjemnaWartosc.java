package AutoApp.Model;

public class UjemnaWartosc extends Exception{
    UjemnaWartosc(int wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    public UjemnaWartosc(double wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    UjemnaWartosc()
    {
        super("Podales niepoprawna wartosc: ");
    }
}
