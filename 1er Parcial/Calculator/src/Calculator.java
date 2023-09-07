import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Calculator {
    private JPanel panelMain;
    private JPanel panel1;
    private JPanel panel3;
    private JButton calculateButton;
    private JButton exitButton;
    private JPanel panel2;
    private JTextField textFieldNumber1;
    private JTextField textFieldNumber2;
    private JTextField textFieldResult;
    private JRadioButton sumRadioButton;
    private JRadioButton subtractionRadioButton;
    private JRadioButton divisionRadioButton;
    private JRadioButton multiplicationRadioButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Calculator() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    double number1 = Double.parseDouble(textFieldNumber1.getText());
                    double number2 = Double.parseDouble(textFieldNumber2.getText());
                    double result = 0;

                    if(sumRadioButton.isSelected()){
                        result = number1 + number2;
                    }else if(subtractionRadioButton.isSelected()){
                        result = number1 - number2;
                    } else if (multiplicationRadioButton.isSelected()) {
                        result = number1 * number2;
                    }else{
                        if(number2 == 0){
                            JOptionPane.showMessageDialog(panelMain, "Undefined, dividing by 0", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        result = number1 / number2;
                    }

                    textFieldResult.setText("");
                    textFieldResult.setText(Double.toString(result));

                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(panelMain, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
    }
}
