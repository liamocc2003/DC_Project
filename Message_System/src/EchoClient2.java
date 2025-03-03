import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 {
   static final String endMessage = ".";

   public static void main(String[] args) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);

      try {
         System.out.println("Welcome to the Echo client.\n");

         System.out.println("- Log-On -");
         System.out.print("Enter the server host: ");
         String hostName = br.readLine();
         if (hostName.isEmpty())
            hostName = "localhost";

         System.out.print("Enter the port number of the server host: ");
         String portNum = br.readLine();
         if (portNum.isEmpty())
            portNum = "1";

         EchoClientHelper2 helper = new EchoClientHelper2(hostName, portNum);

         boolean done = false;
         String message;

         while (!done) {
            String choiceAsString = null;
            int choiceAsInt = 0;

            while (choiceAsString == null) {
               System.out.println("\nChoose one of the following options by inputting the corresponding number: \n" +
                       "[1] Upload a Message \n" +
                       "[2] Retrieve all Messages \n" +
                       "[3] Download a Message \n" +
                       "[4] Download all Messages \n" +
                       "[5] Exit");
               choiceAsString = br.readLine();

               try {
                  choiceAsInt = Integer.parseInt(choiceAsString);
               }
               catch (NumberFormatException e) {
                  System.out.println("Choice Invalid! Please enter a valid number next time.");
                  choiceAsString = null;
               }
            }

            if (choiceAsInt == 1) {
               System.out.println("Enter a line to add to the server.");
               message = br.readLine();

               message = helper.getEcho(message);
               new AddMessage(message);
            }
            else if (choiceAsInt == 2) {
               String typeOfRead = "retrieve";
               new ReadFile(typeOfRead);
            }
            else if (choiceAsInt == 3) {
               String typeOfRead = "downloadOne";
               new ReadFile(typeOfRead);
            }
            else if (choiceAsInt == 4) {
               String typeOfRead = "downloadAll";
               new ReadFile(typeOfRead);
            }
            else if (choiceAsInt == 5) {
               done = true;
               helper.done();
            }

            else{
               System.out.println("Invalid Choice! Please enter a valid number next time.");
            }
         }
      }
      catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
}
