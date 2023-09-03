import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JPanel panel1;
    private JTextField fahrenheitTF;
    private JButton fToCButton;
    private JButton cToFButton;
    private JTextField celciusTF;
    private JButton exitButton;

    public TemperatureConverter() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cToFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fahrenheitTF.setText("");
                fahrenheitTF.setText(Double.toString((((Double.parseDouble(celciusTF.getText())) * (9d/5d) ) + 32d)));
            }
        });

        fToCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                celciusTF.setText("");
                celciusTF.setText(Double.toString((((Double.parseDouble(fahrenheitTF.getText())) - 32d ) * (5d/9d))));
            }
        });
    }

    public static void main(String[] args) {
        TemperatureConverter t = new TemperatureConverter();
        t.setContentPane(t.panel1);
        t.setSize(400, 200);
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
