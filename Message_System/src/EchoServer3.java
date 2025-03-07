import java.net.*;


public class EchoServer3 {
   public static void main(String[] args) {
      int serverPort = 1;

      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);

      try {
         ServerSocket myConnectionSocket = new ServerSocket(serverPort);
         System.out.println("-- Echo Server --\n");

         while (true) {
            System.out.println("Waiting for a connection.");
            MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());

            System.out.println("Connection accepted\n");

            Thread theThread = new Thread(new EchoServerThread(myDataSocket));
            theThread.start();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
