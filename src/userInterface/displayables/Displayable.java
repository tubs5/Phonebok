package userInterface.displayables;

import userInterface.Requestor;
import userInterface.events.SwitchEvent;

import java.util.HashMap;
import java.util.List;

public abstract class Displayable {

    protected final Requestor requestor;
    protected HashMap<String, Object> extras;

    public Displayable(Requestor requestor) {
        this.requestor = requestor;
    }

    public Displayable(Requestor requestor, HashMap<String, Object> extras) {
        this.requestor = requestor;
        this.extras = extras;
    }

    public abstract void Display();

    protected String ListOptionsOnScreen(List options) {
        return listToString(options);
    }

    public abstract SwitchEvent Interact();

    protected String listToString(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(i).append(". ").append(list.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
}
