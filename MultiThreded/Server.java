import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer() {
        return (clienSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clienSocket.getOutputStream());
                toClient.println("hello from the server  ");
                
                toClient.close();
                clienSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String args[]) {
        int port = 5678;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port : " + port);
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch (SocketTimeoutException e) {
            System.out.println("No client connected in the last 10 seconds.");
            // if request is not recived from client then catch block execute 
    }
        catch (IOException ex) {

            ex.printStackTrace( );
        }

    }
}
