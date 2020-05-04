package AutoApp.View;

import AutoApp.Model.Nieuruchomiony;
import AutoApp.Model.Samochod;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class Controller {

    private Samochod auto;
    private Okienko okno;

    public Controller(Samochod auto, Okienko okno) {
        this.auto = auto;
        this.okno = okno;
        this.auto.getPredkosciomierz().addPredkoscListener(new PredkoscListener());
        //this.okno.addGazListener(new GazListener());
        //this.okno.addHamulecListener(new HamulecListener());
        this.okno.addKeyListener(new KeyboarListner());
        this.okno.setFocusable(true);
        okno.setInfoLabel(auto.getMoc_silnika(), auto.getMoc_hamulcow(), auto.getPredkosciomierz().getMax_predkosc());
        okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
    }

    class KeyboarListner implements KeyListener {
        public KeyboarListner() {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //not used
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                try {
                    auto.gaz();
                } catch (Nieuruchomiony ex) {
                    okno.getZaplonCB().setBackground(Color.red);
                }
                //okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                auto.hamulec();
                //okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                auto.skret(false);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                auto.skret(true);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if ((e.getKeyChar() == 'E') || (e.getKeyChar() == 'e')) {
                okno.changeZaplonRB();
                if (auto.isZaplon()) {
                    auto.zgas_silnik();
                    okno.getZaplonCB().setText("ZAP£ON [E]");
                    okno.getPrzejazdyText().setText(okno.getPrzejazdyText().getText() +
                            auto.getPrzejazdy().get(auto.getPrzejazdy().size()-1).toString());
                } else {
                    okno.getZaplonCB().setBackground(new Color(0,0,0,0));
                    okno.getZaplonCB().setText("ZGAŒ SILNIK [E]");
                    auto.uruchom_silnik();
                }
            }
            if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                okno.dispose();
            }
        }
    }

    class GazListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                auto.gaz();
            } catch (Nieuruchomiony ex) {
                okno.getZaplonCB().setBackground(Color.YELLOW);
            }
        }
    }

    class HamulecListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            auto.hamulec();
        }
    }

    class PredkoscListener implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
        }
    }
}
