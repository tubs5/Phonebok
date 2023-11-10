import phonebook.Person;
import phonebook.Phonebook;
import userInterface.UserInterface;

import java.io.IOException;
import java.util.List;

/**
 * @author Tobias Heidlund
 */

public class Main {
    public static void main(String[] args) {
        Phonebook phonebook = GeneratePhonebookFromEarlierInstanceWhenPossible();
        phonebook.addObserver(new FileInteractor());
        UserInterface userInterface = new UserInterface(phonebook);
        userInterface.run();
    }

    private static Phonebook GeneratePhonebookFromEarlierInstanceWhenPossible() {
        List<Person> personList;
        try {
            personList = FileInteractor.LoadFile();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Phonebook phonebook;
        if (personList == null) {
            phonebook = new Phonebook();
            System.out.println("No earlier instances found, Creating new Phonebook");
        } else phonebook = new Phonebook(personList);
        return phonebook;
    }


}