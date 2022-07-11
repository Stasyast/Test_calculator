import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class WriteToFile {
  public static void writeFile(String data) {
        File file = new File("Result.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
