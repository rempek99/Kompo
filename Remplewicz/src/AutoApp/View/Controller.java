package AutoApp.View;

import AutoApp.Data.ObslugaBazy;
import AutoApp.Model.Nieuruchomiony;
import AutoApp.Model.Samochod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class Controller implements ActionListener{
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
        okno3=new OknoTextDataBase();
        this.auto = auto;
        this.okno = okno;
        this.okno3 = new OknoTextDataBase();
        this.auto.getPredkosciomierz().addPredkoscListener(new PredkoscListener());
        this.auto.getLicznik1().addLicznikListener(new LicznikListener());
        this.auto.getLicznikGlowny().addLicznikListener(new LicznikListener());
        this.okno.setLicznik1Label(auto.getLicznik1().getDystans());
        this.okno.setLicznik2Label(auto.getLicznikGlowny().getDystans());
        this.okno.getPrzelacznik_swiatla_krotkie().addActionListener(this);
        this.okno.getPrzelacznik_swiatla_dlugie().addActionListener(this);
        this.auto.getMijania().addSwiatloListener(new SwiatloListener());
        this.auto.getLewyKierunkowskaz().addSwiatloListener(new SwiatloListener());
        this.auto.getPrawyKierunkowskaz().addSwiatloListener(new SwiatloListener());
        this.auto.getDrogowe().addSwiatloListener(new SwiatloListener());
        this.okno.addKeyListener(new KeyboarListner());
        this.okno.setFocusable(true);
        this.okno.setInfoLabel(auto.getMoc_silnika(), auto.getMoc_hamulcow(), auto.getPredkosciomierz().getMax_predkosc());
        this.okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
        this.okno.menuOProgramie.addActionListener(this);
        this.okno.menuWyjscie.addActionListener(this);
        this.okno.menuWczytajPodroze.addActionListener(this);
        this.okno.menuWczytajPodrozeZBazyDanych.addActionListener(this);
        this.okno.menuDodajPodrozeDoBazyDanych.addActionListener(this);
        this.okno.menuZapiszPodroze.addActionListener(this);
        this.okno.menuWyczyscBazeDanych.addActionListener(this);
        this.okno.addOsiagiButtonListener(new OsiagiButtonListener());
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
                    auto.gaz();
                    auto.setGaz(true);

                }catch(Nieuruchomiony ex)
                {
                    JOptionPane.showMessageDialog(okno,"Najpierw uruchom silnik (E)\n");
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
            {
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
            if(e.getKeyChar()==KeyEvent.VK_ESCAPE)
            {
                okno.dispose();
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
        if (source == okno.menuOProgramie) {
            JOptionPane.showMessageDialog(this.okno, "strza³ka do góry-przyspieszanie\n" +
                    "strza³ka w dó³-hamowanie\n" +
                    "strza³ka w lewo/prawo-skrêcanie\n" +
                    "E-zgaœ/uruchom silnik");
        }
        if (source == okno.menuWczytajPodroze) {
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
        if (source == okno.menuWczytajPodrozeZBazyDanych)
        {
            okno3.setMenu(0);
            //okno3.addZastosujButtonListener(new ZastosujButtonListener());
            okno3.setVisible(true);
            okno3.getZastosujButton().addActionListener(this);
        }
        if (source == okno.menuDodajPodrozeDoBazyDanych)
        {
            okno3.setMenu(1);
            //okno3.addZastosujButtonListener(new ZastosujButtonListener());
            okno3.setVisible(true);
            okno3.getZastosujButton().addActionListener(this);
        }
        if (source == okno.menuWyczyscBazeDanych)
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
        if (source == okno.menuZapiszPodroze) {
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            file.showDialog(okno,"Wybór folderu do zapisu");
            auto.zapiszPodroze(file.getSelectedFile().getAbsolutePath().toString());
            }
        if (source == okno.menuWyjscie) {
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
               if(!auto.getDrogowe().isWlaczone())
                   okno.ustawSwiatlo(1,auto.getMijania().getBarwa());
            }
            else
            {
                auto.getMijania().wylacz();
                okno.getSwiatla_krotkie().setVisible(false);
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

//        class PredkoscListener implements PropertyChangeListener{
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
//            }
//        }
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
            okno.setLicznik1Label(auto.getLicznik1().getDystans());
            okno.setLicznik2Label(auto.getLicznikGlowny().getDystans());
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
            if(okno.przelacznik_swiatla_krotkie.isSelected())
                okno.ustawSwiatlo(1,auto.getMijania().getBarwa());
            okno2.dispose();

        }
    }


}
