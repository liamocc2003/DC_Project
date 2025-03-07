import java.net.*;
import java.io.*;


public class MyStreamSocket extends Socket {
   private Socket socket;
   private BufferedReader input;
   private PrintWriter output;
   static final String endMessage = ".";

   MyStreamSocket(InetAddress acceptorHost, int acceptorPort)
           throws SocketException, IOException {
      socket = new Socket(acceptorHost, acceptorPort);
      setStreams();
   }

   MyStreamSocket(Socket socket)
           throws IOException {
      this.socket = socket;
      setStreams();
   }

   private void setStreams()
           throws IOException{
      InputStream inStream = socket.getInputStream();
      input = new BufferedReader(new InputStreamReader(inStream));

      OutputStream outStream = socket.getOutputStream();
      output = new PrintWriter(new OutputStreamWriter(outStream));
   }

   public void sendMessage(String message, String username)
           throws IOException {
      if (!message.equals(endMessage)) {
         output.print(username + ": " + message + "\n");
      }
      else{
         output.print(username + " logged out. Session over.\n");
      }
      output.flush();
   }

   public String receiveMessage()
		throws IOException {
      String message = input.readLine();

      return message;
   }

   public void close()
		throws IOException {	
      socket.close();
   }
}
