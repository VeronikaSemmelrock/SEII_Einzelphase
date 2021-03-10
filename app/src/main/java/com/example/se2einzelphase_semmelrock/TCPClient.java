package com.example.se2einzelphase_semmelrock;
import java.io.*;
import java.net.*;

class TCPClient {

    public static void main() throws Exception {
        String input;
        String output = "";

        Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //input from user

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //output attached to socket - so output to server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input attached to socket - so input read from server

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
    }

}
