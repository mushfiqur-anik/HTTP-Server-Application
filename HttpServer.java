package httpServer2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpServer {
	// HTTP request
	static String method;
	static String url;
	static String requestVersion;
	static String requestHeader;
	static String requestEntityBody;
	
	// HTTP response
	static String responseVersion = "HTTP/1.0";
	static String statusCode;
	static String phrase;
	static String responseHeader;
	static String responseEntityBody;
	
	
	/** Establish server connection
	 *  This starts the server and waits for clients to connect to this server
	 */
	public static void establishConnection(boolean verbose, int port, String directory) { 
		
		try { 
			// Resets everything to null..
			reset();
			
			System.out.println("Creating a new server socket... ");
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(true) { 
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client successfully connected to the server!!! ");
				
				startServer(clientSocket, verbose, port, directory);
			}
		}
		
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	
	// Start server
	public static void startServer(Socket clientSocket, boolean verbose, int port, String directory) { 
		
		try { 
			
			    reset();
			    
			    // Ask for user request
                String httpcRequest;
                String[] httpSplit;
                
            	Scanner keyboard = new Scanner(System.in); // Scanner
                
			    // Output stream
			    OutputStreamWriter outputStream = new OutputStreamWriter(clientSocket.getOutputStream());
	            BufferedWriter out = new BufferedWriter(outputStream);
	            
	            // Input stream
	            InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
	            BufferedReader in = new BufferedReader(inputStream);
	            
	            // Httpc Request
	            System.out.print("Please enter your httpc request: ");
	            httpcRequest = keyboard.nextLine();
	            System.out.println("");
	            
	            httpSplit = httpcRequest.split(" "); // Splitting the request 
	            url = httpSplit[httpSplit.length-1];
	    		method = httpSplit[1];
	    		
	    		for(int i = 0; i < httpSplit.length; i++) { 
	    			// header
	    			if(httpSplit[i].contains("-h")) { 
	    				requestHeader = httpSplit[i+1];
	    			}
	    			
	    			// entity
	    			if(httpSplit[i].contains("-d")) { 
	    				requestEntityBody = httpSplit[i+1];
	    			}
	    		}
	    		
	    		// If forbidden directory 
	    		if(directory.equals("\"/Users/mushfiquranik/Documents/Java Workspace/TestFolder\"")) { 
	    			statusCode = "404";
	    	        phrase = "Not found";
	    	        responseHeader = requestHeader;
	    		
	    			out.write(responseVersion + " " + statusCode + " " + phrase + "\r\n" + responseHeader + "\r\n" + "\r\n" + responseEntityBody);

	                out.flush();
	                clientSocket.shutdownOutput();
	                clientSocket.close();
			    }
	    		
	    		else { 
	    			
	    		// GET
	    		if(method.equals("get")) { 
	    			
	    			// Return files
	    			if(url.length() == 1) {
	    				System.out.println("Implementing get files");
	    				getFileResponse(directory);
	    			}
	    			
	    			else { 
	    				// Return file content
	    				System.out.println("Implementing get");
	    				getResponse(directory, url);
	    			}
	    			
	    			
	    		// Response from server
	    		out.write(responseVersion + " " + statusCode + " " + phrase + "\r\n" + responseHeader + "\r\n" + "\r\n" + responseEntityBody);

                out.flush();
                clientSocket.shutdownOutput();
                clientSocket.close();
	    			
	    		}
	    		
	    		// POST
	    		else { 
	    			System.out.println("Implementing post");
	    			postResponse(directory, url, requestEntityBody);
	    			
	    			// Response from server
		    		out.write(responseVersion + " " + statusCode + " " + phrase + "\r\n" + responseHeader + "\r\n" + "\r\n" + responseEntityBody);

	                out.flush();
	                clientSocket.shutdownOutput();
	                clientSocket.close();
	    		}
	    	}
		}
		
		catch(Exception e) { 
			e.getMessage();
		}
	}
	
	
	// Get File method 
	public static void getFileResponse(String directory) {
		
		File fileDirectory = new File(directory);
		File[] listFiles = fileDirectory.listFiles();
		
		for (File file : listFiles)
        {
            if (file.isFile())
            {
                responseEntityBody += "\n" + file.getName().toString() + "\n";
            }
        }
		
		statusCode = "200";
        phrase = "OK";
        responseHeader = requestHeader;
	}
	
	
	// Get method
	public static void getResponse(String directory, String url) {
		File file = new File(directory+url);
		
		// Check if file exists in the directory
		if(file.exists()) {
			try { 
				readFile(directory, url);
		
			}
			
			catch(Exception e) { 
				e.getMessage();
			}
		}
		
		else {
			statusCode = "404";
			phrase = "Not Found";
		}
	}
	
	
	// Post method
	public static void postResponse(String directory, String url, String requestEntityBody) { 
		File file = new File(directory+url);
		
		if(file.exists()) { 
			
			PrintWriter pw = null;
			
			try {
				pw = new PrintWriter(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			pw.print(requestEntityBody);
			pw.close();
			
			statusCode = "200";
            phrase = "OK";
            responseHeader = requestHeader + "\n" + "Content-length: " + requestEntityBody.length() + "\n" + "Content-Type: text/html";
            responseEntityBody = requestEntityBody;
		}
		
		else {
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			PrintWriter pw = null;
			
			try {
				pw = new PrintWriter(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			pw.print(requestEntityBody);
			pw.close();
			
			statusCode = "201";
            phrase = "Created";
            responseHeader = requestHeader + "\n" + "Content-length: " + requestEntityBody.length() + "\n" + "Content-Type: text/html";
            responseEntityBody = requestEntityBody;
		}
		
	}
	
	
	// Reads the selected file
	public static void readFile(String directory, String url) { 
		
		Scanner keyboard = null;
		String text = "";
		
		try {
			keyboard = new Scanner(new File("/Users/mushfiquranik/Documents/Java Workspace/TestFolder/" + url));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(keyboard.hasNext()) { 
			text += keyboard.nextLine() + "\n";
		}
		
		
		statusCode = "200";
        phrase = "OK";
        responseEntityBody = text;
        responseHeader = requestHeader + "\n" + "Content-Length:" + responseEntityBody.length();
	}
	
	// Reset all the values to null
	public static void reset() { 
		// HTTP request
		method = null;
		url = null;
	    requestVersion = null;
		requestHeader = null;
		requestEntityBody = null;
		
		// HTTP response
		responseVersion = "HTTP/1.0";
		statusCode = null;
		phrase = null;
		responseHeader = null;
		responseEntityBody = null;
	}
}