import javax.swing.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {

    private JTextField firstNumber = new JTextField(10);
    private JLabel additionLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);


    CalculatorView(){

        JPanel calcPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);

        this.add(calcPanel);

    }

    //Uzytkownik wprowadza wartosci do policzenia do View
    public int getFirstNumber() {
        return Integer.parseInt(firstNumber.getText());
    }

    //Model po wykonaniu obliczenia przekazuje obliczona wartość do View, aby wyswietlic ja uzytkownikowi
    public void setCalcSolution(int calcSolution) {
        this.calcSolution.setText(Integer.toString(calcSolution));
    }


    public int getCalcSolution() {
        return Integer.parseInt(calcSolution.getText());
    }

    public int getSecondNumber() {
        return Integer.parseInt(secondNumber.getText());
    }

    void addCalculationListener(ActionListener listenerForCalcButton)
    {
        calculateButton.addActionListener(listenerForCalcButton);
    }

    void displayErrorMessage(String errorMessage)
    {
        JOptionPane.showMessageDialog(this,errorMessage);
    }
}
