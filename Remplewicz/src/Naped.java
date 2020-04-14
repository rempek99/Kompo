public interface Naped {
    int predkosc = 0;
    int max_predkosc = 0;
    void zwieksz_predkosc(float wartosc) throws UjemnaWartosc;
    void zmniejsz_predkosc(float wartosc) throws UjemnaWartosc;
}
