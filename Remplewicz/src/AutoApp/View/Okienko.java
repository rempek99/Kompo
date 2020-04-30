package AutoApp.View;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Okienko extends JFrame implements PropertyChangeListener {

        public Okienko(Object controller)
        {
            setSize(300,300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            JLabel info = new JLabel("Moc silnika: ");
            info.addPropertyChangeListener(this);
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
}
