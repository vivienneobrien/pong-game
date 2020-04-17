import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionThread extends Thread {
	private Socket socket;
	public int player2height = 200; 
	
	public ConnectionThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			// Create input stream reader 
			DataInputStream  in = new DataInputStream(socket.getInputStream());
			
			// While connection is still active, listen 
			while(socket != null) {
				player2height = in.readInt();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Should reach here when connection was lost!
		// ... 
	}
	
	/* 
	 * Send height of player1 through socket to player2 
	 * */
	public void sendInt(int height) {
		DataOutputStream out;
		try {
			// Put height into output stream to other client 
			out = new DataOutputStream(socket.getOutputStream());
			// Send 
			out.writeInt(height);
			// Clears data stream (so its not sending garbage from before)
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
