package echoserver;

import java.net.*;
import java.io.*;

public class EchoClient {

    public static void main(String[] args){
        try {
            Socket socket = new Socket("127.0.0.1", 6013);
            //change ip address to any ip or url

            InputStream fromServer = socket.getInputStream();
            OutputStream toServer = socket.getOutputStream();

            int nextByte;
            int incomingByte;

            //read from system.in, write out to server, read from server and write that
            while ((nextByte = System.in.read()) != -1){
                toServer.write(nextByte);
                incomingByte = fromServer.read();

                System.out.write(incomingByte);
            }

            toServer.flush();
            System.out.flush();
            socket.close();
        } catch (IOException ioe){
            System.out.println("Shit got real");
            System.err.println(ioe);
        }
    }
}