package AutoApp.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Dictionary;


public class OsiagiOkienko extends JFrame {

    private JSlider mocSlider;
    private JSlider hamulceSlider;
    private JSlider maxPredkoscSlider;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JPanel bottomPanel;
    private JLabel mocWartosc;
    private JLabel hamulceWartosc;
    private JLabel maxPredkoscWartosc;
    private JButton zastosujButton;
    private Dictionary<Integer,JLabel> mocLabel;

    public OsiagiOkienko() {
        this.setTitle("Zmieñ osi¹gi samochodu");
        this.setSize(new Dimension(500,300));
        setLayout(new FlowLayout());
        firstPanel = new JPanel();
        secondPanel = new JPanel();
        thirdPanel = new JPanel();
        mocWartosc = new JLabel("");
        mocWartosc.setPreferredSize(new Dimension(50,20));
        hamulceWartosc = new JLabel("");
        hamulceWartosc.setPreferredSize(new Dimension(50,20));
        mocSlider = new JSlider(JSlider.HORIZONTAL,10,220,115);
        mocWartosc.setText(Integer.toString(mocSlider.getValue()));
        mocSlider.setMajorTickSpacing(10);
        mocSlider.setLabelTable(mocSlider.createStandardLabels(30,10));
        mocSlider.setPaintTicks(true);
        mocSlider.setPaintLabels(true);
        mocSlider.addChangeListener(new WartoscChangeListener());

        firstPanel.add(new Label("Moc silnika: "));
        firstPanel.add(mocWartosc);
        firstPanel.add(mocSlider);

        hamulceSlider = new JSlider(JSlider.HORIZONTAL,0,100,10);
        hamulceSlider.setMajorTickSpacing(10);
        hamulceSlider.setLabelTable(hamulceSlider.createStandardLabels(20,0));
        hamulceSlider.setPaintTicks(true);
        hamulceSlider.setPaintLabels(true);
        hamulceSlider.addChangeListener(new WartoscChangeListener());
        hamulceWartosc.setText(Integer.toString(hamulceSlider.getValue())+'%');
        secondPanel.add(new Label("Moc hamulców: "));
        secondPanel.add(hamulceWartosc);
        secondPanel.add(hamulceSlider);

        maxPredkoscSlider = new JSlider(JSlider.HORIZONTAL,10,300,210);
        maxPredkoscSlider.setMajorTickSpacing(10);
        maxPredkoscSlider.setLabelTable(maxPredkoscSlider.createStandardLabels(70,10));
        maxPredkoscSlider.setPaintTicks(true);
        maxPredkoscSlider.setPaintLabels(true);
        maxPredkoscSlider.addChangeListener(new WartoscChangeListener());
        maxPredkoscWartosc = new JLabel("");
        maxPredkoscWartosc.setPreferredSize(new Dimension(50,20));
        maxPredkoscWartosc.setText(Integer.toString(maxPredkoscSlider.getValue())+"km/h");
        thirdPanel.add(new Label("Max prêdkoœæ: "));
        thirdPanel.add(maxPredkoscWartosc);
        thirdPanel.add(maxPredkoscSlider);


        zastosujButton = new JButton("ZASTOSUJ");
        zastosujButton.setPreferredSize(new Dimension(100,30));
        bottomPanel = new JPanel();
        bottomPanel.add(zastosujButton);


        this.add(firstPanel);
        this.add(secondPanel);
        this.add(thirdPanel);
        this.add(bottomPanel);


    }

    class WartoscChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource()==mocSlider)
            {
                mocWartosc.setText(Integer.toString(mocSlider.getValue()));
            }
            else if(e.getSource()==hamulceSlider)
            {
                hamulceWartosc.setText(Integer.toString(hamulceSlider.getValue())+'%');
            }
            else if(e.getSource()==maxPredkoscSlider)
            {
                maxPredkoscWartosc.setText(Integer.toString(maxPredkoscSlider.getValue())+"km/h");
            }
        }
    }
    void addZastosujButtonListener(ActionListener zastosujButtonListener)
    {
        zastosujButton.addActionListener(zastosujButtonListener);
    }
    public int getMocSilnika()
    {
        return mocSlider.getValue();
    }
    public int getMocHamulcow()
    {
        return hamulceSlider.getValue();
    }
    public int getMaxPredkosc()
    {
        return maxPredkoscSlider.getValue();
    }
}
