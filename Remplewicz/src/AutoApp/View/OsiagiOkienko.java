package AutoApp.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Dictionary;



/**
 * Tworzy okno dialogowe, kt�re umozliwia zmian� parametr�w samochodu
 * @author Arkadiusz Remplewicz
 * @author Dawid Jakubik
 *
 */
public class OsiagiOkienko extends JFrame {
    /**
     * Umo�liwia zmian� mocy silnika Samochodu
     */
    private JSlider mocSlider;
    /**
     * Umo�liwia zmian� skuteczno�ci hamulcy Samochodu
     */
    private JSlider hamulceSlider;
    /**
     * Umo�liwia zmian� maksymalnej pr�dko�ci kt�r� mo�e osi�gn�� Samoch�d
     */
    private JSlider maxPredkoscSlider;
    /**
     * Zawiera suwak do zmiany mocy silnika wraz z opisem
     */
    private JPanel firstPanel;
    /**
     * Zawiera suwak do zmiany skuteczno�ci hamulc�w wraz z opisem
     */
    private JPanel secondPanel;
    /**
     * Zawiera suwak do zmiany maksymalnej pr�dko�ci wraz z opisem
     */
    private JPanel thirdPanel;
    /**
     * Panel z przyciskiem "ZASTOSUJ"
     */
    private JPanel bottomPanel;
    /**
     * Odpowiada za wyswietlanie aktualnej warto�ci liczbowej wskazanej przez mocSlider
     */
    private JLabel mocWartosc;
    /**
     * Odpowiada za wyswietlanie aktualnej warto�ci liczbowej wskazanej przez hamulceSlider
     */
    private JLabel hamulceWartosc;
    /**
     * Odpowiada za wyswietlanie aktualnej warto�ci liczbowej wskazanej przez maxPredkoscSlider
     */
    private JLabel maxPredkoscWartosc;
    /**
     * Przycisk s�u��cy do akceptacji wprowadzonych zmian
     */
    private JButton zastosujButton;
    private Dictionary<Integer,JLabel> mocLabel;

    /**
     * Konstruktor bezparametrowy. Inicjuje zawarto�� okna.
     */
    public OsiagiOkienko() {
        this.setTitle("Zmie� osi�gi samochodu");
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
        secondPanel.add(new Label("Moc hamulc�w: "));
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
        thirdPanel.add(new Label("Max pr�dko��: "));
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

    /**
     * Klasa odpowiadaj�ca za reakcje na wprowadzenie zmian w interfejsie poprzez suwaki przez u�ytkownika.
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
     * @return wartosc typu int zale�n� od aktualnego ustawienia suwaka mocSlider
     */
    public int getMocSilnika()
    {
        return mocSlider.getValue();
    }

    /**
     * Standardowy getter do wartosci wskazywanej przez hamulceSlider
     * @return wartosc typu int zale�n� od aktualnego ustawienia suwaka hamulceSlider
     */
    public int getMocHamulcow()
    {
        return hamulceSlider.getValue();
    }
    /**
     * Standardowy getter do wartosci wskazywanej przez maxPredkoscSlider
     * @return wartosc typu int zale�n� od aktualnego ustawienia suwaka hamulceSlider
     */
    public int getMaxPredkosc()
    {
        return maxPredkoscSlider.getValue();
    }
}
