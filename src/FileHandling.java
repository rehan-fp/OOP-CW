import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandling {
    public void writeObjectToFile(String filepath,Object toWriteObject) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Saves the arraylist in bytecode using Serializers
            objectOut.writeObject(toWriteObject);
            objectOut.close();
            System.out.println("File has been saved successfully!");
            System.out.println("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList readObjectFile(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            // Reads the bytecode in the file to an arraylist and returns it
            ArrayList obj = (ArrayList)objectIn.readObject();
            System.out.println("File has been read successfully!");
            System.out.println("");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            System.out.println("File is empty or unavailable!");
            return new ArrayList();
        }
    }
}
