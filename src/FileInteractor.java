import phonebook.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings("ALL")
public class FileInteractor implements Observer {

    private static final String storageLocation = "data.txt";

    public static ArrayList<Person> LoadFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream;
        try {

            fileInputStream = new FileInputStream(storageLocation);
        } catch (FileNotFoundException fileNotFoundException) {
            return null;

        }
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Person> phonebook = (ArrayList<Person>) objectInputStream.readObject();
        objectInputStream.close();
        return phonebook;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void update(Observable o, Object arg) {
        ArrayList phonebook = (ArrayList) arg;
        try {
            Save(phonebook);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void Save(ArrayList phonebook) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(storageLocation);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(phonebook);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

}
