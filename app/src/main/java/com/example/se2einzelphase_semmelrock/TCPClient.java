package com.example.se2einzelphase_semmelrock;
import java.io.*;
import java.net.*;

class TCPClient extends Thread {
    private static String input;
    private static String output = "";
    Socket clientSocket;
    BufferedReader inFromUser;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    String inputFromUser;


    public TCPClient() throws Exception{
        clientSocket = new Socket("se2-isys.aau.at", 53212);
        inFromUser = new BufferedReader(new InputStreamReader(System.in)); //input from user
        outToServer = new DataOutputStream(clientSocket.getOutputStream()); //output attached to socket - so output to server
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input attached to socket - so input read from server
    }

    public void run() {
        try {
            input = inFromUser.readLine();
            switch (input){
                case "close":
                    outToServer.writeBytes("close");
                    clientSocket.close();
                    break;
                default:
                    //
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    }

