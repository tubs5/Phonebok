package userInterface.displayables;

import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class MainMenu extends Displayable {

    final List<String> options = List.of(SEARCH, ADMIN_LOGIN, QUIT);

    public MainMenu(Requestor requestor) {
        super(requestor);
    }

    public MainMenu(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }


    @Override
    public void Display() {
        requestor.print("Please select one of the following options:");
    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        return new SwitchEvent.SwitchEventBuilder(selectedOption).build();

    }

    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options), 0, options.size());
        return SelectOption(response);
    }
}