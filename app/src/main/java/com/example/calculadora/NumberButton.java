package com.example.calculadora;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class NumberButton extends androidx.appcompat.widget.AppCompatButton {
    public NumberButton(Context context, AttributeSet attrs) {
        super(context);

        this.setBackgroundColor(Color.parseColor("#112A4A"));
        this.setTextColor(Color.parseColor("#AA44D9E6"));
        this.setTextSize(30);
        this.setHeight(80);
        this.setText("T");

    }




}
