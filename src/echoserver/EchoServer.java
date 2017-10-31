package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args){
        try {
            ServerSocket sock = new ServerSocket(6013);

            while(true){
                System.out.println("Got a request!");
                Socket client = sock.accept();

                OutputStream out = client.getOutputStream();
                InputStream reader = client.getInputStream();

                int nextByte;
                // read from client, write to client
                while((nextByte = reader.read()) != -1){
                    out.write(nextByte);
                }

                out.flush();
                client.shutdownOutput();
                client.close();
            }
        } catch (IOException ioe){
            System.err.println(ioe);
        }
    }
}