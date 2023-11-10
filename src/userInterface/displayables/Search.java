package userInterface.displayables;

import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.List;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class Search extends Displayable {

    public static final String SURNAME = "Surname";
    public static final String ADRESS = "Adress";
    public static final String NAME = "First Name";
    public static final String FREE = "Free Search";

    private final List<String> options = List.of(SURNAME, ADRESS, NAME, FREE, QUIT);

    public Search(Requestor requestor) {
        super(requestor);
    }

    @Override
    public void Display() {
        requestor.print("Please select one of the following alternatives:");
    }

    private SwitchEvent SelectOption(int response) {
        String selectedOption = options.get(response);
        SwitchEvent.SwitchEventBuilder switchEvent = new SwitchEvent.SwitchEventBuilder(SEARCH_SELECT);
        switch (selectedOption) {
            case SURNAME -> switchEvent.addExtra(EXTRAS_SEARCH, SURNAME);
            case ADRESS -> switchEvent.addExtra(EXTRAS_SEARCH, ADRESS);
            case NAME -> switchEvent.addExtra(EXTRAS_SEARCH, NAME);
            case FREE -> switchEvent.addExtra(EXTRAS_SEARCH, FREE);
            default -> switchEvent.setActivity(null);
        }
        return switchEvent.build();
    }

    @Override
    public SwitchEvent Interact() {
        int response = requestor.getInt(ListOptionsOnScreen(options), 0, options.size());
        return SelectOption(response);
    }


}
