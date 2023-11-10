package userInterface.displayables.admin;

import phonebook.PersonFactory;
import userInterface.Requestor;
import userInterface.displayables.Displayable;
import userInterface.events.SwitchEvent;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class CreatePerson extends Displayable {


    public CreatePerson(Requestor requestor) {
        super(requestor);
    }

    @Override
    public void Display() {
    }
    @Override
    public SwitchEvent Interact() {
        PersonFactory person = new PersonFactory();
        person.setSurname(requestor.getString("Surname:"));
        person.setFirstName(requestor.getString("Firstname:"));
        person.setAge(requestor.getInt("Age:"));
        person.addPhonenumber(requestor.getString("Phone number:"));

        return new SwitchEvent.SwitchEventBuilder(ADMIN_CREATE_ADRESS)
                .addExtra(EXTRAS_PERSON_FACTORY,person)
                .build();
    }
}
