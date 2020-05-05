package AutoApp.View;

import AutoApp.Model.Nieuruchomiony;
import AutoApp.Model.Samochod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//
public class Controller implements ActionListener{

    private Samochod auto;
    private Okienko okno;

    public Controller(Samochod auto, Okienko okno) {
        this.auto = auto;
        this.okno = okno;
        this.auto.getPredkosciomierz().addPredkoscListener(new PredkoscListener());
        this.okno.addKeyListener(new KeyboarListner());
        this.okno.setFocusable(true);
        this.okno.setInfoLabel(auto.getMoc_silnika(), auto.getMoc_hamulcow(), auto.getPredkosciomierz().getMax_predkosc());
        this.okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
        this.okno.menuOProgramie.addActionListener(this);
        this.okno.menuWyjscie.addActionListener(this);
        this.okno.menuWczytajPodroze.addActionListener(this);
        this.okno.menuZapiszPodroze.addActionListener(this);
    }

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
                if (auto.isZaplon()) {
                    auto.zgas_silnik();
                    okno.getZaplonCB().setText("ZAP£ON [E]");

                    okno.getTabelaModel().addRow(new Object[]{auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(0),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(1),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(2),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(3),
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).stringData(4)});

//                    okno.getPrzejazdyText().setText(okno.getPrzejazdyText().getText() +
//                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).toString());
                } else {
                    okno.getZaplonCB().setBackground(new Color(0,0,0,0));
                    okno.getZaplonCB().setText("ZGAŒ SILNIK [E]");
                    auto.uruchom_silnik();
                }
            }
            if(e.getKeyChar()==KeyEvent.VK_ESCAPE)
            {
                okno.dispose();
            }
        }
    }

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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(okno,"B³¹d pliku","Error!",JOptionPane.ERROR_MESSAGE);
            }}
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
        class PredkoscListener implements PropertyChangeListener{

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
            }
        }
    }

    class PredkoscListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
        }
    }
}
