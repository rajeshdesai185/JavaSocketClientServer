
import java.io.*;
import java.net.*;

public class Client {
    public Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run(){
                   int port=5678;
                   try {
                       InetAddress address=InetAddress.getByName("localhost");
                       Socket socket=new Socket(address,port);
                       try {
                           PrintWriter toSocket=new PrintWriter(socket.getOutputStream(),true);
                           BufferedReader fromSocket=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                           toSocket.println("hello form client : "+socket.getLocalSocketAddress());
                            // Read server response line by line
                           String line;
                           while ((line = fromSocket.readLine()) != null) {
                           System.out.println("Response from server: " + line);
                    }
                           // Close resources
                           fromSocket.close();
                           toSocket.close();
                           socket.close();
                       }
                       catch (IOException e) {
                        e.printStackTrace();
                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }               
            }
        };

    }
    public static void main(String args[]){
        Client client=new Client();
        for(int i=0;i<100;i++){
            try {
                Thread thread=new Thread(client.getRunnable());
                thread.start();

            } catch (Exception e) {
                return;
            }
        }
    }
}
