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

			/*
			* Start one thread to send information to the EchoServer
			* and another to receive information from the EchoServer
			* This reduces the time the streams spend waiting for each other to finish reading and writing
			*/
			StreamThread send = new StreamThread(System.in, toServer, socket);
			StreamThread receive = new StreamThread(fromServer, System.out, socket);
			send.start();
			receive.start();

        } catch (IOException ioe){
            System.err.println(ioe);
        }
	}

    private static class StreamThread extends Thread{
    	InputStream in;
    	OutputStream out;
    	Socket socket;

    	// constructor for the thread
    	public StreamThread(InputStream in, OutputStream out, Socket socket){
    		this.in = in;
    		this.out = out;
    		this.socket = socket;
		}

		public void run(){
    		try {
    			int nextByte;
    			// will read from given input and write to given output
    			while ((nextByte = in.read()) != -1){
    				out.write(nextByte);
				}
				out.flush();

    			// if this writes to System.out, shuts down Input when it is done and closes socket
				// otherwise this shuts down Output
				// This has to be done from these threads to ensure things happen in the proper order
    			if (out.equals(System.out)){
    				socket.shutdownInput();
    				socket.close();
				} else {
    				socket.shutdownOutput();
				}
			} catch (IOException e){
    			System.err.println(e);
			}
		}
	}
}