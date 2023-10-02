package com.chouhan.mycalculator;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTV;
    TextView solutionTV;
    MaterialButton button0, button1, button2, button3, button4, button5;
    MaterialButton button6, button7, button8, button9;
    MaterialButton buttonC, buttonAC, buttonDot, buttonOpenB, buttonCloseB;
    MaterialButton buttonMultiply, buttonAdd, buttonDivide, buttonSubtract, buttonEqual;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final double[] firstNumber = new double[1];
        final String[] operation = new String[1];

        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonDivide = findViewById(R.id.button_divide);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubtract = findViewById(R.id.button_subtract);
        buttonDot = findViewById(R.id.button_dot);
        buttonC = findViewById(R.id.button_c);
        buttonAC = findViewById(R.id.button_allClear);
        buttonEqual = findViewById(R.id.button_equal);
        buttonOpenB = findViewById(R.id.button_openB);
        buttonCloseB = findViewById(R.id.button_closeB);

        buttonAC.setOnClickListener(view ->{
            solutionTV.setText("0");
            resultTV.setText("");
        });
        ArrayList<MaterialButton> buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);

        for (MaterialButton b : buttons){
            b.setOnClickListener(view ->{
                if (!solutionTV.getText().equals("0")){
                    solutionTV.setText(solutionTV.getText().toString()+b.getText().toString());
                }else {
                    solutionTV.setText(b.getText().toString());
                }
            });
        }
        ArrayList<MaterialButton> operate = new ArrayList<>();
        operate.add(buttonDivide);
        operate.add(buttonMultiply);
        operate.add(buttonAdd);
        operate.add(buttonSubtract);

        for (MaterialButton b:operate){
            b.setOnClickListener(view ->{
                resultTV.setText(solutionTV.getText().toString() + b.getText().toString());
                firstNumber[0] = Double.parseDouble((String) solutionTV.getText().toString());
                operation[0] = b.getText().toString();
                solutionTV.setText("0");

            });
        }
        buttonC.setOnClickListener(view ->{
            String num = solutionTV.getText().toString();

            if (num.length()>1){
                solutionTV.setText(num.substring(0,num.length()-1));
            } else if (num.length()==1 && !num.equals("0")) {
                solutionTV.setText("0");
            }
        });

        buttonDot.setOnClickListener(view ->{
            if (!solutionTV.getText().toString().contains(".")){
                solutionTV.setText(solutionTV.getText().toString() + buttonDot.getText().toString());
            }
        });

        buttonEqual.setOnClickListener(view ->{
            double secondNumber = Double.parseDouble(solutionTV.getText().toString());
            double result;
            switch (operation[0]){
                case "/":
                    result = firstNumber[0]/secondNumber;
                    break;
                case "*":
                    result = firstNumber[0]*secondNumber;
                    break;
                case "+":
                    result = firstNumber[0]+secondNumber;
                    break;
                case "-":
                    result = firstNumber[0]-secondNumber;
                default:
                    result = firstNumber[0]+secondNumber;
            }
            DecimalFormat df = new DecimalFormat("#.##");
            String format = df.format(result);
            solutionTV.setText("");
            resultTV.setText(format);
            firstNumber[0]=result;
        });

    }


    @Override
    public void onClick(View view) {

    }


}
