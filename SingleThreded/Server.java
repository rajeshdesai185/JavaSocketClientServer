import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException{
        int port = 5678;
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) { 
            try {
                System.out.println("Sever is listening on port : "+port);
                Socket acceptedConnection=socket.accept();
                System.out.println("connection accepted from client"+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("hello from the server");
                toClient.flush(); 
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            
        }

    }
    public static void main(String args[]){
        Server server= new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
    }
}