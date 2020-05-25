package AutoApp.Data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa umo�liwiaj�ca nawi�zanie po��czenia aplikacji z lokaln� baz� danych SQLServer.
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class ObslugaBazy {

    /**
     * Nazwa bazy, z kt�r� nawi�zane zostanie po��czenie
     */
    private static String nazwa_bazy_danych;
    /**
     * Link URL s�u��cy do nawi�zania po��czenia
     */
    private String connectionURL;
    /**
     * Obiekt klasy umo�liwiaj�cy nawi�zanie po��czenia
     * @see Connection
     */
    private Connection con;
    /**
     * Obiekt umo�liwiaj�cy wykonywanie instrukcji SQL i zwracanie ich wynik�w
     * @see Statement
     */
    private Statement stmt;
    /**
     * Obiekt reprezentuj�cy zbi�r danych zwr�cony przez baz� danych
     * @see ResultSet
     */
    private ResultSet rs;

    /**
     * Konstruktor klasy
     * @param nazwa_bazy_danych nazwa bazy danych, z kt�r� zostanie nawi�zane po��czenie
     * @throws SQLException rzucany w przypadku b��d�w zwi�zanych np. z dost�pem do bazy danych
     * @see SQLException
     */
    public ObslugaBazy(String nazwa_bazy_danych) throws SQLException {
        this.nazwa_bazy_danych = nazwa_bazy_danych;
        connectionURL = "jdbc:sqlserver://localhost;databaseName=" + nazwa_bazy_danych + ";integratedSecurity=true";
        con = DriverManager.getConnection(connectionURL);
        stmt = con.createStatement();
    }

    /**
     * Metoda umo�liwiaj�ca odczyt rekord�w podr�y z bazy danych z tablicy przejazdy
     * @return lista podr�y zapisana w bazie
     * @throws SQLException rzucany w przypadku b��d�w zwi�zanych np. z dost�pem do bazy danych
     * @see SQLException
     */
    public ArrayList<Podroz> wczytajBaze() throws SQLException {
        ArrayList<Podroz> result = new ArrayList<Podroz>();
        rs = stmt.executeQuery("SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
        while (rs.next()) {
            Podroz tmp = new Podroz(rs.getInt("id"), rs.getFloat("dystans"), rs.getTimestamp("poczatek"), rs.getTimestamp("koniec"), rs.getFloat("sr_predkosc"));
            result.add(tmp);
        }
        return result;
    }

    /**
     * Usuwa wszystkie rekordy podr�y zapisane w bazy danych w tablicy przejazdy
     * @throws SQLException rzucany w przypadku b��d�w zwi�zanych np. z dost�pem do bazy danych
     * @see SQLException
     */
    public void wyczyscBaze() throws SQLException {
        rs = stmt.executeQuery("DELETE FROM "+nazwa_bazy_danych+".dbo.przejazdy SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
    }

    /**
     * Dodaje do bazy danych rekordy przejazd�w, sprawdza unikalno�� id
     * @param podroze lista podr�y, kt�ra zostanie dodana do bazy danych
     * @throws SQLException rzucany w przypadku b��d�w zwi�zanych np. z dost�pem do bazy danych
     * @see SQLException
     */
    public void dodajDoBazy(ArrayList<Podroz> podroze) throws SQLException {

        boolean unique = true;

        for (int i = 0; i < podroze.size(); i++) {
            unique = true;
            rs = stmt.executeQuery("SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
            while (rs.next()) {
                if (rs.getInt("id") == podroze.get(i).getId())
                    unique = false;
            }

            if (unique) {
                String values = "";
                values += "'" + podroze.get(i).stringData(0) + "',";
                values += "'" + podroze.get(i).stringData(1) + "',";
                values += "'" + podroze.get(i).getDlugosc() + "',";
                values += "'" + podroze.get(i).getSrednia_predkosc() + "',";
                values += "'" + podroze.get(i).getCzas() + "'";
                rs = stmt.executeQuery("INSERT INTO " + nazwa_bazy_danych + ".dbo.przejazdy VALUES(" + values + ") SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
            }
        }
    }
}

