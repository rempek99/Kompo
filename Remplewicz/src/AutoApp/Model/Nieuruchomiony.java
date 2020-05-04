package AutoApp.Model;

public class Nieuruchomiony extends RuntimeException {
    Nieuruchomiony() {
        super("Silnik nie zostal uruchomiony");
    }
}
