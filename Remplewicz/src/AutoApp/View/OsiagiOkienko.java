package AutoApp.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Dictionary;



/**
 * Tworzy okno dialogowe, które umozliwia zmianê parametrów samochodu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 */
public class OsiagiOkienko extends JFrame {
    /**
     * Umo¿liwia zmianê mocy silnika Samochodu
     */
    private JSlider mocSlider;
    /**
     * Umo¿liwia zmianê skutecznoœci hamulcy Samochodu
     */
    private JSlider hamulceSlider;
    /**
     * Umo¿liwia zmianê maksymalnej prêdkoœci któr¹ mo¿e osi¹gn¹æ Samochód
     */
    private JSlider maxPredkoscSlider;
    /**
     * Zawiera suwak do zmiany mocy silnika wraz z opisem
     */
    private JPanel firstPanel;
    /**
     * Zawiera suwak do zmiany skutecznoœci hamulców wraz z opisem
     */
    private JPanel secondPanel;
    /**
     * Zawiera suwak do zmiany maksymalnej prêdkoœci wraz z opisem
     */
    private JPanel thirdPanel;
    /**
     * Zawiera przycisni umo¿liwiaj¹ce zmianê barwy œwiate³ mijania
     */
    private JPanel fourthPanel;
    /**
     * Panel z przyciskiem "ZASTOSUJ"
     */
    private JPanel bottomPanel;
    /**
     * Odpowiada za wyswietlanie aktualnej wartoœci liczbowej wskazanej przez mocSlider
     */
    private JLabel mocWartosc;
    /**
     * Odpowiada za wyswietlanie aktualnej wartoœci liczbowej wskazanej przez hamulceSlider
     */
    private JLabel hamulceWartosc;
    /**
     * Odpowiada za wyswietlanie aktualnej wartoœci liczbowej wskazanej przez maxPredkoscSlider
     */
    private JLabel maxPredkoscWartosc;
    /**
     * Przycisk s³u¿¹cy do akceptacji wprowadzonych zmian
     */
    private JButton zastosujButton;
    /**
     * Przyciski odpowiadaj¹ce za wybór barwy œwiate³ mijania
     */
    private JRadioButton swiatloZolte, swiatloNiebieskie, swiatloFioletowe;
    /**
     * Konstruktor bezparametrowy. Inicjuje zawartoœæ okna.
     */
    public OsiagiOkienko() {
        this.setTitle("Zmieñ osi¹gi samochodu");
        this.setSize(new Dimension(500,300));
        setLayout(new FlowLayout());
        firstPanel = new JPanel();
        secondPanel = new JPanel();
        thirdPanel = new JPanel();
        fourthPanel = new JPanel();
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

        swiatloFioletowe = new JRadioButton("FIOLETOWE");
        swiatloNiebieskie = new JRadioButton("NIEBIESKIE");
        swiatloZolte = new JRadioButton("¯Ó£TE");
        swiatloNiebieskie.setSelected(false);
        swiatloFioletowe.setSelected(false);
        swiatloZolte.setSelected(true);
        ButtonGroup grupa = new ButtonGroup();
        grupa.add(swiatloZolte);
        grupa.add(swiatloFioletowe);
        grupa.add(swiatloNiebieskie);
        fourthPanel.add(new JLabel("Wybierz kolor œwiate³ mijania:"));
        fourthPanel.add(swiatloFioletowe);
        fourthPanel.add(swiatloZolte);
        fourthPanel.add(swiatloNiebieskie);


        zastosujButton = new JButton("ZASTOSUJ");
        zastosujButton.setPreferredSize(new Dimension(100,30));
        bottomPanel = new JPanel();
        bottomPanel.add(zastosujButton);


        this.add(firstPanel);
        this.add(secondPanel);
        this.add(thirdPanel);
        this.add(fourthPanel);
        this.add(bottomPanel);


    }

    /**
     * Klasa odpowiadaj¹ca za reakcje na wprowadzenie zmian w interfejsie poprzez suwaki przez u¿ytkownika.
     */
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
    /**
     * Umozliwia dodanie ActionListener do przycisku "zastosujButton"
     * @see ActionListener
     * @see JButton
     * @param zastosujButtonListener-zostanie przypisany do przycisku reprezentowanego przez pole "zastosujButton"
     */
    void addZastosujButtonListener(ActionListener zastosujButtonListener)
    {
        zastosujButton.addActionListener(zastosujButtonListener);
    }
    /**
     * Standardowy getter do wartosci wskazywanej przez mocSlider
     * @return wartosc typu int zale¿n¹ od aktualnego ustawienia suwaka mocSlider
     */
    public int getMocSilnika()
    {
        return mocSlider.getValue();
    }

    /**
     * Standardowy getter do wartosci wskazywanej przez hamulceSlider
     * @return wartosc typu int zale¿n¹ od aktualnego ustawienia suwaka hamulceSlider
     */
    public int getMocHamulcow()
    {
        return hamulceSlider.getValue();
    }
    /**
     * Standardowy getter do wartosci wskazywanej przez maxPredkoscSlider
     * @return wartosc typu int zale¿n¹ od aktualnego ustawienia suwaka hamulceSlider
     */
    public int getMaxPredkosc()
    {
        return maxPredkoscSlider.getValue();
    }
    public Color getBarwaSwiatla() {
        if(swiatloFioletowe.isSelected())
            return new Color(255,0,255);
        else if(swiatloNiebieskie.isSelected())
            return new Color (0,0,255);
        else
            return new Color(255,255,0);
    }
}
