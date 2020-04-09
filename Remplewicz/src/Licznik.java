public class Licznik {
    float dystans; //metry
    boolean staly;

    public Licznik()
    {
        this.dystans = 0;
        this.staly = true;
    }
    public Licznik(boolean czy_staly)
    {
        this.staly = czy_staly;
    }
    public Licznik(float dystans)
    {
        this.dystans = dystans;
        this.staly = true;
    }
    public Licznik(float dystans, boolean czy_staly)
    {
        this.dystans = dystans;
        this.staly = czy_staly;
    }
    public void reset() throws Exception
    {
        if(this.dystans == 0)
            throw new Exception("Licznik zostal juz zresetowany");
        if(this.staly == true)
            throw new LicznikStaly();
        this.dystans = 0;
    }
    public void dodaj(float dystans) throws UjemnaWartosc
    {
        if(dystans<0)
            throw new UjemnaWartosc(dystans);
        this.dystans += dystans;
    }
    public float getDystans() {
        return dystans;
    }
    public String toString()
    {
        String output = "";
        output +="Dystans: " + dystans +"m";
        return output;
    }
}
