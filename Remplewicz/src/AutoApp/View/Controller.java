package AutoApp.View;

import AutoApp.Model.Samochod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Samochod auto;
    private Okienko okno;

    public Controller(Samochod auto, Okienko okno)
    {
        this.auto = auto;
        this.okno = okno;
        this.okno.addGazListener(new GazListener());
        this.okno.addHamulecListener(new HamulecListener());

        okno.setInfoLabel(auto.getMoc_silnika(),auto.getMoc_hamulcow(),auto.getPredkosciomierz().getMax_predkosc());
        okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());
    }

    class GazListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            auto.gaz();
            okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());

        }
    }

    class HamulecListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            auto.hamulec();
            okno.setPredkoscLabel(auto.getPredkosciomierz().getPredkosc());

        }
    }

}
