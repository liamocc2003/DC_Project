import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

public class EchoClientHelper2 {
   static final String endMessage = ".";
   private MyStreamSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;

   EchoClientHelper2(String hostName, String portNum)
           throws SocketException, UnknownHostException, IOException {
      this.serverHost = InetAddress.getByName(hostName);
      this.serverPort = Integer.parseInt(portNum);
      this.mySocket = new MyStreamSocket(this.serverHost, this.serverPort);

      System.out.println("\nConnection request completed." +
              "\nCurrently logged on to server.");
   }
	
   public String getEcho(String message)
           throws SocketException, IOException{
      mySocket.sendMessage(message);
      return message;
   }

   public void done()
           throws SocketException, IOException{
      System.out.println("Connection closed.");
      mySocket.sendMessage(endMessage);
      mySocket.close();
   }
}
