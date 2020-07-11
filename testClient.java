package httpServer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testClient {

	public static void main(String[] args) throws IOException {
		Socket s = null;
		String str = null; 
		
		try {
			s = new Socket("localhost", 8101);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(s.getInputStream());
	
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine() + " ");
		}
		
		System.out.println();
		
		scan.close();
		s.close();
		
	}

}