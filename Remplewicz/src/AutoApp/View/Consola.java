package AutoApp.View;

import AutoApp.Model.Samochod;

import java.util.Scanner;

/**
 * Klasa reprezentuj¹ca widok konsolowy
 */
public class Consola {
    /**
     * Referencja na samochód, który bêdzie obs³ugiwany przez konsolê
     */
    private Samochod samochod;
    /**
     * Obiekt umo¿liwiaj¹cy zczytywanie wprowadzanych danych do konsoli
     * @see Scanner
     */
    private Scanner scan;

    /**
     * Konstruktor klasy
     * @param samochod  Referencja na samochód, który bêdzie obs³ugiwany przez konsolê
     */
    public Consola(Samochod samochod) {
        this.samochod = samochod;
        scan=new Scanner(System.in);
    }

    /**
     * Wyœwietlanie informacji o stanie samochodu
     */
    public void info()
    {
        System.out.println("------------------------------");
        System.out.println(samochod);
    }

    /**
     * Inicjalizacja dzia³ania menu konsolowego
     */
    public void start()
    {
        int x=0;
        while(x==0)
        {
            info();
            x=akcja();
        }

    }

    /**
     * Obs³uga zdarzeñ, na podstawie danych wprowadzonych przez u¿ytkownika
     * @return 0 - kontynuuje dzia³anie programu
     */
    public int akcja()
    {
        scan=new Scanner(System.in);
        String tmp="";
        System.out.println("1-wyjscie\n2-zmiana predkosci\n3-reset przebiegu\n4-Uruchom/zgas silnik\n5-obsluga swiatel\n6-odswiez informacje\nWybor:");
        tmp=scan.nextLine();
        switch (tmp){
            case"1":
            {
                return 1;
            }
            case"2":
            {
                try {
                    samochod.setGaz(true);
                    String tmp2 = "";
                    int ile;
                    System.out.print("1-Gaz\n2-Hamulec\nWybor:");
                    tmp2 = scan.nextLine();
                    System.out.print("\n Ile razy wywolac metode hamowania/przyspieszania?: ");
                    scan.reset();
                    ile = scan.nextInt();
                    switch (tmp2) {
                        case "1": {
                            for (int i = ile; i > 0; i--) {
                                samochod.gaz();
                            }
                            break;
                        }
                        case "2": {
                            for (int i = (int) ile; i > 0; i--) {
                                samochod.hamulec();
                            }
                            break;
                        }
                        default: {
                            System.out.println("Bledne dane!");
                        }
                    }
                    samochod.setGaz(false);
                }catch(Exception exc)
                {
                    System.out.println(exc.toString());
                }
                break;
            }
            case"3":
            {
                try {
                    samochod.getLicznikUzytkownika().reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("zrobione");
                break;
            }
            case"4":
            {
                if(samochod.isZaplon()){
                    samochod.zgas_silnik();
                    System.out.println("Zgasi³eœ silnik");
                }else{
                    samochod.uruchom_silnik();
                    System.out.println("Silnik uruchomiony");
                }


                break;
            }
            case"5":
            {
                String tmp2="";
                System.out.println("\n1-swiatlo mijania\n2-swiatlo drogowe\n3-swiatlo przeciwmgielne przod\n4-swiatlo przeciwmgielne tyl");
                System.out.print("Co chcesz wlaczyc/wylaczy?\nwybor: ");
                tmp2=scan.nextLine();
                switch (tmp2){
                    case "1":
                    {
                        if(samochod.getMijania().isWlaczone()){
                            samochod.getMijania().wylacz();
                        }else{
                            samochod.getMijania().wlacz();
                        }
                        System.out.println("zrobione");
                        break;
                    }
                    case "2":
                    {
                        if(samochod.getDrogowe().isWlaczone()){
                            samochod.getDrogowe().wylacz();
                        }else{
                            samochod.getDrogowe().wlacz();
                        }
                        System.out.println("zrobione");
                        break;
                    }
                    case "3":
                    {
                        if(samochod.getPrzeciwmgielne_przod().isWlaczone()){
                            samochod.getPrzeciwmgielne_przod().wylacz();
                        }else{
                            samochod.getPrzeciwmgielne_przod().wlacz();
                        }
                        System.out.println("zrobione");
                        break;
                    }
                    case "4":
                    {
                        if(samochod.getPrzeciwmgielne_tyl().isWlaczone()){
                            samochod.getPrzeciwmgielne_tyl().wylacz();
                        }else{
                            samochod.getPrzeciwmgielne_tyl().wlacz();
                        }
                        System.out.println("zrobione");
                        break;
                    }
                    default:{
                        System.out.println("Niepoprawna wartosc");
                    }

                }
                break;
            }
            case "6":{
                break;
            }
            default:{
                System.out.println("Niepoprawna wartosc");
            }
        }
        return 0;
    }
}
