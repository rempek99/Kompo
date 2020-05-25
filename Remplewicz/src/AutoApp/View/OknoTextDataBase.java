package AutoApp.View;

import javax.swing.*;
import java.awt.*;

/**
 * Pozwala na podanie nazwy bazy dancyh z jak� program b�dzie si� ��czy�
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 * @see JFrame
 */
public class OknoTextDataBase extends JFrame {
    /**
     * Umo�liwia wprowadzenie nazwy bazy danych z jak� chcemy nawi�zac po��czenie. Domy�lna warto�� to "komponentowe".
     */
    private JTextField nazwa_bazy_danych;
    /**
     * Panel gromadz�cy wy�wietlane obiekty
     */
    private JPanel topPanel;
    /**
     * Przycisk zatwierdzaj�cy wprowadzone informacje
     */
    private JButton zastosujButton;
    /**
     * Warto�� okre�laj�ca dzia�anie, jakie u�ytkownik chce przeprowadzi� w bazie danych
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
     * Zwraca wprowadzon� w JTextField nazw� bazy danych
     * @see JTextField
     * @return Ci�g znak�w wprowadzony w JTextField
     */
    public String getNazwa_bazy_danych() {
        return nazwa_bazy_danych.getText();
    }
    /**
     * Ustawia warto�� pola menu
     * @param menu-warto�� kt�ra bedzi� przypisanan do pola menu tej klasy
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
