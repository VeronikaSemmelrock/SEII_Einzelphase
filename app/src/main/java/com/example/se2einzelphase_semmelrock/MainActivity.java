package com.example.se2einzelphase_semmelrock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public Button btn;
    public EditText inputtxt;
    public TextView outputtext;
    public Button btncalc;
    private String output;
    private String input;
    private TextView outputtextCalc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        inputtxt = findViewById(R.id.inputField);
        outputtext = findViewById(R.id.outputField);
        btncalc = findViewById(R.id.btncalc);
        outputtextCalc = findViewById(R.id.txtoutputCalc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { taskButton(); }
        });

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { taskCalcButton();}
        });
    }

    private void taskCalcButton() {
        input = inputtxt.getText().toString();
        if (input.length() >0){
            output = "";
            ArrayList<Integer> even = new ArrayList<Integer>();
            ArrayList<Integer> uneven = new ArrayList<Integer>();

            String[] chars = inputtxt.getText().toString().split("");
            for(String c : chars){
                if(Integer.parseInt(c) % 2 == 0){
                    even.add(Integer.parseInt(c));
                }else{
                    uneven.add(Integer.parseInt(c));
                }
            }
            Collections.sort(even);
            Collections.sort(uneven);
            for(int i : even){
                output += i;
            }
            for(int i : uneven){
                output += i;
            }
            outputtextCalc.setText(output);
        }

    }

    private void taskButton() {
        new Thread() {
            public void run() {
                String sending = inputtxt.getText().toString();
                try {
                    Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //output attached to socket - so output to server
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input attached to socket - so input read from server
                    outToServer.writeBytes(sending+"\n"); //\n necessary for it working - probably server intern readLine();
                    String answer = inFromServer.readLine();

                    runOnUiThread(new Runnable() {//works without, but makes freezing of screen because of output less likely
                        @Override
                        public void run() {
                            outputtext.setText(answer);
                        }
                    });
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}