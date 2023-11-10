package phonebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings("deprecation")
public class Phonebook extends Observable implements Serializable {

    private final List<Person> persons;
    public final Search search = new Search(this);

    public Phonebook(){
        persons = new ArrayList<>();
    }

    public Phonebook(List<Person> persons) {
        this.persons = persons;

    }

    protected List<Person> getPersons() {
        return persons;
    }



    public Person addPerson(PersonFactory personFactory){
        Person person = personFactory.build();
        if(!persons.contains(person)){
            persons.add(person);
            notifyObservers();
        }
        return person;
    }

    public void removePerson(Person person){
        boolean removed = persons.remove(person);
        if (removed){
            notifyObservers();
        }
    }


    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers(persons);
    }
}
