package AutoApp.View;

import AutoApp.Data.Podroz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Okienko extends JFrame{

        private JProgressBar pasekPredkosci;

    // Spis przejazdów
        String [] tytuly = {"START","STOP","DYSTANS","ŒREDNIA PRÊDKOŒÆ", "CZAS"};
        Object przejazdyTabData[][];
        private JTable przejazdyTab;
        DefaultTableModel tabelaModel;
        private JScrollPane tabelaPane;
        private JPanel bottomPanel;


        // Wyswietlanie Informacji
        private JPanel topPanel;
        private JLabel infoLabel = new JLabel("Moc silnika: ");
        private JLabel licznik1Label = new JLabel("Licznik1");
        private JLabel licznik2Label = new JLabel("Licznik2");
        private JLabel predkoscLabel = new JLabel("Predkoœæ: ");
        private JCheckBox zaplonCB = new JCheckBox("ZAP£ON [E]");
        private JMenuBar menuB;
        private JPanel predkosciomierz;
        private JMenu menuPlik,menuPomoc;
        public JMenuItem menuZapiszAuto,menuWczytajAuto,menuZapiszPodroze, menuWczytajPodroze,menuWyjscie,menuOProgramie,menuWczytajPodrozeZBazyDanych,menuDodajPodrozeDoBazyDanych,menuWyczyscBazeDanych;
        private static DecimalFormat df = new DecimalFormat("0.00");

        JLabel swiatla_krotkie;
        JLabel swiatla_dlugie;
        JLabel kierunkowskaz_l;
        JLabel kierunkowskaz_p;
        JCheckBox przelacznik_swiatla_krotkie;
        JCheckBox przelacznik_swiatla_dlugie;

        //przycisk do okna do zmiany osiagow
        private JButton osiagiButton;

    public JCheckBox getZaplonCB() {
        return zaplonCB;
    }
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
            this.setTitle("AutoApp");
            setLayout(new BorderLayout());
            JPanel topPanel = new JPanel();
            setSize(1000,700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            topPanel.add(infoLabel,BorderLayout.NORTH);

            zaplonCB.setBounds(450,280,50,20);
			zaplonCB.setEnabled(false);
            topPanel.add(zaplonCB,BorderLayout.SOUTH);
            osiagiButton = new JButton("Zmien Osi¹gi");
            osiagiButton.setFocusable(false);
            topPanel.add(osiagiButton);
            menuB=new JMenuBar();
            menuWyjscie=new JMenuItem("Wyjœcie");
            menuPlik=new JMenu("Plik");
            menuWczytajPodroze =new JMenuItem("Wczytaj historiê podró¿y");
            menuWczytajPodrozeZBazyDanych = new JMenuItem("Wczytaj Podró¿e Z Bazy Danych");
            menuDodajPodrozeDoBazyDanych = new JMenuItem("Dodaj Podró¿e Do Bazy Danych");
            menuWyczyscBazeDanych = new JMenuItem("Wyczysc baze danych");
            menuWczytajPodroze.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            menuZapiszPodroze =new JMenuItem("Zapisz historiê podró¿y");
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
            przelacznik_swiatla_krotkie = new JCheckBox("Œwiat³a Mijania");
            przelacznik_swiatla_dlugie = new JCheckBox("Œwiat³a Drogowe");
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
        this.predkoscLabel.setText("Prêdkoœæ: " + df.format(predkosc)+" km/h");
    }

    public void setLicznik1Label(double dystans) {
        this.licznik1Label.setText("Aktualna podró¿: "+df.format(dystans)+"km");
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
    public void setInfoLabel(int moc, int mocHamulcow, int maxPredkosc) {
        this.infoLabel.setText("Moc silnika: " + Integer.toString(moc) +
                " Moc hamulców: " + Integer.toString(mocHamulcow) + "%"+
                " Max Prêdkoœæ: " + Integer.toString(maxPredkosc) + "km/h");
    }
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
}
