package com.example.se2einzelphase_semmelrock;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class TCPClient extends Thread {
    private static String input;
    private static String output = "";
    Socket clientSocket;
    BufferedReader inFromUser;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    String inputFromUser;
    String outputForUser;
    ArrayList<Integer> even = new ArrayList<Integer>();
    ArrayList<Integer> uneven = new ArrayList<Integer>();


    public TCPClient() throws Exception{
        clientSocket = new Socket("se2-isys.aau.at", 53212);
        outputForUser = "Connection established";
        //inFromUser = new BufferedReader(new InputStreamReader(System.in)); //input from user
        outToServer = new DataOutputStream(clientSocket.getOutputStream()); //output attached to socket - so output to server
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input attached to socket - so input read from server
    }

    public void run() {
        String[] chars = inputFromUser.split("");
        for(String c : chars){
            if(Integer.parseInt(c) % 2 == 0){
                even.add(Integer.parseInt(c));
            }else{
                uneven.add(Integer.parseInt(c));
            }
        }
        Collections.sort(even);
        Collections.sort(uneven);
        outputForUser = "";
        for(int i : even){
            outputForUser += i;
        }
        for(int i : uneven){
            outputForUser += i;
        }

    }


    public void setInputFromUser(String inputFromUser) {
        this.inputFromUser = inputFromUser;
    }

    public String getOutputForUser() {
        return outputForUser;
    }

}

