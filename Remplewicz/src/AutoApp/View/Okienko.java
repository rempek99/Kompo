package AutoApp.View;

import AutoApp.Data.Podroz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Okno symuluj�ce desk� rozdzielcz�.
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 * @see JFrame
 */
public class Okienko extends JFrame{

        private JProgressBar pasekPredkosci;


    /**
     * S�u�y do resetowania przez u�ytkownika jednego z przebieg�w
     */
    private JButton resetPrzebiegButton;
    /**
     * S�u�y do w��cznia i wy��czania tempomatu
     */
    private JButton tempomatButton;
    // Spis przejazd�w
    /**
     * Przechowuje nag��wki tabeli w kt�rej wy�wietlane s� podr�e.
     */
    private String [] tytuly;
    /**
     *Zawiera tablice z danymi podr�y kt�re s� wy�wietlane w tabeli
     */
    private  Object przejazdyTabData[][];
    /**
     *Tablica w kt�rej prezentowany jest zapis podr�y
     */
        private JTable przejazdyTab;
    /**
     *
     */
    private  DefaultTableModel tabelaModel;
    /**
     *Przy wi�kszej ilo�ci podr�y w tabeli umo�liwia scrollowanie po tabeli
     */
        private JScrollPane tabelaPane;
    /**
     *  Znajduje si� w nim z zapisanymi podr�ami
     */
        private JPanel bottomPanel;
    /**
     *  Gromadzi informacje o parametrach samochodu
     */

        private JPanel topPanel;
    /**
     *Informacje o mocy siilnika
     */
        private JLabel infoLabel;
    /**
     *Stan pierwszego licznika
     */
        private JLabel licznik1Label;
    /**
     *Stan drugiego licznika
     */
        private JLabel licznik2Label;
    /**
     * Stan licznika kt�ry samodzielnie moze resetowan uzytkonik
     */
        private JLabel licznikUzytkownikaLabel;
    /**
     *Aktualna pr�dko��
     */
        private JLabel predkoscLabel;
    /**
     *Informuje o tym czy silnik jest uruchomiony czy te� nie
     */
        private JCheckBox zaplonCB;
    /**
     *
     */
        private JMenuBar menuB;
    /**
     *
     */
        private JPanel predkosciomierz;
    /**
     * Informacja o �rednim spalaniu
     */
    private JLabel spalanieLabel;
    /**
     *Element sk�adowy menuBar
     */
        private JMenu menuPlik,menuPomoc;
    /**
     *Element sk�adowy menuBar
     */
        private  JMenuItem menuZapiszAuto,menuWczytajAuto,menuZapiszPodroze, menuWczytajPodroze,menuWyjscie,menuOProgramie,menuWczytajPodrozeZBazyDanych,menuDodajPodrozeDoBazyDanych,menuWyczyscBazeDanych;
        private static DecimalFormat df = new DecimalFormat("0.00");

        private JLabel swiatla_krotkie,swiatla_dlugie, kierunkowskaz_l,kierunkowskaz_p, swiatla_przeciwmgielne_p,swiatla_przeciwmgielne_t;
        private JCheckBox przelacznik_swiatla_krotkie, przelacznik_swiatla_dlugie, przelacznik_swiatla_przeciwmgielne_p, przelacznik_swiatla_przeciwmgielne_t;


    /**
     *     Przycisk do okna do zmiany osiagow
     */
        private JButton osiagiButton;

    /**
     * Umo�liwia dost�p do check boxa informujacego o uruchomieniu silnika
     * @return obiekt JCheckBox przechowuj�cy stan silnika (uruchomiony kub nie)
     */
    public JCheckBox getZaplonCB() {
        return zaplonCB;
    }

    /**
     * Umo�liwia zmiane stanu silnika z uruchomiony na zgaszony i odwrotnie (w zale�no�ci od aktualnego stanu)
     */
    public void changeZaplonRB() {
        if(this.zaplonCB.isSelected())
            this.zaplonCB.setSelected(false);
        else
            this.zaplonCB.setSelected(true);
    }

    public JLabel getSwiatla_krotkie() {
        return swiatla_krotkie;
    }

    public JLabel getSwiatla_dlugie() {
        return swiatla_dlugie;
    }

    public JLabel getKierunkowskaz_l() {
        return kierunkowskaz_l;
    }

    public JLabel getKierunkowskaz_p() {
        return kierunkowskaz_p;
    }

    public DefaultTableModel getTabelaModel() {
        return tabelaModel;
    }

    public Okienko() {

            predkoscLabel = new JLabel("Predko��: ");
            spalanieLabel=new JLabel("�rednie spalanie: --,--l/100km");

            this.setTitle("AutoApp");
            setLayout(new BorderLayout());
            topPanel = new JPanel();
            setSize(1000,700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            infoLabel = new JLabel("Moc silnika: ");
            topPanel.add(infoLabel,BorderLayout.NORTH);
            zaplonCB = new JCheckBox("ZAP�ON [E]");
            zaplonCB.setBounds(450,280,50,20);
			zaplonCB.setEnabled(false);
            topPanel.add(zaplonCB,BorderLayout.SOUTH);
            osiagiButton = new JButton("Zmien Osi�gi");
            osiagiButton.setFocusable(false);
            topPanel.add(osiagiButton);
            menuB=new JMenuBar();
            menuWyjscie=new JMenuItem("Wyj�cie");
            menuPlik=new JMenu("Plik");
            menuWczytajPodroze =new JMenuItem("Wczytaj histori� podr�y");
            menuWczytajPodrozeZBazyDanych = new JMenuItem("Wczytaj Podr�e Z Bazy Danych");
            menuDodajPodrozeDoBazyDanych = new JMenuItem("Dodaj Podr�e Do Bazy Danych");
            menuWyczyscBazeDanych = new JMenuItem("Wyczysc baze danych");
            menuWczytajPodroze.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            menuZapiszPodroze =new JMenuItem("Zapisz histori� podr�y");
            menuWczytajAuto=new JMenuItem("Wczytaj",'O');
            menuZapiszAuto=new JMenuItem("Zapisz",'S');
            menuZapiszPodroze.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
            menuPomoc=new JMenu("Pomoc");
            menuOProgramie=new JMenuItem("O Programie");
            menuPomoc.add(menuOProgramie);
            menuPlik.add(menuWczytajPodroze);
            menuPlik.add(menuWczytajPodrozeZBazyDanych);
            menuPlik.add(menuDodajPodrozeDoBazyDanych);
            menuPlik.add(menuWyczyscBazeDanych);
            menuPlik.add(menuZapiszPodroze);
            menuPlik.addSeparator();
            menuPlik.add(menuWyjscie);
            menuB.add(menuPlik);
            menuB.add(Box.createHorizontalGlue());
            menuB.add(menuPomoc);
            this.add(topPanel,BorderLayout.NORTH);
            this.setJMenuBar(menuB);
			
			bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.white);
            bottomPanel.setPreferredSize(new Dimension(1000,150));

            tytuly = new String[]{"START","STOP","DYSTANS","�REDNIA PR�DKO��", "CZAS"};
            tabelaModel = new DefaultTableModel(przejazdyTabData,tytuly);
            przejazdyTab = new JTable(tabelaModel);
            tabelaPane = new JScrollPane(przejazdyTab);
            tabelaPane.setPreferredSize(new Dimension(800,80));
            bottomPanel.add(tabelaPane);
            this.add(bottomPanel,BorderLayout.SOUTH);

            predkosciomierz = new JPanel();
            predkosciomierz.setLayout(null);
            JLabel tarcza = new JLabel(new ImageIcon("tarcza.png"));
            tarcza.setBounds(50,20,848,430);
            swiatla_krotkie = new JLabel(new ImageIcon("krotkie.png"));
            swiatla_krotkie.setBounds(380,150,75,60);
            swiatla_dlugie = new JLabel(new ImageIcon("dlugie.png"));
            swiatla_dlugie.setBounds(495,150,75,52);
            kierunkowskaz_l = new JLabel(new ImageIcon("kier_l.png"));
            kierunkowskaz_l.setBounds(250,150,46,46);
            kierunkowskaz_p = new JLabel(new ImageIcon("kier_p.png"));
            kierunkowskaz_p.setBounds(650,150,46,46);
            swiatla_przeciwmgielne_p = new JLabel(new ImageIcon("przegiwmgielne_przod.png"));
            swiatla_przeciwmgielne_p.setBounds(200,220,75,64);
            swiatla_przeciwmgielne_t = new JLabel(new ImageIcon("przeciwmgielne_tyl.png"));
            swiatla_przeciwmgielne_t.setBounds(660,220,75,60);
            przelacznik_swiatla_krotkie = new JCheckBox("�wiat�a Mijania [J]");
            przelacznik_swiatla_dlugie = new JCheckBox("�wiat�a Drogowe [K]");
            przelacznik_swiatla_przeciwmgielne_p = new JCheckBox("�wiat�a Przeciwmgielne Przednie [N]");
            przelacznik_swiatla_przeciwmgielne_t = new JCheckBox("�wiat�a Przeciwmgielne Tylne [M]");
            przelacznik_swiatla_krotkie.setBounds(400,50,145,20);
            przelacznik_swiatla_dlugie.setBounds(400,75,145,20);
            przelacznik_swiatla_przeciwmgielne_p.setBounds(355,100,235,20);
            przelacznik_swiatla_przeciwmgielne_t.setBounds(355,125,235,20);
            kierunkowskaz_p.setVisible(false);
            kierunkowskaz_l.setVisible(false);
            swiatla_dlugie.setVisible(false);
            swiatla_krotkie.setVisible(false);
            swiatla_przeciwmgielne_t.setVisible(false);
            swiatla_przeciwmgielne_p.setVisible(false);
            przelacznik_swiatla_dlugie.setFocusable(false);
            przelacznik_swiatla_krotkie.setFocusable(false);
            przelacznik_swiatla_przeciwmgielne_p.setFocusable(false);
            przelacznik_swiatla_przeciwmgielne_p.setEnabled(false);
            przelacznik_swiatla_przeciwmgielne_t.setFocusable(false);
            przelacznik_swiatla_przeciwmgielne_t.setEnabled(false);

            predkosciomierz.add(tarcza);
            predkosciomierz.add(swiatla_krotkie,0);
            predkosciomierz.add(swiatla_dlugie,0);
            predkosciomierz.add(kierunkowskaz_l,0);
            predkosciomierz.add(kierunkowskaz_p,0);
            predkosciomierz.add(swiatla_przeciwmgielne_p,0);
            predkosciomierz.add(swiatla_przeciwmgielne_t,0);
            predkosciomierz.add(przelacznik_swiatla_krotkie,0);
            predkosciomierz.add(przelacznik_swiatla_dlugie,0);
            predkosciomierz.add(przelacznik_swiatla_przeciwmgielne_p,0);
            predkosciomierz.add(przelacznik_swiatla_przeciwmgielne_t,0);
            tempomatButton=new JButton();
            tempomatButton.setBounds(150,300,120,25);
            tempomatButton.setText("tempomat OFF");
            tempomatButton.setBackground(Color.decode("#db2416"));
            tempomatButton.setFocusable(false);
            predkosciomierz.add(tempomatButton,0);
            predkoscLabel.setBounds(330,300,400,50);
            predkoscLabel.setFont(new Font("Dialog",Font.BOLD,32));
            predkosciomierz.add(predkoscLabel,0);
            spalanieLabel.setBounds(350,340,400,50);
            spalanieLabel.setFont(new Font("Dialog",Font.BOLD,18));
            predkosciomierz.add(spalanieLabel,0);
            resetPrzebiegButton =new JButton("reset");
            resetPrzebiegButton.setFocusable(false);
            resetPrzebiegButton.setBounds(600,200,100,25);
            predkosciomierz.add(resetPrzebiegButton,0);
            licznikUzytkownikaLabel=new JLabel("Przebieg: 0,00 km");
            licznikUzytkownikaLabel.setBounds(390,200,210,25);
            licznikUzytkownikaLabel.setFont(new Font("Dialog",Font.ITALIC,20));
            predkosciomierz.add(licznikUzytkownikaLabel,0);
            licznik1Label = new JLabel("Licznik1");
            licznik1Label.setBounds(350,250,300,50);
            licznik1Label.setFont(new Font("Dialog",Font.ITALIC,22));
            predkosciomierz.add(licznik1Label,0);
            licznik2Label = new JLabel("Licznik2");
            licznik2Label.setBounds(370,230,300,25);
            licznik2Label.setFont(new Font("Dialog",Font.ITALIC,18));
            predkosciomierz.add(licznik2Label,0);
            pasekPredkosci = new JProgressBar(0,210);
            pasekPredkosci.setValue(0);
            pasekPredkosci.setStringPainted(true);
            predkosciomierz.add(pasekPredkosci,0);
            pasekPredkosci.setSize(new Dimension(600,30));
            pasekPredkosci.setBounds(174,400,600,30);

            this.add(predkosciomierz,BorderLayout.CENTER);
        }

    public JCheckBox getPrzelacznik_swiatla_krotkie() {
        return przelacznik_swiatla_krotkie;
    }

    public JCheckBox getPrzelacznik_swiatla_dlugie() {
        return przelacznik_swiatla_dlugie;
    }

    public JLabel getSwiatla_przeciwmgielne_p() {
        return swiatla_przeciwmgielne_p;
    }

    public JButton getResetPrzebiegButton() {
        return resetPrzebiegButton;
    }
    public void setLicznikUzytkownikaLabel(double wartosc) {
        this.licznikUzytkownikaLabel.setText("Przebieg: "+df.format(wartosc)+" km");
    }

    public JLabel getSwiatla_przeciwmgielne_t() {
        return swiatla_przeciwmgielne_t;
    }

    public JCheckBox getPrzelacznik_swiatla_przeciwmgielne_p() {
        return przelacznik_swiatla_przeciwmgielne_p;
    }

    public JButton getTempomatButton() {
        return tempomatButton;
    }

    public JProgressBar getPasekPredkosci() {
        return pasekPredkosci;
    }

    public String[] getTytuly() {
        return tytuly;
    }

    public Object[][] getPrzejazdyTabData() {
        return przejazdyTabData;
    }

    public JTable getPrzejazdyTab() {
        return przejazdyTab;
    }

    public JScrollPane getTabelaPane() {
        return tabelaPane;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public JLabel getLicznik1Label() {
        return licznik1Label;
    }

    public JLabel getLicznik2Label() {
        return licznik2Label;
    }

    public JLabel getPredkoscLabel() {
        return predkoscLabel;
    }

    public JMenuBar getMenuB() {
        return menuB;
    }

    public JPanel getPredkosciomierz() {
        return predkosciomierz;
    }

    public JLabel getSpalanieLabel() {
        return spalanieLabel;
    }

    public JMenu getMenuPlik() {
        return menuPlik;
    }

    public JMenu getMenuPomoc() {
        return menuPomoc;
    }

    public JMenuItem getMenuZapiszAuto() {
        return menuZapiszAuto;
    }

    public JMenuItem getMenuWczytajAuto() {
        return menuWczytajAuto;
    }

    public JMenuItem getMenuZapiszPodroze() {
        return menuZapiszPodroze;
    }

    public JMenuItem getMenuWczytajPodroze() {
        return menuWczytajPodroze;
    }

    public JMenuItem getMenuWyjscie() {
        return menuWyjscie;
    }

    public JMenuItem getMenuOProgramie() {
        return menuOProgramie;
    }

    public JMenuItem getMenuWczytajPodrozeZBazyDanych() {
        return menuWczytajPodrozeZBazyDanych;
    }

    public JMenuItem getMenuDodajPodrozeDoBazyDanych() {
        return menuDodajPodrozeDoBazyDanych;
    }

    public JMenuItem getMenuWyczyscBazeDanych() {
        return menuWyczyscBazeDanych;
    }

    public static DecimalFormat getDf() {
        return df;
    }

    public JButton getOsiagiButton() {
        return osiagiButton;
    }

    public JCheckBox getPrzelacznik_swiatla_przeciwmgielne_t() {
        return przelacznik_swiatla_przeciwmgielne_t;
    }

    public void setPredkoscLabel(float predkosc) {
        this.predkoscLabel.setText("Pr�dko��: " + df.format(predkosc)+" km/h");
    }
    public void setSpalanieLabel(double spalanie){
        if(spalanie==0) {
            this.spalanieLabel.setText("�rednie spalanie: --,-- l/100km");
        }else{
            this.spalanieLabel.setText("�rednie spalanie: "+df.format(spalanie)+" l/100km");
        }

    }
    public void setLicznik1Label(double dystans) {
        this.licznik1Label.setText("Aktualna podr�: "+df.format(dystans)+" km");
    }
    public void setLicznik2Label(double dystans) {
        this.licznik2Label.setText("Przebieg g��wny: "+df.format(dystans)+" km");
    }

    void setPasekPredkosciValue(float value)
    {
        this.pasekPredkosci.setValue((int)value);
    }

    void setPasekPredkosciMaximum(int value){this.pasekPredkosci.setMaximum(value);}

    void addOsiagiButtonListener(ActionListener osiagiButtonListener)
    {
        this.osiagiButton.addActionListener(osiagiButtonListener);
    }

    /**
     * Zawiera podstawowe informacje o pojezdzie do wy�wietlenia
     * @param moc-moc silnika
     * @param mocHamulcow-skutecznosc hamulcow
     * @param maxPredkosc-maksymalna do osiagniecia predkosc przez samochod
     */
    public void setInfoLabel(int moc, int mocHamulcow, int maxPredkosc) {
        this.infoLabel.setText("Moc silnika: " + Integer.toString(moc) +
                " Moc hamulc�w: " + Integer.toString(mocHamulcow) + "%"+
                " Max Pr�dko��: " + Integer.toString(maxPredkosc) + "km/h");
    }

    /**
     * Pozwala na zmian� aktualnego spisu podr�y na innny
     * @param dane lista podr�y
     */
    public void setTableData(ArrayList<Podroz> dane){
        przejazdyTabData = new Object[dane.size()][5];
        for(int i = 0; i<dane.size();i++)
        {
            for(int j = 0; j<5;j++)
            {
                przejazdyTabData[i][j] = dane.get(i).stringData(j);
            }
        }
        tabelaModel.setDataVector(przejazdyTabData,tytuly);
    }
    public void ustawSwiatlo(int value, Color color)
    {
        //0 - zgaszone, 1 - mijania, 2 - drogowe
        switch (value)
        {
            case 0:
            {
                topPanel.setBackground(new Color(color.getRed(),color.getGreen(),color.getBlue(),0));
                repaint();
                break;
            }
            case 1:
            {
                topPanel.setBackground(new Color(color.getRed(),color.getGreen(),color.getBlue(),60));
                repaint();
                break;
            }
            case 2: {
                topPanel.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 200));
                repaint();
                break;
            }
        }
    }

}
