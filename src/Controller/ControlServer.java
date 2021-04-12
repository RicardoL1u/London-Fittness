package Controller;

import java.io.IOException;
import java.net.InetSocketAddress;  
import com.sun.net.httpserver.HttpServer;  
import com.sun.net.httpserver.spi.HttpServerProvider;
import java.net.InetAddress;  
import java.net.Socket;  
import java.net.UnknownHostException;  
import java.net.BindException;

public class ControlServer{
	public static int portUsed;
	public static boolean isLocalPortUsing(int port){  
        boolean flag = true;  
        try {
            flag = isPortUsing("127.0.0.1", port);  
        } catch (Exception e) {  
        }  
        return flag;  
    } 
	
	public static boolean isPortUsing(String host,int port) throws UnknownHostException{  
        boolean flag = false;  
		Socket socket = null;
		
        InetAddress Address = InetAddress.getByName(host);  
        try {  
            socket = new Socket(Address,port);
            flag = true;  
        } catch (IOException e) {  
 
        }  
		finally{
			try{
				socket.close();
			}
			catch(Exception e){
				
			}
			
		}
        return flag;  
    }  

	public static void serverStart() throws IOException{
		int portInit = 8081;
		HttpServer httpserver = null;
		
		HttpServerProvider provider = HttpServerProvider.provider();
		
		while(isLocalPortUsing(portInit)){
			System.out.println("Port " + portInit + " has been used. Check next port.");
			portInit++;
		}
		try{
			httpserver = provider.createHttpServer(new InetSocketAddress(portInit), 100);
		}
		catch(BindException e){
			System.out.println("Port " + portInit + " has been used. Check next port.");
		}
		
		portUsed = portInit;
		
		try{
			httpserver.createContext("/User", new UserControllerDispatchHandler());
			//httpserver.createContext("/Admin", new AdminControllerDispatchHandler());
			httpserver.setExecutor(null);  
			httpserver.start();  
			System.out.println("Server Port: " + portUsed);
			System.out.println("Server started"); 
		}
		catch(Exception e){
			System.out.println("Server start failed.");
		}
        
	}
	
	public static void main(String[] args) throws IOException {
        serverStart();
    }
}