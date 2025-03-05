import java.net.*;

/**
 * This module contains the application logic of an echo server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer3 {
   public static void main(String[] args) {
      int serverPort = 1;

      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);

      try {
         ServerSocket myConnectionSocket = new ServerSocket(serverPort);
         System.out.println("Echo server ready.");

         while (true) {
            System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());

            System.out.println("Connection accepted");

            Thread theThread = new Thread(new EchoServerThread(myDataSocket));
            theThread.start();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
