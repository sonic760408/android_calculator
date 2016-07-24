package com.example.hsiehkaiyang.calculator;

import android.util.Log;

/**
 * Created by hsiehkaiyang on 2016/7/24.
 */
public class Calculate
{

    // 3 + 6 = 9
    // 3 & 6 are called the operand.
    // The + is called the operator.
    // 9 is the result of the operation.
    private double mOperand;
    private String operand;
    private double mWaitingOperand;
    private String mWaitingOperator;

    private static final String DEBUG = "XXXX DEBUG";

    // operator types
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MULTI = "X";
    public static final String DIV = "÷";
    public static final String ALLCLEAR = "C" ;
    public static final String CLEAR = "CE" ;
    public static final String TOGGLESIGN = "+/-";

    public Calculate()
    {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
    }

    public double performOperation(String operator)
    {
        /*
        * If you are using Java 7, then you can use switch in place of if statements
        *
        *     switch (operator) {
        *     case CLEARMEMORY:
        *         calculatorMemory = 0;
        *         break;
        *     case ADDTOMEMORY:
        *         calculatorMemory = calculatorMemory + operand;
        *         break;
        *     etc...
        *     }
        */

        switch(operator)
        {
            case ALLCLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                break;

            case CLEAR:
                mWaitingOperator = "";
                mOperand = 0;
                break;

            case TOGGLESIGN:
                mOperand = -mOperand;
                break;

            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
                break;
        }

        Log.d(DEBUG, Double.toString(mOperand));
        return mOperand;

    }

    protected void performWaitingOperation() {

        switch(mWaitingOperator)
        {
            case ADD:
                mOperand = mWaitingOperand + mOperand;
                break;

            case SUB:
                mOperand = mWaitingOperand - mOperand;
                break;

            case MULTI:
                mOperand = mWaitingOperand * mOperand;
                break;

            case DIV:
                try{
                    mOperand = mWaitingOperand / mOperand;
                }
                catch(ArithmeticException e)
                {
                    e.printStackTrace();
                    mOperand = 0;
                    mWaitingOperator = "";
                    mWaitingOperand = 0;
                }
                break;
        }
    }

    public void setOperand(double operand)
    {
        mOperand = operand;
    }

    public double getResult()
    {
        return mOperand;
    }

    public String toString()
    {
        return Double.toString(mOperand);
    }
}
