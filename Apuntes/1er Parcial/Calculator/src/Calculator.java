import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel Calculator;
    private JTextField displayTextField;
    private JButton ACButton;
    private JButton timesButton;
    private JButton minusButton;
    private JButton plusButton;
    private JButton equalButton;
    private JButton invertButton;
    private JButton sevenButton;
    private JButton fourButton;
    private JButton oneButton;
    private JButton zeroButton;
    private JButton percentajeButton;
    private JButton eightButton;
    private JButton fiveButton;
    private JButton twoButton;
    private JButton divideButton;
    private JButton nineButton;
    private JButton sixButton;
    private JButton threeButton;
    private JButton pointButton;
    private JButton deleteButton;
    private static final String AC_MODE = "AC";

    private static final String C_MODE = "C";

    private static boolean isOperating;
    private static String givenNumber;
    private static String operation;
    private static Double total;


    private void setMode(String mode, String character) {

        if (mode.equals(AC_MODE)) {
            isOperating = false;
            ACButton.setLabel(AC_MODE);
            givenNumber = "";
            operation = "";
            total = null;
            displayTextField.setText("0");
        } else if (mode.equals(C_MODE)) {
            isOperating = true;
            ACButton.setLabel(C_MODE);
            givenNumber = character;
        }
    }

    private void operate() {

        if (!isOperating) {
            setMode(AC_MODE, "");
            return;
        }

        if (total == null) {
            total = Double.parseDouble(givenNumber);
        } else {
            if (!givenNumber.equals("")) {
                setResult();
            }
        }

        displayTextField.setText(Double.toString(total));
        givenNumber = "";
    }

    private void setResult() {
        switch (operation) {
            case "+":
                total += Double.parseDouble(givenNumber);
                break;
            case "-":
                total -= Double.parseDouble(givenNumber);
                break;
            case "*":
                total *= Double.parseDouble(givenNumber);
                break;
            case "/":
                total /= Double.parseDouble(givenNumber);
                break;
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Calculator() {
        ACButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMode(AC_MODE, "");
            }
        });
        invertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(AC_MODE, "");
                    return;
                }
                if(!givenNumber.equals("")){
                    total = Double.parseDouble(givenNumber) * -1d;
                }else{
                    total *= -1d;
                }
                displayTextField.setText(Double.toString(total));
            }
        });

        percentajeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(AC_MODE, "");
                    return;
                }
                if(!givenNumber.equals("")){
                    total = Double.parseDouble(givenNumber) / 100d;
                }else{
                    total /= 100d;
                }
                displayTextField.setText(Double.toString(total));
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operate();
                operation = "/";

            }
        });

        timesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operate();
                operation = "*";
            }
        });

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operate();
                operation = "-";

            }
        });

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operate();
                operation = "+";
            }
        });

        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(AC_MODE, "");
                    return;
                }

                operate();
            }
        });

        pointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "");
                    displayTextField.setText("0.");
                    givenNumber += "0.";
                    return;
                }

                if(givenNumber.toString().contains(".")){
                    return;
                }

                if (!givenNumber.equals("")) {
                    givenNumber += ".";
                } else {
                    givenNumber += "0.";
                }

                displayTextField.setText(givenNumber);

            }
        });

        zeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "");
                    displayTextField.setText("0");
                    return;
                }

                if (!givenNumber.equals("")) {
                    givenNumber += "0";
                } else {
                    displayTextField.setText("0");
                    return;
                }

                displayTextField.setText(givenNumber);
            }
        });

        oneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "1");
                } else {
                    givenNumber += "1";
                }

                displayTextField.setText(givenNumber);
            }
        });

        twoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "2");
                } else {
                    givenNumber += "2";
                }

                displayTextField.setText(givenNumber);

            }
        });

        threeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "3");
                } else {
                    givenNumber += "3";
                }

                displayTextField.setText(givenNumber);

            }
        });

        fourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "4");
                } else {
                    givenNumber += "4";
                }

                displayTextField.setText(givenNumber);

            }
        });

        fiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "5");
                } else {
                    givenNumber += "5";
                }

                displayTextField.setText(givenNumber);
            }
        });

        sixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "6");
                } else {
                    givenNumber += "6";
                }

                displayTextField.setText(givenNumber);

            }
        });

        sevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "7");
                } else {
                    givenNumber += "7";
                }

                displayTextField.setText(givenNumber);

            }
        });

        eightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "8");
                } else {
                    givenNumber += "8";
                }

                displayTextField.setText(givenNumber);

            }
        });

        nineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isOperating) {
                    setMode(C_MODE, "9");
                } else {
                    givenNumber += "9";
                }

                displayTextField.setText(givenNumber);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isOperating) {
                    setMode(AC_MODE, "");
                    return;
                } else {
                    total = null;
                    if(!givenNumber.equals("")){
                        givenNumber = givenNumber.substring(0, givenNumber.length() - 1);
                    }else {
                        givenNumber = displayTextField.getText().substring(0, displayTextField.getText().length() - 1);
                    }
                }

                displayTextField.setText(givenNumber);
            }
        });
    }
}