import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class AddMessage {
    String message;

    AddMessage(String message) {
        try {
            String currentDirectory = System.getProperty("user.dir");
            FileWriter writeToFile = new FileWriter(new File(currentDirectory + "\\messageStorage.txt"), true);
            writeToFile.write(message + System.lineSeparator());
            writeToFile.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
