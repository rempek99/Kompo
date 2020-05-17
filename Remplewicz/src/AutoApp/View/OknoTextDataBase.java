package AutoApp.View;

import javax.swing.*;
import java.awt.*;

public class OknoTextDataBase extends JFrame {

    private JTextField nazwa_bazy_danych;
    private JPanel topPanel = new JPanel();
    private JButton zastosujButton;
    private int menu;

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public JButton getZastosujButton() {
        return zastosujButton;
    }

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

    public int getMenu() {
        return menu;
    }

    public String getNazwa_bazy_danych() {
        return nazwa_bazy_danych.getText();
    }
}
