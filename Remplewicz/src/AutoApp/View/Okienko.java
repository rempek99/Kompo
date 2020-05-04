package AutoApp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;


public class Okienko extends JFrame implements ActionListener{

        private JTextArea przejazdyText;
        private JPanel bottomPanel;
        private JPanel topPanel;
        private JLabel infoLabel = new JLabel("Moc silnika: ");
        private JLabel predkoscLabel = new JLabel("Predkosc: ");
        private JLabel background;
        //private JButton gazButton = new JButton("GAZ");
        //private JButton hamulecButton = new JButton("HAMULEC");
        private JCheckBox zaplonCB = new JCheckBox("ZAP£ON [E]");
        private JMenuBar menuB;
        private JPanel predkosciomierz;

    public JCheckBox getZaplonCB() {
        return zaplonCB;
    }

    public void changeZaplonRB() {
        if(this.zaplonCB.isSelected())
            this.zaplonCB.setSelected(false);
        else
            this.zaplonCB.setSelected(true);
    }

    private JMenu menuPlik,menuPomoc;
        private JMenuItem menuZapisz, menuWczytaj,menuWyjscie,menuOProgramie;
        private static DecimalFormat df = new DecimalFormat("0.00");
        private ImageIcon img;

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JTextArea getPrzejazdyText() {
        return przejazdyText;
    }

    public Okienko()
        {
            this.setTitle("AutoApp");
            img=new ImageIcon("background.png");
            background=new JLabel("",img,JLabel.CENTER);
            background.setBounds(0,50,848,430);
            this.add(background);
            setLayout(new BorderLayout());
            topPanel = new JPanel();
            setSize(1000,600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            topPanel.add(infoLabel,BorderLayout.NORTH);

            //topPanel.add(gazButton,BorderLayout.CENTER);
            //topPanel.add(hamulecButton,BorderLayout.CENTER);
            zaplonCB.setBounds(450,280,50,20);
            zaplonCB.setEnabled(false);
            topPanel.add(zaplonCB,BorderLayout.SOUTH);
            menuB=new JMenuBar();
            menuWyjscie=new JMenuItem("Wyjœcie");
            menuPlik=new JMenu("Plik");
            menuWczytaj=new JMenuItem("Wczytaj",'O');
            menuWczytaj.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            menuZapisz=new JMenuItem("Zapisz",'S');
            menuZapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
            menuPomoc=new JMenu("Pomoc");
            menuOProgramie=new JMenuItem("O Programie");
            menuPomoc.add(menuOProgramie);
            menuPlik.add(menuWczytaj);
            menuPlik.add(menuZapisz);
            menuPlik.addSeparator();
            menuPlik.add(menuWyjscie);
            menuB.add(menuPlik);
            menuB.add(Box.createHorizontalGlue());
            menuB.add(menuPomoc);
            menuOProgramie.addActionListener(this);
            menuWyjscie.addActionListener(this);
            menuWczytaj.addActionListener(this);
            menuZapisz.addActionListener(this);
            this.add(topPanel,BorderLayout.NORTH);
            this.setJMenuBar(menuB);

            bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.white);
            przejazdyText = new JTextArea("Przejazdy:\n");
            przejazdyText.setEditable(false);
            bottomPanel.add(przejazdyText);
            this.add(bottomPanel,BorderLayout.SOUTH);

            predkosciomierz = new JPanel();
            JLabel img = new JLabel(new ImageIcon("predkosciomierz.png"));
            predkosciomierz.setLayout(null);
            img.setBounds(50,20,848,430);
            predkosciomierz.add(img);
            predkoscLabel.setBounds(330,300,300,50);
            predkoscLabel.setFont(new Font("Dialog",Font.BOLD,36));
            predkosciomierz.add(predkoscLabel,0);
            this.add(predkosciomierz,BorderLayout.CENTER);



            //setVisible(true);
        }

    public void setPredkoscLabel(float predkosc) {
        this.predkoscLabel.setText("Predkosc: " + df.format(predkosc));
    }

    public void setInfoLabel(int moc, int mocHamulcow, int maxPredkosc) {
        this.infoLabel.setText("Moc silnika: " + Integer.toString(moc) +
                " Moc hamulców: " + Integer.toString(mocHamulcow) + "%"+
                " Max Prêdkoœæ: " + Integer.toString(maxPredkosc) + "km/h");
    }
//    void addGazListener(ActionListener gazListener)
//    {
//        gazButton.addActionListener(gazListener);
//    }
//    void addHamulecListener(ActionListener hamulecListener)
//    {
//        hamulecButton.addActionListener(hamulecListener);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==menuOProgramie)
        {
            JOptionPane.showMessageDialog(this,"strza³ka do góry-przyspieszanie\n" +
                    "strza³ka w dó³-hamowanie\n" +
                    "strza³ka w lewo/prawo-skrêcanie\n");
        }
        if(source==menuWczytaj)
        {
            dispose();
        }
        if(source==menuZapisz)
        {
            dispose();
        }
        if(source==menuWyjscie)
        {
            dispose();
        }
    }



}
