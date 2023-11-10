package userInterface.displayables.admin;

import userInterface.Requestor;
import userInterface.displayables.PersonDisplay;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class PersonDisplayAdmin extends PersonDisplay {

    private final List<String> options = List.of(DELETE, ADMIN_UPDATE, ADMIN_MAIN_MENU, QUIT);

    public PersonDisplayAdmin(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        return new SwitchEvent.SwitchEventBuilder(selectedOption).addExtras(extras).build();

    }

    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options), 0, options.size());
        return SelectOption(response);
    }
}
