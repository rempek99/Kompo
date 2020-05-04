package AutoApp.Logic;

public class UjemnaWartosc extends Exception{
    UjemnaWartosc(int wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    public UjemnaWartosc(float wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    UjemnaWartosc()
    {
        super("Podales niepoprawna wartosc: ");
    }
}
