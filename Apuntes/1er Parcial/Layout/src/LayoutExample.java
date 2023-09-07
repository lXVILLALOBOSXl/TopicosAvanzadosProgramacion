import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LayoutExample {
    private JPanel panel1;
    private JPanel JPanel1;
    private JButton calculateButton;
    private JButton exitButton;
    private JTextField resultTextField;
    private JTextField numberTextField;
    private JRadioButton cuadradoRadioButton;
    private JRadioButton cubeRadioButton;
    private JPanel JPanel2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Degrees Converter");
        frame.setContentPane(new LayoutExample().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public LayoutExample() {

        calculateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    double number = Double.parseDouble(numberTextField.getText());

                    if (cuadradoRadioButton.isSelected()) {
                        number = Math.pow(number, 2);
                    } else {
                        number = Math.pow(number, 3);
                    }

                    resultTextField.setText("");
                    resultTextField.setText(Double.toString(number));
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(panel1, "Invalid format number", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        numberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && e.getKeyChar() != '.')){
                    e.consume();
                }
            }
        });

        resultTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9' && e.getKeyChar() != '.')){
                    e.consume();
                }
            }
        });
    }

}
