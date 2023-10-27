package com.example.calculatorbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val num1 = editText.text.toString().toInt();
            val num2 = editText2.text.toString().toInt();
            val suma = num1 + num2;
            editText3.setText(suma.toString()) ;
        }

    }
}