package AutoApp.View;

import javax.swing.*;
import java.awt.*;

/**
 * Pozwala na podanie nazwy bazy dancyh z jak¹ program bêdzie siê ³¹czy³
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 * @see JFrame
 */
public class OknoTextDataBase extends JFrame {
    /**
     * Umo¿liwia wprowadzenie nazwy bazy danych z jak¹ chcemy nawi¹zac po³¹czenie. Domyœlna wartoœæ to "komponentowe".
     */
    private JTextField nazwa_bazy_danych;
    /**
     * Panel gromadz¹cy wyœwietlane obiekty
     */
    private JPanel topPanel;
    /**
     * Przycisk zatwierdzaj¹cy wprowadzone informacje
     */
    private JButton zastosujButton;
    /**
     * Wartoœæ okreœlaj¹ca dzia³anie, jakie u¿ytkownik chce przeprowadziæ w bazie danych
     */
    private int menu;
    /**
     * Konstruktor bezprametrowy
     */
    OknoTextDataBase ()
    {
        this.topPanel = new JPanel();
        this.setSize(150,150);
        nazwa_bazy_danych = new JTextField("komponentowe");
        topPanel.add(new JLabel("Nazwa bazy danych: "));
        topPanel.add(nazwa_bazy_danych, BorderLayout.WEST);
        zastosujButton = new JButton("Zastosuj");
        topPanel.add(zastosujButton);
        this.add(topPanel);
        this.menu = 0;
    }
    /**
     * Zwraca pole menu
     * @return Pole menu
     */
    public int getMenu() {
        return menu;
    }
    /**
     * Zwraca wprowadzon¹ w JTextField nazwê bazy danych
     * @see JTextField
     * @return Ci¹g znaków wprowadzony w JTextField
     */
    public String getNazwa_bazy_danych() {
        return nazwa_bazy_danych.getText();
    }
    /**
     * Ustawia wartoœæ pola menu
     * @param menu-wartoœæ która bedziê przypisanan do pola menu tej klasy
     */
    public void setMenu(int menu) {
        this.menu = menu;
    }
    /**
     * Zwraca obiekt zastosujButton typu JButton
     * @see JButton
     * @return zastosujButton
     */
    public JButton getZastosujButton() {
        return zastosujButton;
    }
}
