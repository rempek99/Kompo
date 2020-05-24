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

    // Spis przejazd�w
    /**
     * Przechowuje nag��wki tabeli w kt�rej wy�wietlane s� podr�e.
     */
        String [] tytuly = {"START","STOP","DYSTANS","�REDNIA PR�DKO��", "CZAS"};
    /**
     *Zawiera tablice z danymi podr�y kt�re s� wy�wietlane w tabeli
     */
    Object przejazdyTabData[][];
    /**
     *Tablica w kt�rej prezentowany jest zapis podr�y
     */
        private JTable przejazdyTab;
    /**
     *
     */
        DefaultTableModel tabelaModel;
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
        private JLabel infoLabel = new JLabel("Moc silnika: ");
    /**
     *Stan pierwszego licznika
     */
        private JLabel licznik1Label = new JLabel("Licznik1");
    /**
     *Stan drugiego licznika
     */
        private JLabel licznik2Label = new JLabel("Licznik2");
    /**
     *Aktualna pr�dko��
     */
        private JLabel predkoscLabel;
    /**
     *Informuje o tym czy silnik jest uruchomiony czy te� nie
     */
        private JCheckBox zaplonCB = new JCheckBox("ZAP�ON [E]");
    /**
     *
     */
        private JMenuBar menuB;
    /**
     *
     */
        private JPanel predkosciomierz;
    /**
     *Element sk�adowy menuBar
     */
        private JMenu menuPlik,menuPomoc;
    /**
     *Element sk�adowy menuBar
     */
        public JMenuItem menuZapiszAuto,menuWczytajAuto,menuZapiszPodroze, menuWczytajPodroze,menuWyjscie,menuOProgramie,menuWczytajPodrozeZBazyDanych,menuDodajPodrozeDoBazyDanych,menuWyczyscBazeDanych;
        private static DecimalFormat df = new DecimalFormat("0.00");

        JLabel swiatla_krotkie;
        JLabel swiatla_dlugie;
        JLabel kierunkowskaz_l;
        JLabel kierunkowskaz_p;
        JCheckBox przelacznik_swiatla_krotkie;
        JCheckBox przelacznik_swiatla_dlugie;

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

            this.setTitle("AutoApp");
            setLayout(new BorderLayout());
            topPanel = new JPanel();
            setSize(1000,700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            topPanel.add(infoLabel,BorderLayout.NORTH);
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
            kierunkowskaz_p.setVisible(false);
            kierunkowskaz_l.setVisible(false);
            swiatla_dlugie.setVisible(false);
            swiatla_krotkie.setVisible(false);
            przelacznik_swiatla_krotkie = new JCheckBox("�wiat�a Mijania");
            przelacznik_swiatla_dlugie = new JCheckBox("�wiat�a Drogowe");
            przelacznik_swiatla_krotkie.setBounds(320,120,125,20);
            przelacznik_swiatla_dlugie.setBounds(495,120,125,20);
            przelacznik_swiatla_dlugie.setFocusable(false);
            przelacznik_swiatla_krotkie.setFocusable(false);

            predkosciomierz.add(tarcza);
            predkosciomierz.add(swiatla_krotkie,0);
            predkosciomierz.add(swiatla_dlugie,0);
            predkosciomierz.add(kierunkowskaz_l,0);
            predkosciomierz.add(kierunkowskaz_p,0);
            predkosciomierz.add(przelacznik_swiatla_krotkie,0);
            predkosciomierz.add(przelacznik_swiatla_dlugie,0);
            predkoscLabel.setBounds(330,300,400,50);
            predkoscLabel.setFont(new Font("Dialog",Font.BOLD,32));
            predkosciomierz.add(predkoscLabel,0);
            licznik1Label.setBounds(350,250,300,50);
            licznik1Label.setFont(new Font("Dialog",Font.ITALIC,22));
            predkosciomierz.add(licznik1Label,0);
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

    public void setPredkoscLabel(float predkosc) {
        this.predkoscLabel.setText("Pr�dko��: " + df.format(predkosc)+" km/h");
    }

    public void setLicznik1Label(double dystans) {
        this.licznik1Label.setText("Aktualna podr�: "+df.format(dystans)+"km");
    }
    public void setLicznik2Label(double dystans) {
        this.licznik2Label.setText("Przebieg: "+df.format(dystans)+"km");
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
