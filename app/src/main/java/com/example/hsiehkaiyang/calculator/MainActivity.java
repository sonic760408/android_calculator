package com.example.hsiehkaiyang.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Calculate cal;
    private static final String DEBUG_TAG = "DEBUG_TAG";

    private TextView mCalculatorDisplay;
    private static final String DIGITS = "0123456789.";
    private Boolean userIsInTheMiddleOfTypingANumber = false;

    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn0 = (Button)findViewById(R.id.btn0);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        Button btn4 = (Button)findViewById(R.id.btn4);
        Button btn5 = (Button)findViewById(R.id.btn5);
        Button btn6 = (Button)findViewById(R.id.btn6);
        Button btn7 = (Button)findViewById(R.id.btn7);
        Button btn8 = (Button)findViewById(R.id.btn8);
        Button btn9 = (Button)findViewById(R.id.btn9);
        Button btn_add = (Button)findViewById(R.id.btn_add);
        Button btn_sub = (Button)findViewById(R.id.btn_sub);
        Button btn_multi = (Button)findViewById(R.id.btn_multi);
        Button btn_div = (Button)findViewById(R.id.btn_div);
        Button btn_equal = (Button)findViewById(R.id.btn_equal);
        Button btn_allclear = (Button)findViewById(R.id.btn_clear);
        Button btn_clear = (Button)findViewById(R.id.btn_ce);
        Button btn_togglesign = (Button)findViewById(R.id.btn_togglesign);
        Button btn_point = (Button)findViewById(R.id.btn_point);

        mCalculatorDisplay = (TextView) findViewById(R.id.output_text);
        cal = new Calculate();

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        //register buttonlisteners
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_allclear.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_togglesign.setOnClickListener(this);
        btn_point.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            //pressed digit
            if (userIsInTheMiddleOfTypingANumber) {
                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            }
            else
            {
                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        }
        else
        {
            // operation was pressed
            if (userIsInTheMiddleOfTypingANumber) {
                cal.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            cal.performOperation(buttonPressed);
            mCalculatorDisplay.setText(df.format(cal.getResult()));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save variables on screen orientation change
        outState.putDouble("OPERAND", cal.getResult());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore variables on screen orientation change
        cal.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorDisplay.setText(df.format(cal.getResult()));
    }
}
