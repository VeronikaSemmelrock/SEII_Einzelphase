package com.example.se2einzelphase_semmelrock;

import java.io.*;
import java.net.*;

class TCPServer {
    public static void main() throws Exception{
        boolean running = true;
        BufferedReader in;
        DataOutputStream out;
        String input;

        ServerSocket welcomeSocket = new ServerSocket(53212);
        while(running){
            Socket connectionSocket = welcomeSocket.accept();
            in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); //in from client
            out = new DataOutputStream(connectionSocket.getOutputStream()); //out to client

            out.writeBytes("Connection established");
            input = in.readLine();
            switch (input){
                case "close":
                    running = false;
                    break;
                default:
                    //
                    break;
            }

        }



    }

}
