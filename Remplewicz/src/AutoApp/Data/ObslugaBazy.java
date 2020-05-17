package AutoApp.Data;

import java.sql.*;
import java.util.ArrayList;

public class ObslugaBazy {


    private static String nazwa_bazy_danych;
    private String connectionURL;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public ObslugaBazy(String nazwa_bazy_danych) throws SQLException {
        this.nazwa_bazy_danych = nazwa_bazy_danych;
        connectionURL = "jdbc:sqlserver://localhost;databaseName=" + nazwa_bazy_danych + ";integratedSecurity=true";
        con = DriverManager.getConnection(connectionURL);
        stmt = con.createStatement();
    }

    public ArrayList<Podroz> wczytajBaze() throws SQLException {
        ArrayList<Podroz> result = new ArrayList<Podroz>();
        rs = stmt.executeQuery("SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
        while (rs.next()) {
            Podroz tmp = new Podroz(rs.getInt("id"), rs.getFloat("dystans"), rs.getTimestamp("poczatek"), rs.getTimestamp("koniec"), rs.getFloat("sr_predkosc"));
            result.add(tmp);
        }
        return result;
    }

    public void wyczyscBaze() throws SQLException {
        rs = stmt.executeQuery("DELETE FROM "+nazwa_bazy_danych+".dbo.przejazdy SELECT * FROM " + nazwa_bazy_danych + ".dbo.przejazdy");
    }

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

