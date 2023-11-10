package userInterface.displayables.admin;

import phonebook.AdressFactory;
import userInterface.Requestor;
import userInterface.displayables.Displayable;
import userInterface.events.SwitchEvent;

import java.util.HashMap;

import static userInterface.Activities.*;

/**
 * @author Tobias Heidlund
 */
public class CreateAdress extends Displayable {


    public CreateAdress(Requestor requestor, HashMap<String, Object> extras) {
        super(requestor, extras);
    }

    @Override
    public void Display() {

    }

    @Override
    public SwitchEvent Interact() {

        AdressFactory adress = new AdressFactory();
        adress.setAdress(requestor.getString("Street Adress:"));
        adress.setPostalAdress(requestor.getInt("Postal Adress"));
        adress.setCity(requestor.getString("City:"));
        adress.setPortNumber(requestor.getInt("Port number:"));

        return new SwitchEvent.SwitchEventBuilder(CREATE_AND_DISPLAY).addExtras(extras)
                .addExtra(EXTRAS_ADRESS,adress.build())
                .build();
    }
}
