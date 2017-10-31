package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args){
        try {
            ServerSocket sock = new ServerSocket(6013);

            while(true){
                System.out.println("Ready for next request!");
                Socket client = sock.accept();

                // Start the new thread for this client
				// It's important that we accept and then pass the client socket before creating the thread so there
				// aren't infinite threads being created.
                EchoThread echoThread = new EchoThread(client);
                echoThread.start();
            }
        } catch (IOException ioe){
            System.err.println(ioe);
        }
    }

    public static class EchoThread extends Thread {
    	// Create the thread with a socket for the client connection
    	Socket client;
    	public EchoThread(Socket client){
    		this.client = client;
		}

		// Read and write from/to client within thread
		public void run(){
    		// Don't throw IOException because you don't want the entire server to crash if this thread crashes
    		try {
				OutputStream out = client.getOutputStream();
				InputStream reader = client.getInputStream();

				int nextByte;
				// read from client, write to client
				while((nextByte = reader.read()) != -1){
					out.write(nextByte);
				}

				// Close the socket gracefully
				out.flush();
				client.shutdownOutput();
				client.close();
    		} catch (IOException e){
    			System.err.println(e);
			}
		}
	}
}