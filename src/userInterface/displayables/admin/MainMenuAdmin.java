package userInterface.displayables.admin;

import userInterface.Requestor;
import userInterface.displayables.MainMenu;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class MainMenuAdmin extends MainMenu {


    final List<String> options = List.of(SEARCH, ADMIN_CREATE_PERSON,ADMIN_LOGOUT,QUIT);

    public MainMenuAdmin(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    public MainMenuAdmin(Requestor requestor) {
        super(requestor);
    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        return switch (selectedOption) {
            case SEARCH -> new SwitchEvent.SwitchEventBuilder(SEARCH).build();
            case ADMIN_CREATE_PERSON -> new SwitchEvent.SwitchEventBuilder(ADMIN_CREATE_PERSON).build();
            case ADMIN_LOGOUT -> new SwitchEvent.SwitchEventBuilder(MAIN_MENU).build();
            default -> null;
        };
    }
    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options),0,options.size());
        return SelectOption(response);
    }
}
