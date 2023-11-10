package userInterface.displayables;

import userInterface.Activities;
import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.HashMap;

/**
 * @author Tobias Heidlund
 */
public class SearchSelect extends Displayable{
    public SearchSelect(Requestor requestor,HashMap<String,Object> extras) {
        super(requestor,extras);
    }

    @Override
    public void Display() {
        //requestor.print("Please select one of the following options:");
    }


    @Override
    public SwitchEvent Interact() {
        String searchMode = (String) extras.get(Activities.EXTRAS_SEARCH);
        String query = requestor.getString(switch (searchMode){
            case Search.NAME -> "Please enter the persons first name:";
            case Search.SURNAME -> "Please enter the persons surname";
            case Search.ADRESS ->  "Please enter a street adress";
            default -> "Please enter your search query";
        });
        return new SwitchEvent.SwitchEventBuilder(Activities.PERSON_LIST)
                .addExtra(Activities.EXTRAS_SEARCH,searchMode)
                .addExtra(Activities.EXTRAS_QUERY,query).build();
    }
}
