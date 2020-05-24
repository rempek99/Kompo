package AutoApp.View;

import javax.swing.*;
import java.awt.*;

/**
 * Pozwala na podanie nazwy bazy dancyh z jaką program będzie się łączył
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class OknoTextDataBase extends JFrame {
    /**
     * Umożliwia wprowadzenie nazwy bazy danych z jaką chcemy nawiązac połączenie. Domyślna wartość to "komponentowe".
     */
    private JTextField nazwa_bazy_danych;
    private JPanel topPanel = new JPanel();
    private JButton zastosujButton;
    private int menu;

    /**
     * Ustawia wartość pola menu
     * @param menu-wartość która bedzię przypisanan do pola menu tej klasy
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

    /**
     * Konstruktor bezprametrowy
     */
    OknoTextDataBase ()
    {
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
     * Zwraca wprowadzoną w JTextField nazwę bazy danych
     * @see JTextField
     * @return Ciąg znaków wprowadzony w JTextField
     */
    public String getNazwa_bazy_danych() {
        return nazwa_bazy_danych.getText();
    }
}
