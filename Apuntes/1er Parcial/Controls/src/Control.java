import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control {


    private JPanel panelMain;
    private JPanel panel1;
    private JTextField textFieldName;
    private JSpinner spinnerAge;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JCheckBox readCheckBox;
    private JCheckBox goToCinemaCheckBox;
    private JComboBox comboBoxCivilStatus;
    private JPanel panelBottom;
    private JButton disableButton;
    private JButton addButton;
    private JTextArea textArea1;

    private boolean enabled = true;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Control");
        frame.setContentPane(new Control().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,400));
        frame.pack();
        frame.setVisible(true);
    }


    public Control() {
        disableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(enabled){
                    disableButton.setText("Enable");
                    enabled = false;
                }else{
                    disableButton.setText("Disabled");
                    enabled = true;
                }

                textArea1.setEnabled(enabled);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");

                String text = ("Your name is: " + textFieldName.getText() + "\n") + ("You're: " + spinnerAge.getValue() + " years old\n");

                if(maleRadioButton.isSelected()){
                    text += ("Your gender is: male\n");
                }else{
                    text += ("Your gender is: female\n");
                }

                text += ("Your hobbies: ");
                if(goToCinemaCheckBox.isSelected()){
                    text += ("go to cinema");
                }

                if (readCheckBox.isSelected()) {
                    text += (" read");
                }

                text += ("\n");

                String civilStatus = comboBoxCivilStatus.getSelectedItem().toString();

                textArea1.append(text + "Your civil status is: " + civilStatus + "\n");

            }
        });
    }


    private void createUIComponents() {

        // Create a SpinnerModel that enforces the constraint
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        spinnerAge = new JSpinner(spinnerModel);
        JFormattedTextField textField = ((JSpinner.DefaultEditor) spinnerAge.getEditor()).getTextField();

        // Add a change listener to the spinner to handle invalid inputs
        spinnerAge.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) spinnerAge.getValue();
                if (value < 0) {
                    spinnerAge.setValue(0); // Set it to the minimum value
                }
            }
        });


    }
}
