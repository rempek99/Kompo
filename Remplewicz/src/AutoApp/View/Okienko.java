package AutoApp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class Okienko extends JFrame {

        private JLabel infoLabel = new JLabel("Moc silnika: ");
        private JLabel predkoscLabel = new JLabel("Predkosc: ");
        private JButton gazButton = new JButton("GAZ");
        private JButton hamulecButton = new JButton("HAMULEC");
        private JCheckBox zaplonChB = new JCheckBox("ZAPŁON");

        private static DecimalFormat df = new DecimalFormat("0.00");

        public Okienko()
        {
            this.setTitle("AutoApp");
            setLayout(new BorderLayout());
            JPanel topPanel = new JPanel();

            setSize(500,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            topPanel.add(infoLabel,BorderLayout.NORTH);
            topPanel.add(predkoscLabel,BorderLayout.NORTH);
            topPanel.add(gazButton,BorderLayout.CENTER);
            topPanel.add(hamulecButton,BorderLayout.CENTER);
            zaplonChB.setBounds(450,280,50,20);
            topPanel.add(zaplonChB,BorderLayout.SOUTH);

            this.add(topPanel);
            //setVisible(true);
        }

    public void setPredkoscLabel(float predkosc) {
        this.predkoscLabel.setText("Predkosc: " + df.format(predkosc));
    }

    public void setInfoLabel(int moc, int mocHamulcow, int maxPredkosc) {
        this.infoLabel.setText("Moc silnika: " + Integer.toString(moc) +
                " Moc hamulców: " + Integer.toString(mocHamulcow) + "%"+
                " Max Prędkość: " + Integer.toString(maxPredkosc) + "km/h");
    }
    void addGazListener(ActionListener gazListener)
    {
        gazButton.addActionListener(gazListener);
    }
    void addHamulecListener(ActionListener hamulecListener)
    {
        hamulecButton.addActionListener(hamulecListener);
    }
}
