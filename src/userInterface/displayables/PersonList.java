package userInterface.displayables;

import phonebook.Person;
import userInterface.Activities;
import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings("unchecked")
public class PersonList extends Displayable {
    public PersonList(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    @Override
    public void Display() {
    }

    private SwitchEvent SelectOption(int response) {
        Person person = ((List<Person>) extras.get(Activities.EXTRAS_PERSON_LIST)).get(response);
        return new SwitchEvent.SwitchEventBuilder(Activities.PERSON_DISPLAY).addExtra(Activities.EXTRAS_PERSON, person).build();

    }

    @Override
    public SwitchEvent Interact() {
        List<Person> persons = ((List<Person>) extras.get(Activities.EXTRAS_PERSON_LIST));
        switch (persons.size()) {
            case 0 -> {
                requestor.print("Nothing was found on that query please retry");
                return new SwitchEvent.SwitchEventBuilder(Activities.SEARCH).build();
            }
            case 1 -> {
                requestor.print("One person was found on that query");
                extras.put(Activities.EXTRAS_PERSON, persons.get(0));
                return new SwitchEvent.SwitchEventBuilder(Activities.PERSON_DISPLAY).addExtras(extras).build();
            }
            default -> {
                int response = requestor.getInt(ListOptionsOnScreen(persons), 0, persons.size());
                return SelectOption(response);
            }

        }
    }
}
