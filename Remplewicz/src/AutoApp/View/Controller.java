package AutoApp.View;

import AutoApp.Data.ObslugaBazy;
import AutoApp.Model.Nieuruchomiony;
import AutoApp.Model.Samochod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 *  Klasa odpowiadaj¹ca za synchronizacjê informacji wyœwietlanych przez Okienko oraz zawartych w Samochod.
 *  Nas³uchuje akcji ze strony u¿ytkowinika dokonanych w Okienko i wywo³uje odpowiednie metody klas sk³adowych.
 *
 * @see Samochod
 * @see Okienko
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class Controller implements ActionListener, WindowListener {
    private Samochod auto;
    /**
     * G³ówne okno aplikacji
     */
    private Okienko okno;
    /**
     * Okno modyfikacji parametrów Samochodu
     */
    private OsiagiOkienko okno2;
    /**
     * Okno poœrednicz¹ce w nawi¹zywaniu po³¹czenia z baz¹ danych. Tutaj jest wprowadzana nazwa bazy danych.
     */
    private OknoTextDataBase okno3;

    public Controller(Samochod auto, Okienko okno) {
        new SpalanieRefresher();
        okno3=new OknoTextDataBase();
        this.auto = auto;
        this.okno = okno;
        this.okno3 = new OknoTextDataBase();
        this.auto.getPredkosciomierz().addPredkoscListener(new PredkoscListener());
        this.auto.getLicznikPodrozy().addLicznikListener(new LicznikListener());
        this.auto.getLicznikGlowny().addLicznikListener(new LicznikListener());
        this.okno.setLicznik1Label(auto.getLicznikPodrozy().getDystans());
        this.okno.setLicznik2Label(auto.getLicznikGlowny().getDystans());
        this.okno.setLicznikUzytkownikaLabel(auto.getLicznikUzytkownika().getDystans());
        this.okno.getPrzelacznik_swiatla_krotkie().addActionListener(this);
        this.okno.getPrzelacznik_swiatla_dlugie().addActionListener(this);
        this.okno.getPrzelacznik_swiatla_przeciwmgielne_p().addActionListener(this);
        this.okno.getPrzelacznik_swiatla_przeciwmgielne_t().addActionListener(this);
        this.auto.getMijania().addSwiatloListener(new SwiatloListener());
        this.auto.getLewyKierunkowskaz().addSwiatloListener(new SwiatloListener());
        this.auto.getPrawyKierunkowskaz().addSwiatloListener(new SwiatloListener());
        this.auto.getDrogowe().addSwiatloListener(new SwiatloListener());
        this.auto.getPrzeciwmgielne_przod().addSwiatloListener(new SwiatloListener());
        this.auto.getPrzeciwmgielne_tyl().addSwiatloListener(new SwiatloListener());
        this.okno.addKeyListener(new KeyboarListner());
        this.okno.setFocusable(true);
        this.okno.setInfoLabel(auto.getMoc_silnika(), auto.getMoc_hamulcow(), auto.getPredkosciomierz().getMax_predkosc());
        this.okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
        this.okno.getMenuOProgramie().addActionListener(this);
        this.okno.getMenuWyjscie().addActionListener(this);
        this.okno.getMenuWczytajPodroze().addActionListener(this);
        this.okno.getMenuWczytajPodrozeZBazyDanych().addActionListener(this);
        this.okno.getMenuDodajPodrozeDoBazyDanych().addActionListener(this);
        this.okno.getMenuZapiszPodroze().addActionListener(this);
        this.okno.getMenuWyczyscBazeDanych().addActionListener(this);
        this.okno.addOsiagiButtonListener(new OsiagiButtonListener());
        this.okno.getResetPrzebiegButton().addActionListener(this);
        this.okno.getTempomatButton().addActionListener(this);
        this.okno.addWindowListener(this);

    }

    /**
     * Nieu¿ywane
     */
    @Override
    public void windowOpened(WindowEvent e) {
        //not Used
    }

    /**
     *Gdy zamykane jest okno wywoluje metode z auto ktora zapisuje stan g³ównego licznika
     */
    @Override
    public void windowClosing(WindowEvent e)
    {
        try {
            auto.zapiszGlownyLicznik("licznikGlowny.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Nieu¿ywane
     */
    @Override
    public void windowClosed(WindowEvent e) {
//not Used
    }
    /**
     * Nieu¿ywane
     */
    @Override
    public void windowIconified(WindowEvent e) {
//not Used
    }
    /**
     * Nieu¿ywane
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
//not Used
    }
    /**
     * Nieu¿ywane
     */
    @Override
    public void windowActivated(WindowEvent e) {
//not Used
    }
    /**
     * Nieu¿ywane
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
//not Used
    }


    /**
     * Odpowiada za wykrywanie zdarzeñ klawiatury (klawisze strza³ek, ESC oraz E) i wywo³anie odpowiednich metod do obs³ugi tych zdarzeñ.
     * Mo¿e wyœwietlaæ uwagi w g³ównym oknie aplikacji
     */
    class KeyboarListner implements KeyListener{
        public KeyboarListner() {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //not used
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode()==KeyEvent.VK_UP)
            {
                try {
                    auto.getTempomat().wylacz();
                    okno.getTempomatButton().setText("tempomat OFF");
                    okno.getTempomatButton().setBackground(Color.decode("#db2416"));
                    auto.gaz();
                    auto.setGaz(true);

                }catch(Nieuruchomiony ex)
                {
                    JOptionPane.showMessageDialog(okno,"Najpierw uruchom silnik (E)\n");
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
            {
                auto.getTempomat().wylacz();
                okno.getTempomatButton().setText("tempomat OFF");
                okno.getTempomatButton().setBackground(Color.decode("#db2416"));
                auto.hamulec();
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                if(okno.getZaplonCB().isSelected()) {
                    auto.skret(false);
                }else
                {
                    JOptionPane.showMessageDialog(okno,"Najpierw uruchom silnik (E)\n");
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                if(okno.getZaplonCB().isSelected()) {
                    auto.skret(true);
                }else
                {
                    JOptionPane.showMessageDialog(okno,"Najpierw uruchom silnik (E)\n");
                }
            }

        }
        @Override
        public void keyReleased(KeyEvent e) {
            if((e.getKeyChar()=='E')||(e.getKeyChar()=='e'))
            {
                okno.changeZaplonRB();
                if (auto.isZaplon())
                {
                    auto.zgas_silnik();
                    okno.getZaplonCB().setText("ZAP£ON [E]");

                    okno.getTabelaModel().addRow(new Object[]{auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(0),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(1),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(2),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(3),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(4)});

                }
                else
                {
                    okno.getZaplonCB().setBackground(new Color(0,0,0,0));
                    okno.getZaplonCB().setText("ZGAŒ SILNIK [E]");
                    auto.uruchom_silnik();
                }
                okno.repaint();
            }
            if(e.getKeyChar()=='J' || e.getKeyChar()=='j')
            {
                okno.getPrzelacznik_swiatla_krotkie().doClick();
            }
            if(e.getKeyChar()=='K' || e.getKeyChar()=='k')
            {
                okno.getPrzelacznik_swiatla_dlugie().doClick();
            }
            if(e.getKeyChar()=='N' || e.getKeyChar()=='n')
            {
                okno.getPrzelacznik_swiatla_przeciwmgielne_p().doClick();
            }
            if(e.getKeyChar()=='M' || e.getKeyChar()=='m')
            {
                okno.getPrzelacznik_swiatla_przeciwmgielne_t().doClick();
            }
            if(e.getKeyChar()==KeyEvent.VK_ESCAPE)
            {
                try {
                    auto.zapiszGlownyLicznik("licznikGlowny.txt");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
            if(e.getKeyCode()==KeyEvent.VK_UP)
            {
                    auto.setGaz(false);
            }
        }
    }

    /**
     * Odpowiada za reakcje na akcje u¿ytkownika maj¹ce miejsce w obrêbie paska menu oraz w³¹czenie i wy³¹czenie œwiate³.
     * @param e Zród³o zdarzenia
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==okno.getTempomatButton())
        {
            try {
                double pr=0;
                String in="",ms="Ustaw predkosc";
                if (!auto.getTempomat().isWlaczony()){
                    in=JOptionPane.showInputDialog(ms);
                    pr=Double.parseDouble(in);
                }

                if (auto.getTempomat().isWlaczony()) {
                    auto.getTempomat().wylacz();
                    okno.getTempomatButton().setText("tempomat OFF");
                    okno.getTempomatButton().setBackground(Color.decode("#db2416"));

                } else if (((pr > auto.getPredkosciomierz().getMax_predkosc()) || ((pr <= 0)))) {
                    JOptionPane.showMessageDialog(this.okno, "Niepoprawna wartoœæ!!\nPodaj wartoœæ z przedzia³u od 0 do V-max typu liczbowego", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(!auto.isZaplon()) {
                        auto.uruchom_silnik();
                        okno.getZaplonCB().setText("ZGAŒ SILNIK [E]");
                    }
                    auto.getTempomat().wlacz(pr);
                    okno.getTempomatButton().setBackground(Color.decode("#3f9a2a"));
                    okno.getTempomatButton().setText("tempomat ON");
                }
            }catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.okno, "Niepoprawna wartoœæ!!\nPodaj wartoœæ z przedzia³u od 0 do V-max typu liczbowego", "WARNING", JOptionPane.WARNING_MESSAGE);
            }

        }
        if(source==okno.getResetPrzebiegButton())
        {
            try {
                auto.getLicznikUzytkownika().reset();
                okno.setLicznikUzytkownikaLabel(0);
            } catch (Exception ex) {
               System.out.println (ex.getMessage());
            }
        }
        if (source == okno.getMenuOProgramie()) {
            JOptionPane.showMessageDialog(this.okno, "strza³ka do góry-przyspieszanie\n" +
                    "strza³ka w dó³-hamowanie\n" +
                    "strza³ka w lewo/prawo-skrêcanie\n" +
                    "E-zgaœ/uruchom silnik");
        }
        if (source == okno.getMenuWczytajPodroze()) {
            String[] opt={"TAK","NIE"};
            int x = JOptionPane.showOptionDialog(okno, "Wczytanie innej historii podró¿y spowoduje utratê niezapisanej," +
                             "\n aktualnej historii podró¿y. Kontynuowaæ?","Uwaga", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt, opt[0]);
            if(x==0) {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.showDialog(okno,"Wybór pliku do odczytu");
            try {
                auto.wczytajPodroze(file.getSelectedFile().getAbsolutePath().toString());
                okno.setTableData(auto.getPrzejazdy());
                ////////////////////////////////////// Wyswietlanie wczytanych wiadomosci do tabeli na dole okna ////////////////////////////////////////////////////////////////////////
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(okno,"B³¹d pliku","Error!",JOptionPane.ERROR_MESSAGE);
            }}
        }
        if (source == okno.getMenuWczytajPodrozeZBazyDanych())
        {
            okno3.setMenu(0);
            //okno3.addZastosujButtonListener(new ZastosujButtonListener());
            okno3.setVisible(true);
            okno3.getZastosujButton().addActionListener(this);
        }
        if (source == okno.getMenuDodajPodrozeDoBazyDanych())
        {
            okno3.setMenu(1);
            //okno3.addZastosujButtonListener(new ZastosujButtonListener());
            okno3.setVisible(true);
            okno3.getZastosujButton().addActionListener(this);
        }
        if (source == okno.getMenuWyczyscBazeDanych())
        {
            okno3 = new OknoTextDataBase();
            okno3.setMenu(2);
            //okno3.addZastosujButtonListener(new ZastosujButtonListener());
            okno3.setVisible(true);
            okno3.getZastosujButton().addActionListener(this);
        }
        if(source == okno3.getZastosujButton())
        {
            try {
                if(okno3.getMenu()==1)
                {
                    ObslugaBazy tmp = new ObslugaBazy(okno3.getNazwa_bazy_danych());
                    tmp.dodajDoBazy(auto.getPrzejazdy());
                }
                else if(okno3.getMenu()==0)
                {
                    auto.wczytajPodrozeZBazy(okno3.getNazwa_bazy_danych());
                }
                else if(okno3.getMenu()==2)
                {
                    try {
                        ObslugaBazy tmp = new ObslugaBazy(okno3.getNazwa_bazy_danych());
                        tmp.wyczyscBaze();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            okno3.dispose();
            okno.setTableData(auto.getPrzejazdy());
        }
        if (source == okno.getMenuZapiszPodroze()) {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            file.showDialog(okno,"Wybór folderu do zapisu");
            auto.zapiszPodroze(file.getSelectedFile().getAbsolutePath().toString());
            }
        if (source == okno.getMenuWyjscie()) {
            String[] opt={"TAK","NIE"};
            int x = JOptionPane.showOptionDialog(okno, "Na pewno chcesz zamkn¹æ program? Wszelkie niezapisane dane przepadn¹.","Uwaga", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opt, opt[0]);
            if(x==0) {
                okno.dispose();}
            }
        if (source == okno.getPrzelacznik_swiatla_krotkie())
        {
            if(okno.getPrzelacznik_swiatla_krotkie().isSelected())
            {
                auto.getMijania().wlacz();
                okno.getSwiatla_krotkie().setVisible(true);
                okno.getPrzelacznik_swiatla_przeciwmgielne_p().setEnabled(true);
               if(!auto.getDrogowe().isWlaczone())
                   okno.ustawSwiatlo(1,auto.getMijania().getBarwa());
            }
            else
            {
                auto.getMijania().wylacz();
                okno.getSwiatla_krotkie().setVisible(false);
                okno.getPrzelacznik_swiatla_przeciwmgielne_p().setEnabled(false);
                if(!auto.getDrogowe().isWlaczone())
                    okno.ustawSwiatlo(0,auto.getMijania().getBarwa());
            }
        }
        if (source == okno.getPrzelacznik_swiatla_dlugie())
        {
            if(okno.getPrzelacznik_swiatla_dlugie().isSelected())
            {
                auto.getDrogowe().wlacz();
                okno.getSwiatla_dlugie().setVisible(true);
                okno.ustawSwiatlo(2,auto.getDrogowe().getBarwa());
            }
            else
            {
                auto.getDrogowe().wylacz();
                okno.getSwiatla_dlugie().setVisible(false);
                okno.ustawSwiatlo(0,auto.getDrogowe().getBarwa());
                if(auto.getMijania().isWlaczone())
                {
                    okno.ustawSwiatlo(1,auto.getMijania().getBarwa());
                }
            }

        }
        if(source == okno.getPrzelacznik_swiatla_przeciwmgielne_p())
        {
            if(okno.getPrzelacznik_swiatla_przeciwmgielne_p().isSelected())
            {
                auto.getPrzeciwmgielne_przod().wlacz();
                okno.getSwiatla_przeciwmgielne_p().setVisible(true);
                okno.getPrzelacznik_swiatla_krotkie().setEnabled(false);
                okno.getPrzelacznik_swiatla_przeciwmgielne_t().setEnabled(true);
            }
            else
            {
                auto.getPrzeciwmgielne_przod().wylacz();
                okno.getSwiatla_przeciwmgielne_p().setVisible(false);
                okno.getPrzelacznik_swiatla_krotkie().setEnabled(true);
                okno.getPrzelacznik_swiatla_przeciwmgielne_t().setEnabled(false);
            }
        }
        if(source == okno.getPrzelacznik_swiatla_przeciwmgielne_t())
        {
            if(okno.getPrzelacznik_swiatla_przeciwmgielne_t().isSelected())
            {
                auto.getPrzeciwmgielne_tyl().wlacz();
                okno.getSwiatla_przeciwmgielne_t().setVisible(true);
                okno.getPrzelacznik_swiatla_przeciwmgielne_p().setEnabled(false);
            }
            else
            {
                auto.getPrzeciwmgielne_tyl().wylacz();
                okno.getSwiatla_przeciwmgielne_t().setVisible(false);
                okno.getPrzelacznik_swiatla_przeciwmgielne_p().setEnabled(true);
            }
        }

//        class PredkoscListener implements PropertyChangeListener{
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
//            }
//        }
    }
    private class SpalanieRefresher implements ActionListener
    {
        private Timer timer;
        SpalanieRefresher(){
            timer=new Timer(500,this);
            timer.start();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            okno.setSpalanieLabel(auto.getKalkulator().getSrednieSpalanie());
        }
    }

    class PredkoscListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
            okno.setPasekPredkosciValue(auto.getPredkosciomierz().getPredkosc());
        }
    }

    class LicznikListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            okno.setLicznik1Label(auto.getLicznikPodrozy().getDystans());
            okno.setLicznik2Label(auto.getLicznikGlowny().getDystans());
            okno.setLicznikUzytkownikaLabel(auto.getLicznikUzytkownika().getDystans());
        }
    }

    class SwiatloListener implements  PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getSource()==auto.getMijania())
            {
                okno.getSwiatla_krotkie().setVisible(auto.getMijania().isWlaczone());
            }
            else if(evt.getSource()==auto.getLewyKierunkowskaz())
            {
                okno.getKierunkowskaz_l().setVisible(auto.getLewyKierunkowskaz().isWlaczone());
            }
            else if(evt.getSource()==auto.getPrawyKierunkowskaz())
            {
                okno.getKierunkowskaz_p().setVisible(auto.getPrawyKierunkowskaz().isWlaczone());
            }
            else if(evt.getSource()==auto.getDrogowe())
            {
                okno.getSwiatla_dlugie().setVisible(auto.getDrogowe().isWlaczone());
            }
        }
    }

    class OsiagiButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            okno2 = new OsiagiOkienko();
            okno2.addZastosujButtonListener(new ZastosujButtonListener());
            okno2.setVisible(true);
        }
    }

    class ZastosujButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            okno.setInfoLabel(okno2.getMocSilnika(),okno2.getMocHamulcow(),okno2.getMaxPredkosc());
            auto.setMoc_silnika(okno2.getMocSilnika());
            auto.setMoc_hamulcow(okno2.getMocHamulcow());
            auto.getPredkosciomierz().setMax_predkosc(okno2.getMaxPredkosc());
            auto.getMijania().setBarwa(okno2.getBarwaSwiatla());
            okno.setPasekPredkosciMaximum(okno2.getMaxPredkosc());
            if(okno.getPrzelacznik_swiatla_krotkie().isSelected())
                okno.ustawSwiatlo(1,auto.getMijania().getBarwa());
            okno2.dispose();

        }
    }


}
