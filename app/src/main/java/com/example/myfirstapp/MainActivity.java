package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//    }
//}


public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    private final String Tag = "MyTag";

    private long f = 0;
    private long s = 0;
    private boolean isRestart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_main);

        tv = findViewById(R.id.textView2);
        et = findViewById(R.id.editTextText);
        isRestart = false;

        Log.d(Tag, "onCreate working..");

        f = System.currentTimeMillis();
    }

    @Override
    protected void onStart() {
        super.onStart();

        s = System.currentTimeMillis();
        if(isRestart) {
            Log.d(Tag, "Time between onRestart and onStart: " + (s - f) + " ms");
        }
        else {
            Log.d(Tag, "Time between onCreate and onStart: " + (s - f) + " ms");
        }
        Log.d(Tag, "onStart working..");

        f = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();

        s = System.currentTimeMillis();

        Log.d(Tag, "Time between onStart and onResume: " + (s - f) + " ms");
        Log.d(Tag, "onResume working..");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(Tag, "onPause working..");

        f = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();

        s = System.currentTimeMillis();

        Log.d(Tag, "Time between onPause and onStop: " + (s - f) + " ms");
        Log.d(Tag, "onStop working..");

        f = System.currentTimeMillis();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        isRestart = true;
        Log.d(Tag, "onRestart working..");

        f = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        s = System.currentTimeMillis();

        Log.d(Tag, "Time between onStop and onDestroy: " + (s - f) + " ms");
        Log.d(Tag, "onDestroy working..");
    }

    @SuppressLint({"ResourceAsColor"})
    public void clickButton(View view) {
        tv.setBackgroundColor(R.color.green);
        tv.setText(et.getText());

        Log.d(Tag, "Button has been clicked!");
    }
}