import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class EchoClient2 {
   static final String endMessage = ".";

   private static class ClientHelper{
      private EchoClientHelper2 helper;

      public void setHelper(String hostname, String portNum)
         throws IOException{
         helper = new EchoClientHelper2(hostname, portNum);
      }

      public void done(String username)
              throws IOException{
         helper.done(username);
      }

      public String getEcho(String message, String username)
              throws IOException {
         message = helper.getEcho(message, username);
         return message;
      }
   }

   public static void main(String[] args) {
      String currentDirectory = System.getProperty("user.dir") + "\\";
      File MessageStorage = new File(currentDirectory + "Message_system\\src\\" + "messageStorage.txt");

      final ClientHelper helper = new ClientHelper();
      JFrame frame = new JFrame();

      // Create Starting Page to connect to server
      JLabel title1 = new JLabel("Welcome to the Echo Client!");
      title1.setFont(new Font("Helvetica", Font.BOLD, 30));
      title1.setBounds(40, 20, 500, 50);

      JLabel hostNameLabel = new JLabel("Host Name:");
      hostNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      hostNameLabel.setBounds(70, 100, 500, 50);
      JTextPane hostNameText = new JTextPane();
      hostNameText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      hostNameText.setBounds(200, 110, 200, 30);
      hostNameText.setBorder(BorderFactory.createLineBorder(Color.black));

      JLabel portNumLabel = new JLabel("Port Number:");
      portNumLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      portNumLabel.setBounds(70, 150, 500, 50);
      JTextPane portNumText = new JTextPane();
      portNumText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      portNumText.setBounds(200, 160, 200, 30);
      portNumText.setBorder(BorderFactory.createLineBorder(Color.black));

      JButton connectToServerButton = new JButton("Connect to Server");
      connectToServerButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      connectToServerButton.setBounds(150, 250, 200, 40);


      // Login page
      JLabel title2 = new JLabel("Enter Login Information");
      title2.setFont(new Font("Helvetica", Font.BOLD, 30));
      title2.setBounds(80, 20, 500, 50);

      JLabel usernameLabel = new JLabel("Username:");
      usernameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      usernameLabel.setBounds(90, 100, 500, 50);
      JTextPane usernameText = new JTextPane();
      usernameText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      usernameText.setBounds(190, 110, 200, 30);
      usernameText.setBorder(BorderFactory.createLineBorder(Color.black));

      JLabel passwordLabel = new JLabel("Password:");
      passwordLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      passwordLabel.setBounds(90, 150, 500, 50);
      JTextPane passwordText = new JTextPane();
      passwordText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      passwordText.setBounds(190, 160, 200, 30);
      passwordText.setBorder(BorderFactory.createLineBorder(Color.black));

      JButton loginButton = new JButton("Login");
      loginButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      loginButton.setBounds(200, 250, 100, 40);


      // Choice page
      JLabel title3 = new JLabel("Echo Client Functionality");
      title3.setFont(new Font("Helvetica", Font.BOLD, 30));
      title3.setBounds(65, 20, 500, 50);

      JLabel choiceLabel = new JLabel("Choose one of the following options:");
      choiceLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      choiceLabel.setBounds(40, 60, 500, 50);

      ButtonGroup buttonGroup = new ButtonGroup();
      JRadioButton uploadMessage = new JRadioButton("Upload a message");
      uploadMessage.setFont(new Font("Helvetica", Font.PLAIN, 15));
      uploadMessage.setBounds(50, 100, 250, 30);
      JRadioButton retrieveMessages = new JRadioButton("Retrieve all messages");
      retrieveMessages.setFont(new Font("Helvetica", Font.PLAIN, 15));
      retrieveMessages.setBounds(50, 125, 250, 30);
      JRadioButton downloadOne = new JRadioButton("Download a specific message");
      downloadOne.setFont(new Font("Helvetica", Font.PLAIN, 15));
      downloadOne.setBounds(50, 150, 250, 30);
      JRadioButton downloadAll = new JRadioButton("Download all messages");
      downloadAll.setFont(new Font("Helvetica", Font.PLAIN, 15));
      downloadAll.setBounds(50, 175, 250, 30);
      JRadioButton logout = new JRadioButton("Logout");
      logout.setFont(new Font("Helvetica", Font.PLAIN, 15));
      logout.setBounds(50, 200, 250, 30);

      buttonGroup.add(uploadMessage);
      buttonGroup.add(retrieveMessages);
      buttonGroup.add(downloadOne);
      buttonGroup.add(downloadAll);
      buttonGroup.add(logout);

      JButton confirmChoice = new JButton("Confirm Choice");
      confirmChoice.setFont(new Font("Helvetica", Font.PLAIN, 15));
      confirmChoice.setBounds(175, 250, 150, 40);


      // Upload Message
      JLabel title4 = new JLabel("Upload a Message to the Server");
      title4.setFont(new Font("Helvetica", Font.BOLD, 25));
      title4.setBounds(50, 20, 500, 50);

      JLabel uploadMessageLabel = new JLabel("Enter a line to add to the server: ");
      uploadMessageLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      uploadMessageLabel.setBounds(100, 100, 500, 30);
      JTextPane uploadMessageText = new JTextPane();
      uploadMessageText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      uploadMessageText.setBounds(110, 130, 265, 30);
      uploadMessageText.setBorder(BorderFactory.createLineBorder(Color.black));

      JButton uploadMessageButton = new JButton("Upload Message");
      uploadMessageButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      uploadMessageButton.setBounds(175, 250, 150, 40);


      //Download a Message
      JLabel title5 = new JLabel("Download a message from the Server");
      title5.setFont(new Font("Helvetica", Font.BOLD, 25));
      title5.setBounds(20, 20, 500, 50);

      JLabel chosenIndexLabel = new JLabel("Enter index of message: ");
      chosenIndexLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      chosenIndexLabel.setBounds(30, 80, 500, 30);
      JTextPane chosenIndexText = new JTextPane();
      chosenIndexText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      chosenIndexText.setBounds(250, 80, 100, 30);
      chosenIndexText.setBorder(BorderFactory.createLineBorder(Color.black));

      JLabel messageChosenLabel = new JLabel("Message chosen: ");
      messageChosenLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
      messageChosenLabel.setBounds(30, 105, 500, 30);
      JLabel messageReceivedLabel = new JLabel();
      messageReceivedLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
      messageReceivedLabel.setBounds(150, 105, 500, 30);

      JLabel downloadOneFileNameLabel = new JLabel("Enter the name of the download file: ");
      downloadOneFileNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      downloadOneFileNameLabel.setBounds(30, 150, 500, 30);
      JTextPane downloadOneFileNameText = new JTextPane();
      downloadOneFileNameText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      downloadOneFileNameText.setBounds(50, 180, 300, 30);
      downloadOneFileNameText.setBorder(BorderFactory.createLineBorder(Color.black));

      JButton chosenIndexButton = new JButton("Check Index");
      chosenIndexButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      chosenIndexButton.setBounds(50, 250, 175, 40);

      JButton downloadOneButton = new JButton("Download Message");
      downloadOneButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      downloadOneButton.setBounds(270, 250, 175, 40);
      
      
      // Download all messages
      JLabel title6 = new JLabel("Download all Messages from the Server");
      title6.setFont(new Font("Helvetica", Font.PLAIN, 25));
      title6.setBounds(20, 20, 500, 50);
      
      JLabel downloadAllFileNameLabel = new JLabel("Enter the name of the download file: ");
      downloadAllFileNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
      downloadAllFileNameLabel.setBounds(30, 80, 500, 30);
      JTextPane downloadAllFileNameText = new JTextPane();
      downloadAllFileNameText.setFont(new Font("Helvetica", Font.PLAIN, 20));
      downloadAllFileNameText.setBounds(50, 110, 250, 30);
      downloadAllFileNameText.setBorder(BorderFactory.createLineBorder(Color.black));

      JButton downloadAllButton = new JButton("Download All Messages");
      downloadAllButton.setFont(new Font("Helvetica", Font.PLAIN, 15));
      downloadAllButton.setBounds(150, 250, 200, 40);


      // Add initial components to frame
      frame.add(title1);
      frame.add(hostNameLabel);
      frame.add(hostNameText);
      frame.add(portNumLabel);
      frame.add(portNumText);
      frame.add(connectToServerButton);

      // Add Download a Message components to frame
      // If not added here, will not allow the components in this section to be added to frame
      // Very strange, Very confused
      frame.add(messageChosenLabel);
      messageChosenLabel.setVisible(false);

      // Set frame details
      frame.setTitle("Echo Client");
      frame.setSize(500, 350);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(null);
      frame.setVisible(true);


      // Connect to server button
      connectToServerButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            title1.setVisible(false);
            hostNameLabel.setVisible(false);
            hostNameText.setVisible(false);
            portNumLabel.setVisible(false);
            portNumText.setVisible(false);
            connectToServerButton.setVisible(false);

            frame.add(title2);
            frame.add(usernameLabel);
            frame.add(usernameText);
            frame.add(passwordLabel);
            frame.add(passwordText);
            frame.add(loginButton);
            frame.setVisible(true);
         }
      });

      // Login to server
      loginButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (!usernameText.getText().isEmpty()){
               String hostName = hostNameText.getText();
               String portNum = portNumText.getText();
               if (hostName.isEmpty()) {
                  hostName = "localhost";
               }
               if (portNum.isEmpty()) {
                  portNum = "1";
               }

               try {
                  helper.setHelper(hostName, portNum);

                  frame.setVisible(false);
                  title2.setVisible(false);
                  usernameLabel.setVisible(false);
                  usernameText.setVisible(false);
                  passwordLabel.setVisible(false);
                  passwordText.setVisible(false);
                  loginButton.setVisible(false);

                  frame.add(title3);
                  frame.add(choiceLabel);
                  frame.add(uploadMessage);
                  frame.add(retrieveMessages);
                  frame.add(downloadOne);
                  frame.add(downloadAll);
                  frame.add(logout);
                  frame.add(confirmChoice);

                  title3.setVisible(true);
                  choiceLabel.setVisible(true);
                  uploadMessage.setVisible(true);
                  retrieveMessages.setVisible(true);
                  downloadOne.setVisible(true);
                  downloadAll.setVisible(true);
                  logout.setVisible(true);
                  confirmChoice.setVisible(true);
                  frame.setVisible(true);
               }
               catch (Exception ex) {
                  ex.printStackTrace();
               }
            }
            else {
               JOptionPane.showMessageDialog(null, "Username is required to use chat system in server.", "Username Required", JOptionPane.ERROR_MESSAGE);
            }
         }
      });

      // Choice is selected
      confirmChoice.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               if (uploadMessage.isSelected()) {
                  frame.setVisible(false);
                  title3.setVisible(false);
                  choiceLabel.setVisible(false);
                  uploadMessage.setVisible(false);
                  retrieveMessages.setVisible(false);
                  downloadOne.setVisible(false);
                  downloadAll.setVisible(false);
                  logout.setVisible(false);
                  confirmChoice.setVisible(false);

                  frame.add(title4);
                  frame.add(uploadMessageLabel);
                  frame.add(uploadMessageText);
                  frame.add(uploadMessageButton);

                  title4.setVisible(true);
                  uploadMessageLabel.setVisible(true);
                  uploadMessageText.setVisible(true);
                  uploadMessageButton.setVisible(true);
                  frame.setVisible(true);
               }
               else if (retrieveMessages.isSelected()) {
                  new ReadFile();
               }
               else if (downloadOne.isSelected()) {
                  frame.setVisible(false);
                  title3.setVisible(false);
                  choiceLabel.setVisible(false);
                  uploadMessage.setVisible(false);
                  retrieveMessages.setVisible(false);
                  downloadOne.setVisible(false);
                  downloadAll.setVisible(false);
                  logout.setVisible(false);
                  confirmChoice.setVisible(false);

                  frame.add(title5);
                  frame.add(chosenIndexLabel);
                  frame.add(chosenIndexText);
                  frame.add(chosenIndexButton);

                  title5.setVisible(true);
                  chosenIndexLabel.setVisible(true);
                  chosenIndexText.setVisible(true);
                  chosenIndexButton.setVisible(true);
                  frame.setVisible(true);
               }
               else if (downloadAll.isSelected()) {
                  frame.setVisible(false);
                  title3.setVisible(false);
                  choiceLabel.setVisible(false);
                  uploadMessage.setVisible(false);
                  retrieveMessages.setVisible(false);
                  downloadOne.setVisible(false);
                  downloadAll.setVisible(false);
                  logout.setVisible(false);
                  confirmChoice.setVisible(false);
                  
                  frame.add(title6);
                  frame.add(downloadAllFileNameLabel);
                  frame.add(downloadAllFileNameText);
                  frame.add(downloadAllButton);

                  title6.setVisible(true);
                  downloadAllFileNameLabel.setVisible(true);
                  downloadAllFileNameText.setVisible(true);
                  downloadAllButton.setVisible(true);
                  frame.setVisible(true);
               }
               else if (logout.isSelected()) {
                  try {
                     String username = usernameText.getText();
                     helper.done(username);

                     frame.setVisible(false);
                     title3.setVisible(false);
                     choiceLabel.setVisible(false);
                     uploadMessage.setVisible(false);
                     retrieveMessages.setVisible(false);
                     downloadOne.setVisible(false);
                     downloadAll.setVisible(false);
                     logout.setVisible(false);
                     confirmChoice.setVisible(false);

                     title2.setVisible(true);
                     usernameLabel.setVisible(true);
                     usernameText.setVisible(true);
                     passwordLabel.setVisible(true);
                     passwordText.setVisible(true);
                     loginButton.setVisible(true);
                     frame.setVisible(true);

                     usernameText.setText("");
                     passwordText.setText("");
                  }
                  catch (Exception e1) {
                     e1.printStackTrace();
                  }
               }
               else{
                  JOptionPane.showMessageDialog(null,
                          "Invalid Choice!\nPlease select an available option.",
                          "Invalid Choice",
                          JOptionPane.ERROR_MESSAGE
                  );
               }
            }
            catch (Exception e1){
               e1.printStackTrace();
            }
         }
      });

      // Upload message to Server
      uploadMessageButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String username = usernameText.getText();
            String message = uploadMessageText.getText();

            String hostName = hostNameText.getText();
            String portNum = portNumText.getText();
            if (hostName.isEmpty()) {
               hostName = "localhost";
            }
            if (portNum.isEmpty()) {
               portNum = "1";
            }

            try{
               message = helper.getEcho(message, username);
               new AddMessage(message, username);

               frame.setVisible(false);
               title4.setVisible(false);
               uploadMessageLabel.setVisible(false);
               uploadMessageText.setVisible(false);
               uploadMessageButton.setVisible(false);

               title3.setVisible(true);
               choiceLabel.setVisible(true);
               uploadMessage.setVisible(true);
               retrieveMessages.setVisible(true);
               downloadOne.setVisible(true);
               downloadAll.setVisible(true);
               logout.setVisible(true);
               confirmChoice.setVisible(true);
               frame.setVisible(true);

               uploadMessageText.setText("");
            }
            catch (Exception e1) {
               e1.printStackTrace();
            }
         }
      });

      // Message Stroage index is given
      chosenIndexButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String indexAsString = chosenIndexText.getText();
            String message = "";

            try {
               Scanner reader = new Scanner(MessageStorage);
               ArrayList<String> listOfLines = new ArrayList<String>();

               while (reader.hasNextLine()) {
                  String line = reader.nextLine();
                  listOfLines.add(line);
               }

               try {
                  int index = Integer.parseInt(indexAsString.trim());
                  int lengthOfList = listOfLines.size();

                  if (index >= lengthOfList) {
                     JOptionPane.showMessageDialog(null,
                             "Entered index too large for list.\n" +
                                     "List has maximum index of " + (lengthOfList - 1) + ".\n" +
                                     "Please view the list before selecting an index.",
                             "Index entered is too large",
                             JOptionPane.ERROR_MESSAGE
                     );
                  }
                  else{
                     message = listOfLines.get(index);
                     messageReceivedLabel.setText(message);

                     frame.add(messageReceivedLabel);
                     frame.add(downloadOneFileNameLabel);
                     frame.add(downloadOneFileNameText);
                     frame.add(downloadOneButton);

                     messageChosenLabel.setVisible(true);
                     messageReceivedLabel.setVisible(true);
                     downloadOneFileNameLabel.setVisible(true);
                     downloadOneFileNameText.setVisible(true);
                     downloadOneButton.setVisible(true);
                  }
               }
               catch (Exception ex) {
                  ex.printStackTrace();
               }
            }
            catch (Exception ex) {
               ex.printStackTrace();
            }
         }
      });

      downloadOneButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String message = messageReceivedLabel.getText();
            String fileName = downloadOneFileNameText.getText();

            new ReadFile(fileName, message);

            frame.setVisible(false);
            title5.setVisible(false);
            chosenIndexLabel.setVisible(false);
            chosenIndexText.setVisible(false);
            chosenIndexButton.setVisible(false);
            messageChosenLabel.setVisible(false);
            messageReceivedLabel.setVisible(false);
            downloadOneFileNameLabel.setVisible(false);
            downloadOneFileNameText.setVisible(false);
            downloadOneButton.setVisible(false);

            title3.setVisible(true);
            choiceLabel.setVisible(true);
            uploadMessage.setVisible(true);
            retrieveMessages.setVisible(true);
            downloadOne.setVisible(true);
            downloadAll.setVisible(true);
            logout.setVisible(true);
            confirmChoice.setVisible(true);
            frame.setVisible(true);

            chosenIndexText.setText("");
            messageReceivedLabel.setText("");
            downloadOneFileNameText.setText("");
         }
      });

      downloadAllButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String fileName = downloadAllFileNameText.getText();
            new ReadFile(fileName);

            frame.setVisible(false);
            title6.setVisible(false);
            downloadAllFileNameLabel.setVisible(false);
            downloadAllFileNameText.setVisible(false);
            downloadAllButton.setVisible(false);

            title3.setVisible(true);
            choiceLabel.setVisible(true);
            uploadMessage.setVisible(true);
            retrieveMessages.setVisible(true);
            downloadOne.setVisible(true);
            downloadAll.setVisible(true);
            logout.setVisible(true);
            confirmChoice.setVisible(true);
            frame.setVisible(true);

            downloadAllFileNameText.setText("");
         }
      });
   }
}
