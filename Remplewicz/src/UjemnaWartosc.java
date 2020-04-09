public class UjemnaWartosc extends Exception{
    UjemnaWartosc(int wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    UjemnaWartosc(float wartosc)
    {
        super("Podales niepoprawna wartosc: "+ wartosc);
    }
    UjemnaWartosc()
    {
        super("Podales niepoprawna wartosc: ");
    }
}
