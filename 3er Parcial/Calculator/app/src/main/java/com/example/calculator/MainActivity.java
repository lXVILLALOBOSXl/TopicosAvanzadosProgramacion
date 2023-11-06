package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewScreen;
    Button buttonC;

    private static final String AC_MODE = "AC";

    private static final String C_MODE = "C";

    private static boolean isOperating;
    private static String givenNumber;
    private static String operation;
    private static Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScreen = findViewById(R.id.textViewScreen);
        buttonC = findViewById(R.id.buttonC);
    }

    private void setMode(String mode, String character) {

        if (mode.equals(AC_MODE)) {
            isOperating = false;
            buttonC.setText(AC_MODE);
            givenNumber = "";
            operation = "";
            total = null;
            textViewScreen.setText("0");
        } else if (mode.equals(C_MODE)) {
            isOperating = true;
            buttonC.setText(C_MODE);
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

        textViewScreen.setText(Double.toString(total));
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

    public void buttonCAction(View view) {
        setMode(AC_MODE, "");
    }

    public void buttonPlusMinusAction(View view) {
        if (!isOperating) {
            setMode(AC_MODE, "");
            return;
        }
        if(!givenNumber.equals("")){
            total = Double.parseDouble(givenNumber) * -1d;
        }else{
            total *= -1d;
        }
        textViewScreen.setText(Double.toString(total));
    }

    public void buttonPercentajeAction(View view) {
        if (!isOperating) {
            setMode(AC_MODE, "");
            return;
        }
        if(!givenNumber.equals("")){
            total = Double.parseDouble(givenNumber) / 100d;
        }else{
            total /= 100d;
        }
        textViewScreen.setText(Double.toString(total));
    }

    public void buttonDivisionAction(View view) {
        operate();
        operation = "/";
    }

    public void buttonMultiplyAction(View view) {
        operate();
        operation = "*";
    }

    public void buttonMinusAction(View view) {
        operate();
        operation = "-";
    }
    public void buttonSumAction(View view) {
        operate();
        operation = "+";
    }

    public void buttonRootAction(View view) {
        if (!isOperating) {
            setMode(AC_MODE, "");
            return;
        }



        if(!givenNumber.equals("")){
            total = Math.sqrt(Double.parseDouble(givenNumber));
        }else {
            total = Math.sqrt(total);

        }
        givenNumber = "";

        textViewScreen.setText(Double.toString(total));
    }

    public void buttonEqualAction(View view) {
        if (!isOperating) {
            setMode(AC_MODE, "");
            return;
        }

        operate();
    }

    public void buttonPointAction(View view) {
        if (!isOperating) {
            setMode(C_MODE, "");
            textViewScreen.setText("0.");
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

       textViewScreen.setText(givenNumber);
    }

    public void button0Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "");
            textViewScreen.setText("0");
            return;
        }

        if (!givenNumber.equals("")) {
            givenNumber += "0";
        } else {
            textViewScreen.setText("0");
            return;
        }

        textViewScreen.setText(givenNumber);
    }



    public void button1Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "1");
        } else {
            givenNumber += "1";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button2Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "2");
        } else {
            givenNumber += "2";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button3Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "3");
        } else {
            givenNumber += "3";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button4Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "4");
        } else {
            givenNumber += "4";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button5Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "5");
        } else {
            givenNumber += "5";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button6Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "6");
        } else {
            givenNumber += "6";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button7Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "7");
        } else {
            givenNumber += "7";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button8Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "8");
        } else {
            givenNumber += "8";
        }

        textViewScreen.setText(givenNumber);
    }

    public void button9Action(View view) {
        if (!isOperating) {
            setMode(C_MODE, "9");
        } else {
            givenNumber += "9";
        }

        textViewScreen.setText(givenNumber);
    }
}