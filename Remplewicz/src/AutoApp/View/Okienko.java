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
        private JLabel predkoscLabel = new JLabel("Predkoœæ: ");
        private JLabel background;
        private JCheckBox zaplonCB = new JCheckBox("ZAP£ON [E]");
        private JMenuBar menuB;
        private JPanel predkosciomierz;
        private JMenu menuPlik,menuPomoc;
        public JMenuItem menuZapiszAuto,menuWczytajAuto,menuZapiszPodroze, menuWczytajPodroze,menuWyjscie,menuOProgramie;
        private static DecimalFormat df = new DecimalFormat("0.00");
        private ImageIcon img;
        private int angle;

        //przycisk do okna do zmiany osiagow
        private JButton osiagiButton;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public JCheckBox getZaplonCB() {
        return zaplonCB;
    }
    public void changeZaplonRB() {
        if(this.zaplonCB.isSelected())
            this.zaplonCB.setSelected(false);
        else
            this.zaplonCB.setSelected(true);
    }

    public DefaultTableModel getTabelaModel() {
        return tabelaModel;
    }

    public Okienko() {
            this.setTitle("AutoApp");
            img=new ImageIcon("background.png");
            background=new JLabel("",img,JLabel.CENTER);
            background.setBounds(0,50,848,430);
            this.add(background);
            setLayout(new BorderLayout());
            JPanel topPanel = new JPanel();
            setSize(1000,650);
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
            menuWczytajPodroze.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            menuZapiszPodroze =new JMenuItem("Zapisz historiê podró¿y");
            menuWczytajAuto=new JMenuItem("Wczytaj",'O');
            menuZapiszAuto=new JMenuItem("Zapisz",'S');
            menuZapiszPodroze.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
            menuPomoc=new JMenu("Pomoc");
            menuOProgramie=new JMenuItem("O Programie");
            menuPomoc.add(menuOProgramie);
            menuPlik.add(menuWczytajPodroze);
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

            tabelaModel = new DefaultTableModel(przejazdyTabData,tytuly);
            przejazdyTab = new JTable(tabelaModel);
            tabelaPane = new JScrollPane(przejazdyTab);
            tabelaPane.setPreferredSize(new Dimension(800,80));
            bottomPanel.add(tabelaPane);
            this.add(bottomPanel,BorderLayout.SOUTH);

            predkosciomierz = new JPanel();
            JLabel img = new JLabel(new ImageIcon("predkosciomierz.png"));
            predkosciomierz.setLayout(null);
            img.setBounds(50,20,848,430);
            predkosciomierz.add(img);
            predkoscLabel.setBounds(330,300,400,50);
            predkoscLabel.setFont(new Font("Dialog",Font.BOLD,32));
            predkosciomierz.add(predkoscLabel,0);
            pasekPredkosci = new JProgressBar(0,210);
            pasekPredkosci.setValue(0);
            pasekPredkosci.setStringPainted(true);
            predkosciomierz.add(pasekPredkosci,0);
            pasekPredkosci.setSize(new Dimension(600,30));
            pasekPredkosci.setBounds(174,400,600,30);

            this.add(predkosciomierz,BorderLayout.CENTER);
        }

    public void setPredkoscLabel(float predkosc) {
        this.predkoscLabel.setText("Prêdkoœæ: " + df.format(predkosc)+" km/h");
    }

    void setPasekPredkosciValue(float value)
    {
        this.pasekPredkosci.setValue((int)value);
    }

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
