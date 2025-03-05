import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class AddMessage {
    String message;
    String username;

    AddMessage(String message, String username) {
        try {
            String currentDirectory = System.getProperty("user.dir");
            FileWriter writeToFile = new FileWriter(new File(currentDirectory + "\\Message_System\\src\\messageStorage.txt"), true);
            writeToFile.write(username + " - " + message + System.lineSeparator());
            writeToFile.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
