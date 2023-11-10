package userInterface.displayables.admin;

import phonebook.Person;
import userInterface.Activities;
import userInterface.Requestor;
import userInterface.displayables.Displayable;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class Update extends Displayable {

    private static final String UPDATE_SURNAME = "Update Surname";
    private static final String UPDATE_FIRSTNAME = "Update Firstname";
    private static final String MOVE_ADRESS = "Move Adress";
    private static final String UPDATE_AGE = "Update Age";
    private static final String UPDATE_PHONE_NUMBERS = "Update Phone Numbers";


    final List<String> options = List.of(UPDATE_FIRSTNAME, UPDATE_SURNAME, UPDATE_AGE, UPDATE_PHONE_NUMBERS, MOVE_ADRESS, ADMIN_MAIN_MENU);

    public Update(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    @Override
    public void Display() {

    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        Person person = (Person) extras.get(EXTRAS_PERSON);
        String nextScene = switch (selectedOption) {
            case UPDATE_FIRSTNAME -> {
                person.updateFirstName(requestor.getString("New Firstname:"));
                yield Activities.PERSON_DISPLAY;
            }
            case UPDATE_SURNAME -> {
                person.updateSurname(requestor.getString("New surname:"));
                yield Activities.PERSON_DISPLAY;
            }
            case MOVE_ADRESS -> ADMIN_CREATE_ADRESS;
            case UPDATE_AGE -> {
                person.updateAge(requestor.getInt("New Age:"));
                yield Activities.PERSON_DISPLAY;
            }
            case UPDATE_PHONE_NUMBERS -> ADMIN_PHONE_NUMBERS;
            case ADMIN_MAIN_MENU -> ADMIN_MAIN_MENU;
            default -> null;
        };

        return new SwitchEvent.SwitchEventBuilder(nextScene).addExtras(extras).build();

    }

    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options), 0, options.size());
        return SelectOption(response);
    }
}
