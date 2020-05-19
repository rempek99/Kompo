package AutoApp.Data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa umo¿liwiaj¹ca nawi¹zanie po³¹czenia pomiêdzy aplikacj¹ a baz¹ danych MS SQLServer oraz zarz¹dzanie t¹ baz¹ z poziomu aplikacji
 */
public class ObslugaBazy {


    private static String nazwa_bazy_danych;
    private String connectionURL;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    /**
     * Konstruktor klasy nawi¹zuj¹cy po³¹czenie z baz¹ danych
     * @param nazwa_bazy_danych nazwa bazy, z któr¹ aplikacja nawi¹¿e po³¹czenie
     * @throws SQLException
     */
    public ObslugaBazy(String nazwa_bazy_danych) throws SQLException {
        this.nazwa_bazy_danych = nazwa_bazy_danych;
        connectionURL = "jdbc:sqlserver://localhost;databaseName=" + nazwa_bazy_danych + ";integratedSecurity=true";
        con = DriverManager.getConnection(connectionURL);
        stmt = con.createStatement();
    }

    /**
     * Wczytuje rekordy zapisane w bazie danych w tabeli "przejazdy"
     * @return rekordy zapisane w bazie w postaci listy
     * @throws SQLException
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
     * Usuwa wszystkie rekordy zapisane w bazie danych w tabeli "przejazdy"
     * @throws SQLException
     */
    public void wyczyscBaze() throws SQLException {
        rs = stmt.executeQuery("DELETE FROM "+nazwa_bazy_danych+".dbo.przejazdy SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
    }

    /**
     * Dodaje do bazy rekodry, których nr id nie powiela siê z istniej¹cymi ju¿ rekordami zapisanymi w bazie danych
     * @param podroze lista podró¿y, któr¹ zapiszemy w bazie
     * @throws {@link java.sql.SQLException}
     * @see java.sql.SQLException
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

