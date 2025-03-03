import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);

    ReadFile(String typeOfRead) {
        String currentDirectory = System.getProperty("user.dir") + "\\";
        File MessageStorage = new File(currentDirectory + "messageStorage.txt");

        try {
            if (MessageStorage.exists()) {
                Scanner reader = new Scanner(MessageStorage);
                ArrayList<String> listOfLines = new ArrayList<String>();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    listOfLines.add(line);
                }

                if (typeOfRead.equals("retrieve")) {
                    for (String line : listOfLines) {
                        System.out.println(line);
                    }
                }
                else if (typeOfRead.equals("downloadOne")) {
                    try {
                        System.out.print("Enter the index of the message you want ('.' to cancel): ");
                        String indexAsString = br.readLine();

                        try {
                            int index = Integer.parseInt(indexAsString);
                            int lengthOfList = listOfLines.size();

                            if (index > lengthOfList) {
                                System.out.println("Entered index too large for list." +
                                        "Please view the list before choosing to download.");
                            }
                            else {
                                String message = listOfLines.get(index);
                                System.out.println("Chosen message: " + message);

                                System.out.print("Confirm this is the correct message (Y/N): ");
                                String confirmMessage = br.readLine();

                                if (confirmMessage.equals("Y")) {
                                    try {
                                        System.out.print("Enter the name of the download file: ");
                                        String fileName = br.readLine();

                                        FileWriter createFile = new FileWriter(currentDirectory +  fileName + ".txt");
                                        createFile.write(message);
                                        createFile.close();
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    System.out.println("Download cancelled.");
                                }
                            }
                        }
                        catch (NumberFormatException e) {
                            if (indexAsString.equals(".")){
                                System.out.println("Download cancelled.");
                            }
                            else{
                                e.printStackTrace();
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (typeOfRead.equals("downloadAll")){
                    try {
                        System.out.print("Enter the name of the download file: ");
                        String fileName = br.readLine();

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

                reader.close();
            }
            else {
                System.out.println("File does not exist");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}