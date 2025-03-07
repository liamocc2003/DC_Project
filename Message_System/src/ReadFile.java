import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ReadFile {
    String currentDirectory = System.getProperty("user.dir") + "\\";
    File MessageStorage = new File(currentDirectory + "Message_system\\src\\" + "messageStorage.txt");

    ReadFile() {
        try {
            if (MessageStorage.exists()) {
                Scanner reader = new Scanner(MessageStorage);
                ArrayList<String> listOfLines = new ArrayList<String>();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    listOfLines.add(line);
                }
                reader.close();

                String allMessages = "";
                for (String line : listOfLines) {
                    allMessages += line + "\n";
                }
                if (allMessages.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "No messages that are available to retrieve.",
                            "No Messages Found",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            allMessages,
                            "Messages retrieved from Server",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "File does not exist",
                        "File Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    ReadFile (String fileName){
        try {
            if (MessageStorage.exists()) {
                Scanner reader = new Scanner(MessageStorage);
                ArrayList<String> listOfLines = new ArrayList<String>();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    listOfLines.add(line);
                }
                reader.close();

                try {
                    FileWriter createFile = new FileWriter(currentDirectory + fileName + ".txt");

                    for (String line : listOfLines) {
                        createFile.write(line + System.lineSeparator());
                    }
                    createFile.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "File does not exist",
                        "File Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    ReadFile (String fileName, String message) {
        try {
            if (MessageStorage.exists()) {
                Scanner reader = new Scanner(MessageStorage);
                ArrayList<String> listOfLines = new ArrayList<String>();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    listOfLines.add(line);
                }
                reader.close();

                try {
                    FileWriter createFile = new FileWriter(currentDirectory + fileName + ".txt");
                    createFile.write(message);
                    createFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "File does not exist",
                        "File Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}