package com.example.se2einzelphase_semmelrock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText inputtxt;
    TextView outputtext;
    Button btncalc;
    TCPClient c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        inputtxt = findViewById(R.id.inputField);
        outputtext = findViewById(R.id.outputField);
        btncalc = findViewById(R.id.btncalc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    c = new TCPClient();
                    outputtext.setText(c.getOutputForUser());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setInputFromUser(inputtxt.getText().toString());
                try {
                    c.join();   //waits for thread to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputtext.setText(c.getOutputForUser());
            }
        });
    }

}