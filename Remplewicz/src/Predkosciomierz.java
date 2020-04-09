public class Predkosciomierz {
    int max_predkosc;
    int predkosc;//  km/h
    public Predkosciomierz(int max_predkosc)
    {
        this.max_predkosc = max_predkosc;
        predkosc = 0;
    }
    public String toString()
    {
        String output="";
        output = "Predkosc: "+predkosc+"km/h";
        return output;
    }
    public void zwieksz(int wartosc) throws UjemnaWartosc
    {
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        this.predkosc += wartosc;
    }
    public void zmniejsz(int wartosc) throws UjemnaWartosc
    {
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        this.predkosc -= wartosc;
    }

}
