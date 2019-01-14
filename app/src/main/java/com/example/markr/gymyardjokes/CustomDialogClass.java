package com.example.markr.gymyardjokes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */



public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    String m_string;

    public CustomDialogClass(Activity a, String in_string) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        m_string = in_string;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        TextView textView = (TextView) findViewById(R.id.txt_dia);
        textView.setText(m_string);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                m_myDialogListener.userAccepted();
                dismiss();
                break;
            case R.id.btn_no:
                m_myDialogListener.userDeclined();
                dismiss();
                break;
            default:
                break;
        }
    }

    public static interface MyDialogListener
    {
        public void userAccepted();
        public void userDeclined();
    }

    MyDialogListener m_myDialogListener;

    public void setMyDialogListener( MyDialogListener myDialogListener)
    {
        m_myDialogListener = myDialogListener;
    }

    public MyDialogListener getMyDialogListener(){
        return m_myDialogListener;
    }


}

