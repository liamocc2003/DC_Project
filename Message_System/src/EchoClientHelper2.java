import java.net.*;
import java.io.*;


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
   }
	
   public String getEcho(String message, String username)
           throws SocketException, IOException{
      mySocket.sendMessage(message, username);
      return message;
   }

   public void done(String username)
           throws SocketException, IOException{
      mySocket.sendMessage(endMessage, username);
      mySocket.close();
   }
}
