package userInterface;

import phonebook.Adress;
import phonebook.Person;
import phonebook.PersonFactory;
import phonebook.Phonebook;
import user.Admin;
import user.Guest;
import user.User;
import userInterface.displayables.*;
import userInterface.displayables.admin.*;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;
import static userInterface.displayables.Search.*;

/**
 * @author Tobias Heidlund
 */
public class UserInterface {

    private final Phonebook phonebook;
    private final Requestor requestor = new Requestor();
    Displayable currentScreen = new MainMenu(requestor);
    private User user = new Guest();

    public UserInterface(Phonebook phonebook) {
        this.phonebook = phonebook;
    }

    private static Person UpdateAdressOfAPerson(HashMap<String, Object> extras, Adress adress) {
        Person person = (Person) extras.get(EXTRAS_PERSON);
        person.setAdress(adress);
        return person;
    }

    public void run() {
        while (currentScreen != null) {
            currentScreen.Display();
            SwitchEvent switchEvent = currentScreen.Interact();
            if (switchEvent.getActivity() == null) return;
            currentScreen = switchActivity(switchEvent);
        }
    }

    private Displayable switchActivity(SwitchEvent switchEvent) {
        HashMap<String, Object> extras = switchEvent.getExtras();

        return switch (switchEvent.getActivity()) {
            case ADMIN_CREATE_PERSON -> new CreatePerson(requestor);
            case ADMIN_LOGIN -> new Login(requestor);
            case ADMIN_MAIN_MENU -> {
                setAdmin(extras);
                yield new MainMenuAdmin(requestor);
            }
            case ADMIN_UPDATE -> new Update(requestor, extras);
            case MAIN_MENU -> {
                //Logsout Admin
                user = new Guest();
                yield new MainMenu(requestor);
            }
            case PERSON_DISPLAY -> {
                extras.put(EXTRAS_ADRESS, ((Person) extras.get(EXTRAS_PERSON)).getAdress());
                if (user instanceof Admin) {
                    //Save any changes that might have been made.
                    phonebook.notifyObservers();
                    yield new PersonDisplayAdmin(requestor, extras);
                }
                yield new PersonDisplay(requestor, extras);
            }
            case PERSON_LIST -> new PersonList(requestor, RequestPersonList(switchEvent));
            case SEARCH -> new Search(requestor);
            case SEARCH_SELECT -> new SearchSelect(requestor, extras);
            case CREATE_AND_DISPLAY -> {
                Person person = UpdatePersonOrAdress(extras);
                extras.put(EXTRAS_PERSON, person);
                phonebook.notifyObservers();
                yield new PersonDisplayAdmin(requestor, extras);

            }
            case ADMIN_CREATE_ADRESS -> new CreateAdress(requestor, extras);
            case DELETE -> {
                RemovePersonFromPhonebook(extras);
                yield new MainMenuAdmin(requestor, extras);
            }
            case ADMIN_PHONE_NUMBERS -> new UpdatePhoneNumbers(requestor, extras);
            default -> null;
        };
    }

    private void RemovePersonFromPhonebook(HashMap<String, Object> extras) {
        Person person = (Person) extras.get(EXTRAS_PERSON);
        RemovePerson(person);
        extras.remove(EXTRAS_PERSON);
    }

    private void setAdmin(HashMap<String, Object> extras) {
        if (extras.containsKey(EXTRAS_USER)) {
            if (extras.get(EXTRAS_USER) instanceof Admin) {
                user = (Admin) extras.get(EXTRAS_USER);
            }
        }
    }

    private Person UpdatePersonOrAdress(HashMap<String, Object> extras) {
        Adress adress = (Adress) extras.get(EXTRAS_ADRESS);
        if (extras.containsKey(EXTRAS_PERSON_FACTORY)) {
            return GeneratePersonWithAnAdress(extras, adress);
        } else if (extras.containsKey(EXTRAS_PERSON)) {
            return UpdateAdressOfAPerson(extras, adress);
        }
        return null;
    }

    private Person GeneratePersonWithAnAdress(HashMap<String, Object> extras, Adress adress) {
        PersonFactory personFactory = (PersonFactory) extras.get(EXTRAS_PERSON_FACTORY);
        personFactory.setAdress(adress);
        Person person = phonebook.addPerson(personFactory);
        extras.remove(EXTRAS_PERSON_FACTORY);
        return person;
    }


    private void RemovePerson(Person person) {
        phonebook.removePerson(person);
    }


    private HashMap<String, Object> RequestPersonList(SwitchEvent switchEvent) {
        HashMap<String, Object> map = switchEvent.getExtras();
        phonebook.Search search = phonebook.search;
        String searchPhrase = (String) map.get(EXTRAS_QUERY);

        List<Person> persons = switch ((String) map.get(EXTRAS_SEARCH)) {
            case SURNAME -> search.searchLastName(searchPhrase);
            case ADRESS -> search.searchAdress(searchPhrase);
            case NAME -> search.searchFirstName(searchPhrase);
            default -> search.searchFree(searchPhrase);
        };

        map.put(EXTRAS_PERSON_LIST, persons);

        return map;
    }


}
