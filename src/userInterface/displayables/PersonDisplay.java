package userInterface.displayables;

import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class PersonDisplay extends Displayable {

    private final List<String> options = List.of(MAIN_MENU, QUIT);

    public PersonDisplay(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }


    @Override
    public void Display() {
        requestor.print(extras.get(EXTRAS_PERSON).toString());
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
