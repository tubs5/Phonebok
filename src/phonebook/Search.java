package phonebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobias Heidlund
 */
public class Search implements Serializable {
    private final Phonebook phonebook;
    public Search(Phonebook p){
        phonebook = p;
    }

    public List<Person> searchFirstName(String s){
        List<Person> matches = new ArrayList<>();
        for (Person person : phonebook.getPersons()) {
            if(person.searchFirstName(s)){
                matches.add(person);
            }
        }
        return matches;
    }
    public List<Person> searchLastName(String s){
        List<Person> matches = new ArrayList<>();
        for (Person person : phonebook.getPersons()) {
            if(person.searchSurName(s)){
                matches.add(person);
                return matches;
            }
        }
        return matches;
    }
    public List<Person> searchAdress(String s){
        List<Person> matches = new ArrayList<>();
        for (Person person : phonebook.getPersons()) {
            if(person.getAdress().searchAdress(s)){
                matches.add(person);
            }
        }
        return matches;
    }
    public List<Person> searchFree(String s){
        List<Person> matches = new ArrayList<>();
        for (Person person : phonebook.getPersons()) {
            if(person.search(s)){
                matches.add(person);
            }
        }
        return matches;
    }

}
