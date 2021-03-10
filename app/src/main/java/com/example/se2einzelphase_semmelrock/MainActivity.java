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
    String mn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        inputtxt = findViewById(R.id.inputField);
        outputtext = findViewById(R.id.outputField);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mn = inputtxt.getText().toString();

            }
        });
    }

}