import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator {
    private JPanel panelMain;
    private JTextField textFieldNumber1;
    private JTextField textFieldNumber2;
    private JTextField textFieldResult;
    private JButton subtractionButton;
    private JButton multiplicationButton;
    private JButton sumButton;
    private JButton divisionButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Calculator() {
    textFieldNumber1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && e.getKeyChar() != '.')) {
                e.consume();
            }
        }
    });
    textFieldNumber2.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && e.getKeyChar() != '.')) {
                e.consume();
            }
        }
    });
    sumButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                double number1 = Double.parseDouble(textFieldNumber1.getText());
                double number2 = Double.parseDouble(textFieldNumber2.getText());
                double result = 0;

                result = number1 + number2;

                textFieldResult.setText("");
                textFieldResult.setText(Double.toString(result));

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(panelMain, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    });
    subtractionButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                double number1 = Double.parseDouble(textFieldNumber1.getText());
                double number2 = Double.parseDouble(textFieldNumber2.getText());
                double result = 0;

                result = number1 - number2;

                textFieldResult.setText("");
                textFieldResult.setText(Double.toString(result));

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(panelMain, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    });
    divisionButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                double number1 = Double.parseDouble(textFieldNumber1.getText());
                double number2 = Double.parseDouble(textFieldNumber2.getText());
                double result = 0;

                if(number2 == 0){
                    JOptionPane.showMessageDialog(panelMain, "Undefined, dividing by 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                result = number1 / number2;

                textFieldResult.setText("");
                textFieldResult.setText(Double.toString(result));

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(panelMain, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    });
    multiplicationButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                double number1 = Double.parseDouble(textFieldNumber1.getText());
                double number2 = Double.parseDouble(textFieldNumber2.getText());
                double result = 0;

                result = number1 * number2;

                textFieldResult.setText("");
                textFieldResult.setText(Double.toString(result));

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(panelMain, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    });
}
}