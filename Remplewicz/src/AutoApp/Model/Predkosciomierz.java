package AutoApp.Model;


public class Predkosciomierz implements Naped, Resetowalny {
    int max_predkosc;
    float predkosc;//  km/h

    public int getMax_predkosc() {
        return max_predkosc;
    }

    public Predkosciomierz(int max_predkosc)
    {
        this.max_predkosc = max_predkosc;
        predkosc = 0;
    }

    public float getPredkosc() {
        return predkosc;
    }

    @Override
    public String toString()
    {
        String output="";
        output = "Predkosc: "+predkosc+"km/h";
        return output;
    }
    @Override
    public void zwieksz_predkosc(float wartosc) throws UjemnaWartosc {
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        if(wartosc+predkosc>max_predkosc)
            this.predkosc = max_predkosc;
        else
            this.predkosc += wartosc;
    }
    @Override
    public void zmniejsz_predkosc(float wartosc) throws UjemnaWartosc
    {
        if(wartosc<0)
            throw new UjemnaWartosc(wartosc);
        if(predkosc-wartosc<0)
            this.predkosc = 0;
        else
            this.predkosc -= wartosc;
    }

    @Override
    public void reset() {
        predkosc = 0;
    }


}
