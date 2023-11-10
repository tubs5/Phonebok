package userInterface.displayables.admin;

import phonebook.Person;
import userInterface.Activities;
import userInterface.Requestor;
import userInterface.displayables.Displayable;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.EXTRAS_PERSON;

/**
 * @author Tobias Heidlund
 */
public class UpdatePhoneNumbers extends Displayable {
    private static final String CREATE_NEW = "Create a new Phonenumber";
    private static final String UPDATE = "Update a existing Phonenumber";
    private static final String REMOVE = "Remove a existing Phonenumber";
    final List<String> options = List.of(CREATE_NEW, UPDATE, REMOVE);

    public UpdatePhoneNumbers(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    @Override
    public void Display() {

    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        switch (selectedOption) {
            case CREATE_NEW -> createNewPhonenumber();
            case UPDATE -> updatePhonenumber();
            case REMOVE -> removePhonenumber();
        }
        return new SwitchEvent.SwitchEventBuilder(Activities.PERSON_DISPLAY).addExtras(extras).build();

    }

    private void removePhonenumber() {
        Person person = (Person) extras.get(EXTRAS_PERSON);
        int numberIndex = SelectNumber(person);
        person.getPhoneNumbers().remove(numberIndex);
    }

    private int SelectNumber(Person person) {
        return requestor.getInt(ListOptionsOnScreen(person.getPhoneNumbers()));
    }

    private void updatePhonenumber() {
        removePhonenumber();
        createNewPhonenumber();

    }

    private void createNewPhonenumber() {
        Person person = (Person) extras.get(EXTRAS_PERSON);
        person.addPhonenumber(requestor.getString("New number:"));
    }

    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options), 0, options.size());
        return SelectOption(response);
    }
}
